package net.codestory.fight.captcha;

import java.io.*;

import net.codestory.fight.captcha.*;
import net.codestory.http.filters.Filter;

import com.sun.net.httpserver.*;

public class ThrottleFilter implements Filter {
  private final QueryCounter queryCounter;

  public ThrottleFilter(QueryCounter queryCounter) {
    this.queryCounter = queryCounter;
  }

  @Override
  public boolean apply(String uri, HttpExchange exchange) throws IOException {
    if (uri.endsWith("/captcha") || !uri.contains("/fight/")) {
      return false;
    }

    String host = exchange.getRemoteAddress().getAddress().toString();
    if (!queryCounter.quotaReached(host)) {
      return false;
    }

    exchange.getResponseHeaders().add("Location", "/fight/captcha");
    exchange.sendResponseHeaders(303, 0);
    return true;
  }
}
