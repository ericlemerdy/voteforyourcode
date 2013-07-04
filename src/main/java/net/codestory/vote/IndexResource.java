package net.codestory.vote;

import net.codestory.http.annotations.*;
import net.codestory.http.templating.*;
import net.codestory.vote.gists.*;

public class IndexResource {
  private final Gists gists;

  public IndexResource(Gists gists) {
    this.gists = gists;
  }

  @Get("/")
  public String index() {
    Template index = new Template("file:app/index.html");

    return index.render("fight", gists.randomFight());
  }

  @Get("/vote/:winner/:looser")
  public String vote(int winner, int looser) {
    gists.match(winner, looser);

    Template index = new Template("file:app/index.html");

    return index.render("fight", gists.randomFight());
  }
}
