package net.codestory.vote.captcha;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import com.google.common.cache.*;

public class QueryCounter {
  private final LoadingCache<String, AtomicLong> lastMinuteCountPerHost;
  private final LoadingCache<String, AtomicLong> lastDayCountPerHost;

  public QueryCounter() {
    this.lastMinuteCountPerHost = createCountPerHost(1, MINUTES);
    this.lastDayCountPerHost = createCountPerHost(1, DAYS);
  }

  private static LoadingCache<String, AtomicLong> createCountPerHost(long duration, TimeUnit unit) {
    return CacheBuilder
        .newBuilder()
        .expireAfterWrite(duration, unit)
        .build(new CacheLoader<String, AtomicLong>() {
          @Override
          public AtomicLong load(String host) {
            return new AtomicLong(0L);
          }
        });
  }

  public boolean quotaReached(String host) {
    long countLastMinute = lastMinuteCountPerHost.getUnchecked(host).incrementAndGet();
    long countLastDay = lastDayCountPerHost.getUnchecked(host).incrementAndGet();

    return (countLastMinute > 30) || (countLastDay > 1000);
  }
}
