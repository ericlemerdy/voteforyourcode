package net.codestory.vote.gists;

import java.util.*;

public class DemoGists implements Gists {
  private final Random random;
  private final List<String> gists;

  public DemoGists(Random random) {
    this.random = random;
    this.gists = availableGists();
  }

  private static List<String> availableGists() {
    List<String> gists = new ArrayList<>();
    gists.add("dgageot/9895cbae5fbd70892d0d");
    gists.add("dgageot/4718233");
    return gists;
  }

  private String randomGist() {
    return get(random.nextInt(gists.size()));
  }

  public String get(int index) {
    return gists.get(index);
  }

  @Override
  public Candidates candidates() {
    String left = randomGist();
    String right = randomGist();
    while (left.equals(right)) {
      right = randomGist();
    }

    return new Candidates(left, right);
  }
}
