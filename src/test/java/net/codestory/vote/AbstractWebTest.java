package net.codestory.vote;

import net.codestory.vote.gists.*;
import net.codestory.vote.misc.*;

import org.fluentlenium.adapter.*;
import org.fluentlenium.adapter.util.*;
import org.junit.*;
import org.openqa.selenium.*;

import com.google.inject.*;

@SharedDriver
public abstract class AbstractWebTest extends FluentTest {
  @Rule
  public WebServerRule webServer = new WebServerRule(new VoteConfiguration(new AbstractModule() {
    @Override
    protected void configure() {
      bind(Gists.class).toInstance(new Gists() {
        public String left() {
          return "dgageot/9895cbae5fbd70892d0d";
        }

        public String right() {
          return "dgageot/4718233";
        }
      });
    }
  }));

  @Override
  public WebDriver getDefaultDriver() {
    return new PhantomJsDownloader().createDriver();
  }

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }
}
