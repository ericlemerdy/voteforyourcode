package net.codestory.vote;

import net.codestory.http.annotations.*;
import net.codestory.http.templating.*;
import net.codestory.vote.gists.*;

public class IndexResource {
  private final MatchMaker matchMaker;
  private final Gists gists;

  public IndexResource(MatchMaker matchMaker, Gists gists) {
    this.matchMaker = matchMaker;
    this.gists = gists;
  }

  @Get("/")
  public String index() {
    Template index = new Template("file:app/index.html");

    return index.render("fight", matchMaker.randomFight());
  }

  @Get("/vote/:winner/:looser")
  public String vote(int winner, int looser) {
    gists.match(winner, looser);

    return index();
  }
}
