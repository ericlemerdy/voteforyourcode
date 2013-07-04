package net.codestory.vote.ranking;

import java.util.*;

public class Votes {
  public static final int MAX = 10;

  private final Map<Integer, Rank> ranks;

  public Votes() {
    ranks = new HashMap<>();
    for (int i = 0; i < MAX; i++) {
      ranks.put(i, new Rank());
    }
  }

  public synchronized int score(int index) {
    return (int) rank(index).elo();
  }

  public void match(int winner, int looser) {
    match(rank(winner), rank(looser));
  }

  private Rank rank(int index) {
    return ranks.get(index);
  }

  private synchronized void match(Rank winner, Rank looser) {
    float eloWinner = winner.elo();
    float eloLooser = looser.elo();

    winner.win(eloLooser);
    looser.loose(eloWinner);
  }
}
