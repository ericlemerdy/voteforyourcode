package net.codestory.vote;

import javax.inject.Inject;
import net.codestory.http.*;
import net.codestory.http.routes.*;

import com.google.inject.*;

public class VoteConfiguration implements Configuration {
  private final Injector injector;

  @Inject
  public VoteConfiguration(Injector injector) {
    this.injector = injector;
  }

  @Override
  public void configure(Routes routes) {
    routes.serve("file:app");
    routes.addResource(injector.getInstance(IndexResource.class));
  }
}
