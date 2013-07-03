package net.codestory.vote;

import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.http.templating.*;

public class VoteConfiguration implements Configuration {
  @Override
  public void configure(Routes routes) {
    routes.serve("file:app");
    routes.addResource(new IndexResource());
  }
}
