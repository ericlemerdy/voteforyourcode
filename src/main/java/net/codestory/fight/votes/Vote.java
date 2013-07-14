package net.codestory.fight.votes;

public class Vote {
  public String winner;
  public String looser;

  public Vote() {
  }

  public Vote(String winner, String looser) {
    this.winner = winner;
    this.looser = looser;
  }
}
