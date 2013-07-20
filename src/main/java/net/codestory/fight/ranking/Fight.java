package net.codestory.fight.ranking;

import java.math.*;
import java.security.*;
import java.util.*;

import net.codestory.fight.gists.*;

public class Fight {
  private static final Random RANDOM = new SecureRandom();

  private final String uniqueId;
  private final Gist left;
  private final Gist right;

  public Fight(Gist left, Gist right) {
    this.uniqueId = createUniqueId();
    this.left = left;
    this.right = right;
  }

  private static String createUniqueId() {
    synchronized (RANDOM) {
      return new BigInteger(130, RANDOM).toString(32);
    }
  }

  public String uniqueId() {
    return uniqueId;
  }

  public Gist left() {
    return left;
  }

  public Gist right() {
    return right;
  }
}
