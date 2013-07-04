package net.codestory.vote;

import static net.codestory.http.Payload.seeOther;

import java.util.*;

import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.http.templating.*;
import net.codestory.vote.gists.*;

public class VoteConfiguration implements Configuration {
  private final Random random;
  private final MatchMaker matchMaker;
  private final Gists gists;

  public VoteConfiguration() {
    random = createRandom();
    gists = createGists();
    matchMaker = createMatchMaker(random, gists);
  }

  @Override
  public void configure(Routes routes) {
    routes.serve("file:app");

    routes.get("/", this::index);
    routes.get("/win/left/:fightId", (fightId) -> {
      matchMaker.leftWins(fightId);
      return seeOther("/");
    });
    routes.get("/win/right/:fightId", (fightId) -> {
      matchMaker.rightWins(fightId);
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

  protected MatchMaker createMatchMaker(Random random, Gists gists) {
    return new MatchMaker(random, gists);
  }
}
