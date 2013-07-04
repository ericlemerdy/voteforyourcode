package net.codestory.vote.repository;

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
