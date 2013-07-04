package net.codestory.vote;

import static org.mockito.Mockito.*;

import net.codestory.vote.gists.*;
import net.codestory.vote.misc.*;

import org.fluentlenium.adapter.*;
import org.fluentlenium.adapter.util.*;
import org.junit.*;
import org.openqa.selenium.*;

@SharedDriver
public abstract class AbstractWebTest extends FluentTest {
  private VoteConfiguration configuration = new VoteConfiguration() {
    @Override
    protected Gists createGists() {
      return spy(super.createGists());
    }
  };

  @Rule
  public WebServerRule webServer = new WebServerRule(configuration);

  @Override
  public WebDriver getDefaultDriver() {
    return new PhantomJsDownloader().createDriver();
  }

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  protected VoteConfiguration getConfiguration() {
    return configuration;
  }
}
