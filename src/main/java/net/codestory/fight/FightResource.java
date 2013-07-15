package net.codestory.fight;

import static net.codestory.http.Payload.*;

import net.codestory.fight.ranking.*;
import net.codestory.http.*;
import net.codestory.http.annotations.*;
import net.codestory.http.templating.*;

public class FightResource {
  private final MatchMaker matchMaker;

  public FightResource(MatchMaker matchMaker) {
    this.matchMaker = matchMaker;
  }

  @Get("/")
  public String fight() {
    return new Template("classpath:app/fight/index.html").render("fight", matchMaker.randomFight());
  }

  @Get("/win/left/:fightId")
  public Payload winLeft(String fightId) {
    matchMaker.fightWonByLeft(fightId);

    return seeOther("/fight/");
  }

  @Get("/win/right/:fightId")
  public Payload winRight(String fightId) {
    matchMaker.fightWonByRight(fightId);

    return seeOther("/fight/");
  }
}
