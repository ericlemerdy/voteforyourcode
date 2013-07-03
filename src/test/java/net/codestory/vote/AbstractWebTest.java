package net.codestory.vote;

import static org.mockito.Mockito.mock;

import net.codestory.vote.gists.*;
import net.codestory.vote.misc.*;

import org.fluentlenium.adapter.*;
import org.fluentlenium.adapter.util.*;
import org.junit.*;
import org.mockito.*;
import org.openqa.selenium.*;

import com.google.inject.*;
import com.google.inject.util.*;

@SharedDriver
public abstract class AbstractWebTest extends FluentTest {
  private final Injector injector = Guice.createInjector(Modules.override(new MainVote.MainVoteModule()).with(new AbstractModule() {
    @Override
    protected void configure() {
      bind(Gists.class).toInstance(mock(Gists.class));
    }
  }));

  @Rule
  public WebServerRule webServer = new WebServerRule(new VoteConfiguration(injector));

  @Override
  public WebDriver getDefaultDriver() {
    return new PhantomJsDownloader().createDriver();
  }

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  protected <T> T getInstance(Class<T> type) {
    return injector.getInstance(type);
  }
}
