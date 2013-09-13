package net.codestory;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import org.junit.Test;
import org.rometools.fetcher.impl.HttpURLFeedFetcher;

import java.net.URL;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

public class BlogRssTest extends AbstractWebTest {
  @Test
  public void rss_feed_should_be_parsable() throws Exception {
    HttpURLFeedFetcher feedFetcher = new HttpURLFeedFetcher();
    SyndFeed feed = feedFetcher.retrieveFeed(new URL(getDefaultBaseUrl() + "/blog/rss.xml"));

    assertThat(feed.getTitle()).isNotNull();
    assertThat(feed.getEntries()).isNotEmpty();

    for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
      assertThat(entry.getTitle()).isNotEmpty();
      assertThat(entry.getLink()).isNotEmpty();
    }
  }
}
