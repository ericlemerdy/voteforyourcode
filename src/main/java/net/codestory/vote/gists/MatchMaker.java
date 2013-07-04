package net.codestory.vote.gists;

import java.util.*;
import java.util.concurrent.*;

import org.apache.commons.lang.*;

public class MatchMaker {
  private final Random random;
  private final Gists gists;
  private final Map<String, Fight> fightsById;

  public MatchMaker(Random random, Gists gists) {
    this.random = random;
    this.gists = gists;
    fightsById = new ConcurrentHashMap<>();
  }

  public Fight randomFight() {
    String uniqueId = uniqueId();

    int nbGists = gists.size();
    int left = random.nextInt(nbGists);
    int right = random.nextInt(nbGists);
    while (left == right) {
      right = random.nextInt(nbGists);
    }

    Fight fight = new Fight(uniqueId, gists.get(left), gists.get(right));
    fightsById.put(uniqueId, fight);

    return fight;
  }

  public void leftWins(String uniqueId) {
    Fight fight = fightsById.remove(uniqueId);
    if (null != fight) {
      fight.left().rank().beats(fight.right().rank());
    }
  }

  public void rightWins(String uniqueId) {
    Fight fight = fightsById.remove(uniqueId);
    if (null != fight) {
      fight.right().rank().beats(fight.left().rank());
    }
  }

  private static String uniqueId() {
    return RandomStringUtils.random(32, true, true);
  }
}
