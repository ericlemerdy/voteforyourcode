package net.codestory.fight.captcha;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.atomic.*;

import com.google.common.cache.*;

public class QueryCounter {
  private final LoadingCache<String, AtomicLong> countPerHostMinute;
  private final LoadingCache<String, AtomicLong> countPerHostDay;

  public QueryCounter() {
    this.countPerHostMinute = CacheBuilder.newBuilder().expireAfterWrite(1, MINUTES).build(CacheLoader.from(() -> new AtomicLong()));
    this.countPerHostDay = CacheBuilder.newBuilder().expireAfterWrite(1, DAYS).build(CacheLoader.from(() -> new AtomicLong()));
  }

  public boolean quotaReached(String host) {
    long countLastMinute = countPerHostMinute.getUnchecked(host).incrementAndGet();
    if (countLastMinute > 30) {
      return true;
    }

    long countLastDay = countPerHostDay.getUnchecked(host).incrementAndGet();
    if (countLastDay > 1000) {
      return true;
    }

    return false;
  }
}
