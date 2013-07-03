package net.codestory.vote;

import net.codestory.http.annotations.*;
import net.codestory.http.templating.*;

public class IndexResource {
  @Get("/")
  public String index() {
    Template template = new Template("file:app/index.html");

    return template.render("left_gist", "dgageot/9895cbae5fbd70892d0d", "right_gist", "dgageot/4718233");
  }
}
