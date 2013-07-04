package net.codestory.vote;

import java.util.*;

import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.vote.gists.*;

public class VoteConfiguration implements Configuration {
  private final Random random;
  private final MatchMaker matchMaker;
  private final Gists gists;
  private final IndexResource indexResource;

  public VoteConfiguration() {
    random = createRandom();
    gists = createGists();
    matchMaker = createMatchMaker(random, gists);
    indexResource = createIndexResource(matchMaker);
  }

  @Override
  public void configure(Routes routes) {
    routes.serve("file:app");
    routes.addResource(indexResource);
  }

  protected IndexResource createIndexResource(MatchMaker matchMaker) {
    return new IndexResource(matchMaker);
  }

  protected Random createRandom() {
    return new Random();
  }

  protected Gists createGists() {
    return new Gists(
        new Gist("dgageot/9895cbae5fbd70892d0d"),
        new Gist("dgageot/4718233")
    );
  }

  protected MatchMaker createMatchMaker(Random random, Gists gists) {
    return new MatchMaker(random, gists);
  }
}
