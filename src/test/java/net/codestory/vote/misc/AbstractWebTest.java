package net.codestory.vote.misc;

import static org.mockito.Mockito.*;

import java.util.*;

import net.codestory.vote.*;
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
    protected Gists createGists(Random random) {
      return spy(super.createGists(random));
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
