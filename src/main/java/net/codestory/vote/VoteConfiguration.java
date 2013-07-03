package net.codestory.vote;

import net.codestory.http.*;
import net.codestory.http.routes.*;

public class VoteConfiguration implements Configuration {
  @Override
  public void configure(Routes routes) {
    routes.get("/", () -> "Hello");
  }
}
