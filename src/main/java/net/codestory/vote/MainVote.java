package net.codestory.vote;

import net.codestory.http.*;

import com.sun.net.httpserver.*;

public class MainVote {
  public static void main(String[] args) {
    int port = Integer.parseInt(System.getProperty("app.port", "8181"));

    new MainVote().start(port);
  }

  private void start(int port) {
    WebServer webServer = new WebServer() {
      @Override
      protected void onRequest(HttpExchange exchange) {
        //System.out.println(exchange.getRemoteAddress());
        super.onRequest(exchange);
      }
    };
    webServer.configure(new VoteConfiguration());
    webServer.start(port);
  }
}
