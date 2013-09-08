package net.codestory.fight.captcha;

import java.io.*;

import net.codestory.fight.captcha.*;
import net.codestory.http.filters.*;
import net.codestory.http.filters.Filter;

import com.sun.net.httpserver.*;

public class ThrottleFilter implements Filter {
  private final QueryCounter queryCounter;

  public ThrottleFilter(QueryCounter queryCounter) {
    this.queryCounter = queryCounter;
  }

  @Override
  public Match apply(String uri, HttpExchange exchange) throws IOException {
    if (uri.endsWith("/captcha") || !uri.contains("/fight/")) {
      return Match.WRONG_URL;
    }

    String host = exchange.getRemoteAddress().getAddress().toString();
    if (!queryCounter.quotaReached(host)) {
      return Match.WRONG_URL;
    }

    exchange.getResponseHeaders().add("Location", "/fight/captcha");
    exchange.sendResponseHeaders(303, 0);
    return Match.OK;
  }
}
