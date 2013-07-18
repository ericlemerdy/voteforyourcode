package net.codestory.fight.ranking;

import net.codestory.fight.gists.*;
import net.codestory.fight.ranking.*;

import org.apache.commons.lang.*;

public class Fight {
  private final String uniqueId;
  private final Gist left;
  private final Gist right;

  public Fight(Gist left, Gist right) {
    this.uniqueId = createUniqueId();
    this.left = left;
    this.right = right;
  }

  private static String createUniqueId() {
    return RandomStringUtils.random(32, true, true);
  }

  public String uniqueId() {
    return uniqueId;
  }

  public int leftElo() {
    return left.rank().elo();
  }

  public int rightElo() {
    return right.rank().elo();
  }

  public String leftEloName() {
    return new RankName().name(leftElo());
  }

  public String rightEloName() {
    return new RankName().name(rightElo());
  }

  public Gist left() {
    return left;
  }

  public Gist right() {
    return right;
  }
}