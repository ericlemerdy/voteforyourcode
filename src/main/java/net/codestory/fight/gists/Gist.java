package net.codestory.fight.gists;

import net.codestory.fight.ranking.*;

public class Gist {
  private String name;
  private String url;
  private transient Rank rank;

  public Gist() {
    this.rank = new Rank();
  }

  public Gist(String name, String url) {
    this();
    this.name = name;
    this.url = url;
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
