package net.codestory.vote.gists;

import javax.inject.*;

@Singleton
public class DemoGists implements Gists {
  @Override
  public String left() {
    return "dgageot/9895cbae5fbd70892d0d";
  }

  @Override
  public String right() {
    return "dgageot/9895cbae5fbd70892d0d";
  }
}
