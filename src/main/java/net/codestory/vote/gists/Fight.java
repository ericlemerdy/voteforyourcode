package net.codestory.vote.gists;

public class Fight {
  private final Gist left;
  private final Gist right;

  public Fight(Gist left, Gist right) {
    this.left = left;
    this.right = right;
  }

  public Gist left() {
    return left;
  }

  public Gist right() {
    return right;
  }
}
