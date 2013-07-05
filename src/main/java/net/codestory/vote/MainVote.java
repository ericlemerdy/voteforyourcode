package net.codestory.vote;

import java.io.*;

import net.codestory.http.*;
import net.codestory.vote.captcha.*;

import com.sun.net.httpserver.*;

public class MainVote {
  public static void main(String[] args) {
    int port = Integer.parseInt(System.getProperty("app.port", "8181"));

    new MainVote().start(port);
  }

  private void start(int port) {
    QueryCounter queryCounter = new QueryCounter();

    WebServer webServer = new WebServer() {
      @Override
      protected void applyRoutes(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getRawPath();
        if ("/".equals(path) || "/index".equals(path) || "/index.html".equals(path)) {
          String host = exchange.getRemoteAddress().getAddress().toString();

          long count = queryCounter.add(host);
          if (count > 30) {
            exchange.getResponseHeaders().add("Location", "/captcha");
            exchange.sendResponseHeaders(303, 0);
            return;
          }
        }

        super.applyRoutes(exchange);
      }
    };
    webServer.configure(new VoteConfiguration());
    webServer.start(port);
  }
}
