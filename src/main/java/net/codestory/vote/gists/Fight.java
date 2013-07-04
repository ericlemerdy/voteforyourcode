package net.codestory.vote.gists;

public class Fight {
  private final String uniqueId;
  private final Gist left;
  private final Gist right;

  public Fight(String uniqueId, Gist left, Gist right) {
    this.uniqueId = uniqueId;
    this.left = left;
    this.right = right;
  }

  public String uniqueId() {
    return uniqueId;
  }

  public int leftElo() {
    return left.rank().elo();
  }

  public int rightElo() {
    return right.rank().elo();
  }

  public Gist left() {
    return left;
  }

  public Gist right() {
    return right;
  }
}
