package net.codestory.vote;

import net.codestory.http.*;

public class MainVote {
  public static void main(String[] args) {
    new MainVote().start(8181);
  }

  private void start(int port) {
    new WebServer()
        .configure(new VoteConfiguration())
        .start(port);
  }
}
