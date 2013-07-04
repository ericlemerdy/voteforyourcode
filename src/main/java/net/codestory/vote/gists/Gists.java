package net.codestory.vote.gists;

public class Gists {
  private final Gist[] gists;

  public Gists(Gist... gists) {
    this.gists = gists;
  }

  public Gist findByName(String name) {
    for (Gist gist : gists) {
      if (gist.name().equals(name)) {
        return gist;
      }
    }
    return null;
  }

  public int size() {
    return gists.length;
  }

  public Gist get(int index) {
    return gists[index];
  }
}
