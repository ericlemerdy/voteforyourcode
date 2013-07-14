package net.codestory.fight.gists;

import java.util.*;
import java.util.concurrent.*;

import net.codestory.fight.gists.*;

public class Gists implements Iterable<Gist> {
  private final Gist[] gists;
  private final Map<String, Gist> byName;

  public Gists(Gist... gists) {
    this.gists = gists;
    this.byName = new ConcurrentHashMap<>();
    for (Gist gist : gists) {
      byName.put(gist.name(), gist);
    }
  }

  public Gist findByName(String name) {
    return byName.get(name);
  }

  public int size() {
    return gists.length;
  }

  public Gist get(int index) {
    return gists[index];
  }

  @Override
  public Iterator<Gist> iterator() {
    return Arrays.asList(gists).iterator();
  }
}
