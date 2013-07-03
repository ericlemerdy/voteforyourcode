package net.codestory.vote;

import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.http.templating.*;

import com.google.inject.*;

public class VoteConfiguration implements Configuration {
  private final Injector injector;

  public VoteConfiguration(Module... modules) {
    injector = Guice.createInjector(modules);
  }

  @Override
  public void configure(Routes routes) {
    routes.serve("file:app");
    routes.addResource(injector.getInstance(IndexResource.class));
  }
}
