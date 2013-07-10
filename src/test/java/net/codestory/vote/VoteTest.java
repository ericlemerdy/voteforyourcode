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

    assertThat(title()).isEqualTo("{{Code}} Fight by Code-Story");
  }

  @Test
  public void candidates() {
    Random random = getInstance(Random.class);
    when(random.nextInt(44)).thenReturn(0, 1);

    goTo("/");

    assertThat(find("#left h2").getText()).contains("FooBarQix Java");
    assertThat(find("#left").getText()).contains("net.ericlefevre");

    assertThat(find("#right h2").getText()).contains("FooBarQix Clojure");
    assertThat(find("#right").getText()).contains("(def number)");
  }

  @Test
  public void vote() {
    Random random = getInstance(Random.class);
    when(random.nextInt(44)).thenReturn(0, 1, 0, 1);

    goTo("/");

    assertThat(find("#left .score").getText()).contains("1200");
    assertThat(find("#right .score").getText()).contains("1200");

    click("#left a", withText("I prefer this one!"));

    assertThat(find("#left .score").getText()).contains("1212");
    assertThat(find("#right .score").getText()).contains("1187");
  }

  @Test
  public void captcha() {
    goTo("/captcha");

    assertThat(find("#captcha").getText()).contains("- Are you a Robot? -");
  }
}
