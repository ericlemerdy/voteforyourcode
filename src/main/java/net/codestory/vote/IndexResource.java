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
    Candidates candidates = gists.candidates();

    Template template = new Template("file:app/index.html");

    return template.render(
        "left_gist", candidates.left(),
        "right_gist", candidates.right()
    );
  }
}
