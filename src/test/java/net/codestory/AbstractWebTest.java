package net.codestory;

import static org.mockito.Mockito.*;

import net.codestory.fight.votes.*;
import net.codestory.http.injection.*;
import net.codestory.misc.*;

import org.fluentlenium.adapter.*;
import org.fluentlenium.adapter.util.*;
import org.junit.*;
import org.openqa.selenium.*;

@SharedDriver
public abstract class AbstractWebTest extends FluentTest {
  private final Singletons singletons = new Singletons() {
    @Override
    protected <T> T postProcess(T instance) {
      if (instance instanceof VoteRepository) {
        return (T) mock(VoteRepository.class);
      }
      return spy(instance);
    }
  };

  @Rule
  public WebServerRule webServer = new WebServerRule(new WebConfiguration(singletons)) {
    @Override
    protected boolean devMode() {
      return false;
    }
  };

  AbstractWebTest() {
    setSnapshotPath("snapshots");
    setSnapshotMode(Mode.TAKE_SNAPSHOT_ON_FAIL);
  }

  @Override
  public WebDriver getDefaultDriver() {
    WebDriver driver = new PhantomJsDownloader().createDriver();
    driver.manage().window().setSize(new Dimension(1024, 768));
    return driver;
  }

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  @SuppressWarnings("unchecked")
  <T> T getInstance(Class<T> type) {
    return singletons.get(type);
  }
}
