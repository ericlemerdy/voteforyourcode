package net.codestory.vote;

import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.mockito.Mockito.*;

import java.util.*;

import net.codestory.vote.misc.*;

import org.junit.*;

public class MainVoteTest extends AbstractWebTest {
  @Test
  public void homepage() {
    goTo("/");

    assertThat(title()).isEqualTo("Code-Story - Vote For Your Code");
    assertThat(find("h1").getText()).isEqualTo("Vote For Your Code");
  }

  @Test
  public void candidates() {
    Random random = getInstance(Random.class);
    when(random.nextInt(2)).thenReturn(0, 1);

    goTo("/");

    assertThat(find("#left .name").getText()).contains("FooBarGolo");
    assertThat(find("#left").getText()).contains("module hello.World");

    assertThat(find("#right .name").getText()).contains("CodeStoryStatusTest");
    assertThat(find("#right").getText()).contains("extends PhantomJsTest");
  }

  @Test
  public void vote() throws InterruptedException {
    Random random = getInstance(Random.class);
    when(random.nextInt(2)).thenReturn(0, 1, 0, 1);

    goTo("/");

    assertThat(find("#left .score").getText()).isEqualTo("1200");
    assertThat(find("#right .score").getText()).isEqualTo("1200");

    click("#left a", withText("Vote"));

    assertThat(find("#left .score").getText()).isEqualTo("1212");
    assertThat(find("#right .score").getText()).isEqualTo("1187");
  }
}
