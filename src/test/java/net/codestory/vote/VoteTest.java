package net.codestory.vote;

import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.mockito.Mockito.*;

import java.util.*;

import net.codestory.vote.misc.*;

import org.junit.*;

public class VoteTest extends AbstractWebTest {
  @Test
  public void homepage() {
    goTo("/");

    assertThat(title()).isEqualTo("Code-Story - Vote For The {{ Code }}");
    assertThat(find("h1").getText()).isEqualTo("Vote For The {{ Code }}");
  }

  @Test
  public void candidates() {
    Random random = getInstance(Random.class);
    when(random.nextInt(7)).thenReturn(0, 1);

    goTo("/");

    assertThat(find("#left h2").getText()).contains("FooBarGolo");
    assertThat(find("#left").getText()).contains("module hello.World");

    assertThat(find("#right h2").getText()).contains("CodeStoryStatusTest");
    assertThat(find("#right").getText()).contains("extends PhantomJsTest");
  }

  @Test
  public void vote() throws InterruptedException {
    Random random = getInstance(Random.class);
    when(random.nextInt(7)).thenReturn(0, 1, 0, 1);

    goTo("/");

    assertThat(find("#left .score").getText()).contains("1200");
    assertThat(find("#right .score").getText()).contains("1200");

    click("#left a", withText("I prefer this one!"));

    assertThat(find("#left .score").getText()).contains("1212");
    assertThat(find("#right .score").getText()).contains("1187");
  }
}
