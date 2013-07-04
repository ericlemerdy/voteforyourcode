package net.codestory.vote.gists;

public class Gists {
  private final Gist[] gists;

  public Gists(Gist... gists) {
    this.gists = gists;
  }

  public int size() {
    return gists.length;
  }

  public Gist get(int index) {
    return gists[index];
  }
}
