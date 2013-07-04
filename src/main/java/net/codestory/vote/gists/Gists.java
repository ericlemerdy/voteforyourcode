package net.codestory.vote.gists;

public class Gists {
  private final Gist[] gists;

  public Gists(Gist... gists) {
    this.gists = gists;
  }

  public int size() {
    return gists.length;
  }

  public void match(int winner, int looser) {
    match(rank(winner), rank(looser));
  }

  public synchronized int score(int index) {
    return (int) rank(index).elo();
  }

  private Rank rank(int index) {
    return get(index).rank();
  }

  private synchronized void match(Rank winner, Rank looser) {
    float eloWinner = winner.elo();
    float eloLooser = looser.elo();

    winner.win(eloLooser);
    looser.loose(eloWinner);
  }

  Gist get(int index) {
    return gists[index];
  }
}
