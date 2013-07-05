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
  public boolean apply(String uri, HttpExchange exchange) throws IOException {
    if ("/".equals(uri) || "/index".equals(uri) || "/index.html".equals(uri)) {
      String host = exchange.getRemoteAddress().getAddress().toString();

      if (queryCounter.quotaReached(host)) {
        exchange.getResponseHeaders().add("Location", "/captcha");
        exchange.sendResponseHeaders(303, 0);
        return true;
      }
    }

    return false;
  }
}
