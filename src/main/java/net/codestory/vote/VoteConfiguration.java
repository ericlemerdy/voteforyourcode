package net.codestory.vote;

import net.codestory.http.*;
import net.codestory.http.routes.*;
import net.codestory.vote.gists.*;

public class VoteConfiguration implements Configuration {
  private final Gists gists;
  private final IndexResource indexResource;

  public VoteConfiguration() {
    gists = createGists();
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

  protected Gists createGists() {
    return new DemoGists();
  }

  public Gists getGists() {
    return gists;
  }

  public IndexResource getIndexResource() {
    return indexResource;
  }
}
