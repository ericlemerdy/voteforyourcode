package net.codestory.vote.captcha;

import static java.util.concurrent.TimeUnit.*;

import java.util.concurrent.atomic.*;

import com.google.common.cache.*;

public class QueryCounter {
  private LoadingCache<String, AtomicLong> queriesPerHost;

  public QueryCounter() {
    this.queriesPerHost = CacheBuilder
        .newBuilder()
        .expireAfterWrite(1, MINUTES)
        .build(new CacheLoader<String, AtomicLong>() {
          @Override
          public AtomicLong load(String host) {
            return new AtomicLong(0L);
          }
        });
  }

  public long add(String host) {
    return queriesPerHost.getUnchecked(host).incrementAndGet();
  }
}
