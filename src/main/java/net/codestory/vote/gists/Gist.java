package net.codestory.vote.gists;

public class Gist {
  private final String url;
  private final Rank rank;

  public Gist(String url) {
    this.url = url;
    this.rank = new Rank();
  }

  public Rank rank() {
    return rank;
  }

  public String url() {
    return url;
  }
}
