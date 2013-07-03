package net.codestory.vote;

import javax.inject.*;
import net.codestory.http.annotations.*;
import net.codestory.http.templating.*;
import net.codestory.vote.gists.*;

public class IndexResource {
  private final Gists gists;

  @Inject
  public IndexResource(Gists gists) {
    this.gists = gists;
  }

  @Get("/")
  public String index() {
    Template template = new Template("file:app/index.html");

    return template.render(
        "left_gist", gists.left(),
        "right_gist", gists.right()
    );
  }
}
