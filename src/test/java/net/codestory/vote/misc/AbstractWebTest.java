package net.codestory.vote.misc;

import static org.mockito.Mockito.*;

import java.lang.reflect.*;
import java.util.*;

import net.codestory.vote.*;
import net.codestory.vote.repository.*;

import org.fluentlenium.adapter.*;
import org.fluentlenium.adapter.util.*;
import org.junit.*;
import org.openqa.selenium.*;

@SharedDriver
public abstract class AbstractWebTest extends FluentTest {
  private VoteConfiguration configuration = new VoteConfiguration() {
    @Override
    protected VoteRepository createVoteRepository() {
      VoteRepository voteRepository = mock(VoteRepository.class);
      when(voteRepository.all()).thenReturn(Collections.emptyList());
      return voteRepository;
    }

    @Override
    protected Random createRandom() {
      return spy(super.createRandom());
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
