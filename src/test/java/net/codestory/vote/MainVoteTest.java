package net.codestory.vote;

import static org.fest.assertions.Assertions.*;

import net.codestory.vote.misc.*;

import org.fluentlenium.adapter.*;
import org.fluentlenium.adapter.util.*;
import org.junit.*;
import org.openqa.selenium.*;

@SharedDriver
public class MainVoteTest extends FluentTest {
  @Rule
  public WebServerRule webServer = new WebServerRule(new VoteConfiguration());

  @Override
  public WebDriver getDefaultDriver() {
    return new PhantomJsDownloader().createDriver();
  }

  @Override
  public String getDefaultBaseUrl() {
    return "http://localhost:" + webServer.port();
  }

  @Test
  public void test_http() {
    goTo("/");

    assertThat(find("body").getText()).isEqualTo("Hello");
  }
}
