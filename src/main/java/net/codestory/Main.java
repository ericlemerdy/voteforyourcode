package net.codestory;

import net.codestory.http.*;

public class Main {
  public static void main(String[] args) {
    int port = Integer.parseInt(System.getProperty("app.port", "8181"));

    new Main().start(port);
  }

  private void start(int port) {
    new WebServer()
        .configure(new VoteConfiguration())
        .start(port);
  }
}
