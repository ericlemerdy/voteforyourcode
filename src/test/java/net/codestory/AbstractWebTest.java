package net.codestory;

import static org.mockito.Mockito.*;

import java.util.*;

import net.codestory.fight.votes.*;
import net.codestory.http.injection.*;
import net.codestory.misc.*;

import org.fluentlenium.adapter.*;
import org.fluentlenium.adapter.util.*;
import org.junit.*;
import org.openqa.selenium.*;

@SharedDriver
public abstract class AbstractWebTest extends FluentTest {
  private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(1024, 768);

  private final Singletons singletons = new Singletons() {
    @Override
    protected <T> T postProcess(T instance) {
      T spy = spy(instance);

      if (spy instanceof VoteRepository) {
        VoteRepository voteRepository = (VoteRepository) spy;
        doNothing().when(voteRepository).save(any(Vote.class));
        doReturn(Collections.emptyList()).when(voteRepository).all();
      }

      return spy;
    }
  };

  @Rule
  public WebServerRule webServer = new WebServerRule(new WebConfiguration(singletons)) {
    @Override
    protected boolean devMode() {
      return false;
    }
  };

  protected AbstractWebTest() {
    setSnapshotPath("snapshots");
    setSnapshotMode(Mode.TAKE_SNAPSHOT_ON_FAIL);
  }

  @Override
  public WebDriver getDefaultDriver() {
    WebDriver driver = new PhantomJsDownloader().createDriver();
    driver.manage().window().setSize(DEFAULT_WINDOW_SIZE);
    return driver;
  }

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  @SuppressWarnings("unchecked")
  protected <T> T getInstance(Class<T> type) {
    return singletons.get(type);
  }
}
