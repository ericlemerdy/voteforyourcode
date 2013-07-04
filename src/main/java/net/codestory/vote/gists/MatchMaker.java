package net.codestory.vote.gists;

import java.util.*;

public class MatchMaker {
  private final Random random;
  private final Gists gists;

  public MatchMaker(Random random, Gists gists) {
    this.random = random;
    this.gists = gists;
  }

  public Fight randomFight() {
    Gist left = randomGist();
    Gist right = randomGist();
    while (left == right) {
      right = randomGist();
    }
    return new Fight(left, right);
  }

  private Gist randomGist() {
    return gists.get(random.nextInt(gists.size()));
  }
}
