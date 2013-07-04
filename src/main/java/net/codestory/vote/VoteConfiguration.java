package net.codestory.vote;

import java.util.*;

import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.vote.gists.*;

public class VoteConfiguration implements Configuration {
  private final Random random;
  private final Gists gists;
  private final IndexResource indexResource;

  public VoteConfiguration() {
    random = createRandom();
    gists = createGists(random);
    indexResource = createIndexResource(gists);
  }

  @Override
  public void configure(Routes routes) {
    routes.serve("file:app");
    routes.addResource(indexResource);
  }

  protected IndexResource createIndexResource(Gists gists) {
    return new IndexResource(gists);
  }

  protected Random createRandom() {
    return new Random();
  }

  protected Gists createGists(Random random) {
    return new DemoGists(random);
  }

  public Gists getGists() {
    return gists;
  }

  public IndexResource getIndexResource() {
    return indexResource;
  }
}
