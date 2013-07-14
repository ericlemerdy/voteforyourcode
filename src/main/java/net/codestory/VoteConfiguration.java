package net.codestory;

import static net.codestory.http.Payload.*;

import java.util.*;

import net.codestory.fight.captcha.*;
import net.codestory.fight.gists.*;
import net.codestory.fight.ranking.*;
import net.codestory.fight.votes.*;
import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.http.templating.*;

public class VoteConfiguration implements Configuration {
  private final Random random;
  private final Gists gists;
  private final VoteRepository voteRepository;
  private final MatchMaker matchMaker;
  private final QueryCounter queryCounter;
  private final ThrottleFilter throttleFilter;

  public VoteConfiguration() {
    String mongoUri = System.getProperty("mongo.uri", "mongodb://localhost/code-story-votes");

    random = createRandom();
    gists = createGists();
    voteRepository = createVoteRepository(mongoUri);
    matchMaker = createMatchMaker(random, gists, voteRepository);
    queryCounter = createQueryCounter();
    throttleFilter = createThrottleFilter(queryCounter);
  }

  @Override
  public void configure(Routes routes) {
    routes.staticDir("app");
    routes.filter(throttleFilter);
    routes.get("/", () -> Payload.seeOther("/fight/"));
    routes.get("/fight/", this::index);
    routes.get("/fight/win/left/:fightId", (fightId) -> {
      matchMaker.fightWonByLeft(fightId);
      return seeOther("/fight/");
    });
    routes.get("/fight/win/right/:fightId", (fightId) -> {
      matchMaker.fightWonByRight(fightId);
      return seeOther("/fight/");
    });
  }

  private String index() {
    return new Template("app/fight/index.html").render("fight", matchMaker.randomFight());
  }

  // Poor man's IoC
  protected Random createRandom() {
    return new Random();
  }

