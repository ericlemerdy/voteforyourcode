package net.codestory.vote;

import static net.codestory.http.Payload.seeOther;

import java.util.*;

import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.http.templating.*;
import net.codestory.vote.gists.*;
import net.codestory.vote.repository.*;

public class VoteConfiguration implements Configuration {
  private final Random random;
  private final Gists gists;
  private final VoteRepository voteRepository;
  private final MatchMaker matchMaker;

  public VoteConfiguration() {
    String mongoUri = System.getProperty("mongo.uri", "mongodb://localhost/code-story-votes");

    random = createRandom();
    gists = createGists();
    voteRepository = createVoteRepository(mongoUri);
    matchMaker = createMatchMaker(random, gists, voteRepository);
  }

  @Override
  public void configure(Routes routes) {
    routes.serve("file:app");
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
        new Gist("FooBarGolo", "dgageot/9895cbae5fbd70892d0d"),
        new Gist("CodeStoryStatusTest", "dgageot/4718233")
    );
  }

  protected MatchMaker createMatchMaker(Random random, Gists gists, VoteRepository voteRepository) {
    return new MatchMaker(random, gists, voteRepository);
  }

  protected VoteRepository createVoteRepository(String uri) {
    return new VoteRepository(uri);
  }
}
