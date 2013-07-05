package net.codestory.vote.captcha;

import java.io.*;

import net.codestory.http.filters.Filter;

import com.sun.net.httpserver.*;

public class ThrottleFilter implements Filter {
  private final QueryCounter queryCounter;

  public ThrottleFilter(QueryCounter queryCounter) {
    this.queryCounter = queryCounter;
  }

  @Override
  public boolean apply(HttpExchange exchange) throws IOException {
    String path = exchange.getRequestURI().getRawPath();
    if ("/".equals(path) || "/index".equals(path) || "/index.html".equals(path)) {
      String host = exchange.getRemoteAddress().getAddress().toString();

      long count = queryCounter.add(host);
      if (count > 30) {
        exchange.getResponseHeaders().add("Location", "/captcha");
        exchange.sendResponseHeaders(303, 0);
        return true;
      }
    }

    return false;
  }
}
