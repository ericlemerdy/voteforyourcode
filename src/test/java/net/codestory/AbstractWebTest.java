package net.codestory;

import static org.mockito.Mockito.*;

import java.lang.reflect.*;
import java.util.*;

import net.codestory.fight.votes.*;
import net.codestory.misc.*;

import org.fluentlenium.adapter.*;
import org.fluentlenium.adapter.util.*;
import org.junit.*;
import org.openqa.selenium.*;

@SharedDriver
public abstract class AbstractWebTest extends FluentTest {
  private static final Dimension DEFAULT_WINDOW_SIZE = new Dimension(1024, 768);

  private VoteConfiguration configuration = new VoteConfiguration() {
    @Override
    protected VoteRepository createVoteRepository(String uri) {
      VoteRepository voteRepository = spy(super.createVoteRepository(uri));
      doNothing().when(voteRepository).save(any(Vote.class));
      doReturn(Collections.emptyList()).when(voteRepository).all();
      return voteRepository;
    }

    @Override
    protected Random createRandom() {
      return spy(super.createRandom());
    }
  };

  @Rule
  public WebServerRule webServer = new WebServerRule(configuration) {
    @Override
    protected boolean devMode() {
      return false;
    }
  };

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

  protected AbstractWebTest() {
    setSnapshotPath("snapshots");
    setSnapshotMode(Mode.TAKE_SNAPSHOT_ON_FAIL);
  }

  @SuppressWarnings("unchecked")
  protected <T> T getInstance(Class<T> type) {
    for (Field field : configuration.getClass().getSuperclass().getDeclaredFields()) {
      if (field.getType() == type) {
        try {
          field.setAccessible(true);
          return (T) field.get(configuration);
        } catch (IllegalAccessException e) {
          throw new IllegalStateException("Unable to read bean of type " + type);
        }
      }
    }

    throw new IllegalArgumentException("Invalid type of bean " + type);
  }
}
