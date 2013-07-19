package net.codestory.fight.gists;

import net.codestory.fight.ranking.*;

public class Gist {
  private final String name;
  private final String url;
  private final Rank rank;

  public Gist(String name, String url) {
    this.name = name;
    this.url = url;
    this.rank = new Rank();
  }

  public String name() {
    return name;
  }

  public Rank rank() {
    return rank;
  }

  public String url() {
    return url;
  }
}
