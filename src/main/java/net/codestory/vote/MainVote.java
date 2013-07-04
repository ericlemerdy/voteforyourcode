package net.codestory.vote;

import net.codestory.http.*;

public class MainVote {
  public static void main(String[] args) {
    int port = Integer.parseInt(System.getProperty("app.port", "8181"));

    new MainVote().start(port);
  }

  private void start(int port) {
    new WebServer()
        .configure(new VoteConfiguration())
        .start(port);
  }
}
