package net.codestory.vote;

import static net.codestory.http.Payload.*;

import java.util.*;

import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.http.templating.*;
import net.codestory.vote.captcha.*;
import net.codestory.vote.gists.*;
import net.codestory.vote.repository.*;

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
    routes.staticDir("file:app");
    routes.filter(throttleFilter);
    routes.get("/", this::index);
    routes.get("/win/left/:fightId", (fightId) -> {
      matchMaker.fightWonByLeft(fightId);
      return seeOther("/");
    });
    routes.get("/win/right/:fightId", (fightId) -> {
      matchMaker.fightWonByRight(fightId);
      return seeOther("/");
    });
  }

  private String index() {
    return new Template("file:app/index.html").render("fight", matchMaker.randomFight());
  }

  // Poor man's IoC
  protected Random createRandom() {
    return new Random();
  }

  protected Gists createGists() {
    return new Gists(
        new Gist("FooBarGolo", "https://gist.github.com/dgageot/9895cbae5fbd70892d0d.js"),
        new Gist("CodeStoryStatusTest", "https://gist.github.com/dgageot/4718233.js"),
        new Gist("Forget about Lambdas", "https://gist.github.com/dgageot/5840050.js"),
        new Gist("Mockito and java8", "https://gist.github.com/dgageot/5445181.js"),
        new Gist("Avion Ruby", "https://gist.github.com/dgageot/4996069.js"),
        new Gist("Main Web", "http://gist-it.appspot.com/github/CodeStory/voteforyourcode/blob/master/src/main/java/net/codestory/vote/MainVote.java"),
        new Gist("Twitter say", "https://gist.github.com/dgageot/2711039.js")
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
