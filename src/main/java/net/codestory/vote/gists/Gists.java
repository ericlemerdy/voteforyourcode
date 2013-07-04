package net.codestory.vote.gists;

import java.util.*;

public class Gists {
  private final Random random;
  private final Gist[] gists;

  public Gists(Random random, Gist... gists) {
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

  public void match(int winner, int looser) {
    match(rank(winner), rank(looser));
  }

  public synchronized int score(int index) {
    return (int) rank(index).elo();
  }

  private Gist randomGist() {
    return gists[random.nextInt(gists.length)];
  }

  private Rank rank(int index) {
    return gists[index].rank();
  }

  private synchronized void match(Rank winner, Rank looser) {
    float eloWinner = winner.elo();
    float eloLooser = looser.elo();

    winner.win(eloLooser);
    looser.loose(eloWinner);
  }
}
