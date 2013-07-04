package net.codestory.vote;

import net.codestory.http.annotations.*;
import net.codestory.http.templating.*;
import net.codestory.vote.gists.*;

public class IndexResource {
  private final MatchMaker matchMaker;

  public IndexResource(MatchMaker matchMaker) {
    this.matchMaker = matchMaker;
  }

  @Get("/")
  public String index() {
    Template index = new Template("file:app/index.html");

    return index.render("fight", matchMaker.randomFight());
  }

  @Get("/win/left/:fightId")
  public String leftWins(String fightId) {
    matchMaker.leftWins(fightId);

    return index();
  }

  @Get("/win/right/:fightId")
  public String rightWins(String fightId) {
    matchMaker.rightWins(fightId);

    return index();
  }
}
