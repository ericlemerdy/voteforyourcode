package net.codestory.vote;

import net.codestory.http.*;

public class MainVote {
  public static void main(String[] args) {
    new MainVote().start(8181);
  }

  private void start(int port) {
    WebServer web = new WebServer();
    web.configure(new VoteConfiguration());
    web.start(port);
  }
}
