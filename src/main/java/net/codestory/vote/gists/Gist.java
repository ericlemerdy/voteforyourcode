package net.codestory.vote.gists;

public class Gist {
  private final int id;
  private final String url;
  private final Rank rank;

  public Gist(int id, String url) {
    this.id = id;
    this.url = url;
    this.rank = new Rank();
  }

  public int id() {
    return id;
  }

  public Rank rank() {
    return rank;
  }

  public int elo() {
    return (int) rank.elo();
  }

  public String url() {
    return url;
  }
}
