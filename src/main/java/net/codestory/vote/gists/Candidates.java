package net.codestory.vote.gists;

public class Candidates {
  private final String left;
  private final String right;

  public Candidates(String left, String right) {
    this.left = left;
    this.right = right;
  }

  public String left() {
    return left;
  }

  public String right() {
    return right;
  }
}