  protected Gists createGists() {
    return new Gists(
        new Gist("FooBarQix Java", "https://gist.github.com/MeddahJ/1374633.js"),
        new Gist("FooBarQix Groovy Tweetable", "https://gist.github.com/mfranck/1390934.js"),
        new Gist("FooBarQix Scala", "https://gist.github.com/MeddahJ/1403626.js"),
        new Gist("FooBarQix Scala REPL", "https://gist.github.com/ikarius/2f83cdb16554fb494718.js"),
        new Gist("FooBarQix Scala II", "http://gist-it.appspot.com/github/adetante/FooBarQix/blob/master/FooBarQix.scala"),
        new Gist("FooBarQix Scala III", "http://gist-it.appspot.com/github/bcourtine/FooBarQix/blob/master/src/main/scala/org/courtine/foobarqix/FooBarQix.scala"),
        new Gist("FooBarQix Goovy", "http://gist-it.appspot.com/github/boillodmanuel/codestory/blob/master/src/foobarqix.groovy"),
        new Gist("FooBarQix Java Mini", "http://gist-it.appspot.com/github/dbaeli/FooBarQix/blob/master/src/main/java/org/foobar/FooBarQix.java"),
        new Gist("FooBarQix Java Fillup", "http://gist-it.appspot.com/github/dbaeli/FooBarQix/blob/master/src/main/java/org/foobar/FooBarQixV2.java"),
        new Gist("FooBarQix Java No Artist", "http://gist-it.appspot.com/github/diodfr/code-story/blob/master/diodfr/codeStory/FooBarQix.java"),
        new Gist("FooBarQix Fantom", "http://gist-it.appspot.com/github/elefevre/FooBarQix/blob/master/FooBarQixFantom/fan/FooBarQix.fan"),
        new Gist("FooBarQix Gosu", "http://gist-it.appspot.com/github/elefevre/FooBarQix/blob/master/FooBarQixGosu/FooBarQix.gsp"),
        new Gist("FooBarQix Ioke", "http://gist-it.appspot.com/github/elefevre/FooBarQix/blob/master/FooBarQixIoke/FooBarQix.ik"),
        new Gist("FooBarQix Java", "http://gist-it.appspot.com/github/elefevre/FooBarQix/blob/master/FooBarQixJava/src/net/ericlefevre/FooBarQix.java"),
        new Gist("FooBarQix Java Enum", "http://gist-it.appspot.com/github/Ellene/FooBarQix/blob/master/src/FooBarQix.java"),
        new Gist("FooBarQix Java RPL", "http://gist-it.appspot.com/github/fcroiseaux/FooBarQix-scala/blob/master/src/main/java/fcx/codestory/FooBarQix3.java"),
        new Gist("FooBarQix Scala Short", "http://gist-it.appspot.com/github/fcroiseaux/FooBarQix-scala/blob/master/src/main/scala/fcx/codestory/FooBarQix1.scala"),
        new Gist("FooBarQix Scala For Loop", "http://gist-it.appspot.com/github/fcroiseaux/FooBarQix-scala/blob/master/src/main/scala/fcx/codestory/FooBarQix2.scala"),
        new Gist("FooBarQix ASM", "http://gist-it.appspot.com/github/framiere/FooBarQix/blob/master/src/main/asm/foobarqix.asm"),
        new Gist("FooBarQix C", "http://gist-it.appspot.com/github/framiere/FooBarQix/blob/master/src/main/c/foobarqix.c"),
        new Gist("FooBarQix Enum", "http://gist-it.appspot.com/github/framiere/FooBarQix/blob/master/src/main/java/fr/ramiere/codestory/FooBarQixEnum.java"),
        new Gist("FooBarQix Abuse Builder", "http://gist-it.appspot.com/github/framiere/FooBarQix/blob/master/src/main/java/fr/ramiere/codestory/FooBarQixFluentAbuseBuilder.java"),
        new Gist("FooBarQix Perf", "http://gist-it.appspot.com/github/framiere/FooBarQix/blob/master/src/main/java/fr/ramiere/codestory/FooBarQixPerf.java"),
        new Gist("FooBarQix Java NavigableMap", "http://gist-it.appspot.com/github/fsarradin/code-story/blob/master/src/main/java/fr/frs/codestory/exercise1/FooBarQix.java"),
        new Gist("FooBarQix Scala Tests", "http://gist-it.appspot.com/github/GabrielKast/FooBarQix/blob/master/foobar.scala"),
        new Gist("FooBarQixGenerator", "http://gist-it.appspot.com/github/GenevaJUG/FooBarQix/blob/master/FooBarQix/src/main/java/FooBarQixGenerator.java"),
        new Gist("FooBarQix Phantom FBQNumber", "http://gist-it.appspot.com/github/gtardif/FooBarQix-fantom/blob/master/src/FooBarQix.fan"),
        new Gist("FooBarQix Supply Chain", "http://gist-it.appspot.com/github/GTORDO/FooBarQix/blob/master/FooBarQix.java"),
        new Gist("FooBarQix Guava", "http://gist-it.appspot.com/github/guillaumerose/FooBarQix/blob/master/java/src/main/java/net/grose/foo/FooBarQix.java"),
        new Gist("FooBarQix Ruby", "http://gist-it.appspot.com/github/guillaumerose/FooBarQix/blob/master/ruby/lib/foo.rb"),
        new Gist("FooBarQix Java Fast", "http://gist-it.appspot.com/github/henri-tremblay/foobarqix/blob/master/src/main/java/org/hjf/foobarqix/FooBarQix.java"),
        new Gist("FooBarQix Yeti", "http://gist-it.appspot.com/github/jfpoilpret/yeti-katas/blob/master/foobarqix.yeti"),
        new Gist("FooBarQix HashCode-Equals", "http://gist-it.appspot.com/github/kocakosm/foobarqix/blob/master/src/org/kocakosm/foobarqix/FooBarQixNumber.java"),
        new Gist("FooBarQix Empty Lines", "http://gist-it.appspot.com/github/lgueye/foobarqix/blob/master/src/main/java/org/diveintojee/foobarquix/FooBarQix.java"),
        new Gist("FooBarQix Groovy Style", "http://gist-it.appspot.com/github/melix/CodeStory/blob/master/FooBarQix/FooBarQix.groovy"),
        new Gist("FooBarQixer", "http://gist-it.appspot.com/github/seblm/FooBarQix/blob/release/src/main/java/fr/free/lemerdy/FooBarQixer.java"),
        new Gist("FooBarQix SixTeam", "http://gist-it.appspot.com/github/SixTeam/FooBarQix/blob/master/src/main/scala/FooBarQix.scala"),
        new Gist("FooBarQix Magic Replacement", "http://gist-it.appspot.com/github/wokier/FooBarQix/blob/master/src/net/story/code/FooBarQix.java"),
        new Gist("FooBarQix Builder", "http://gist-it.appspot.com/github/YannMoisan/foobarqix/blob/master/src/com/yannmoisan/foobarqix/Main.java"),
        new Gist("FooBarQix LINE_SEPARATOR", "http://gist-it.appspot.com/github/youribonnaffe/FooBarQix/blob/master/src/main/java/foobarqix/FooBarQix.java"),
        new Gist("FooBarQix Js", "http://gist-it.appspot.com/github/a14n/FooBarQix/blob/master/js/fooBarQix.js")
    );
  }

  protected MatchMaker createMatchMaker(Random random, Gists gists, VoteRepository voteRepository) {
    return new MatchMaker(random, gists, voteRepository);
  }

  protected VoteRepository createVoteRepository(String uri) {
    return new VoteRepository(uri);
  }

  protected QueryCounter createQueryCounter() {
    return new QueryCounter();
  }

  protected ThrottleFilter createThrottleFilter(QueryCounter queryCounter) {
    return new ThrottleFilter(queryCounter);
  }
}
