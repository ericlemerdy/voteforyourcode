package net.codestory.vote;

import static org.fest.assertions.Assertions.*;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.mockito.Mockito.*;

import java.util.*;

import net.codestory.vote.gists.*;
import net.codestory.vote.misc.*;

import org.junit.*;

public class VoteTest extends AbstractWebTest {
  @Test
  public void fight() {
    goTo("/fight");

    assertThat(title()).isEqualTo("{{Code}} Fight by Code-Story");
  }

  @Test
  public void candidates() {
    Random random = getInstance(Random.class);
    Gists gists = getInstance(Gists.class);
    when(random.nextInt(gists.size())).thenReturn(0, 1);

    goTo("/fight");

    assertThat(find("#left h2").getText()).contains("FooBarQix Java");
    assertThat(find("#left").getText()).contains("byDivisor");

    assertThat(find("#right h2").getText()).contains("FooBarQix Groovy Tweetable");
    assertThat(find("#right").getText()).contains("A coller directement");
  }

  @Test
  public void vote() {
    Random random = getInstance(Random.class);
    Gists gists = getInstance(Gists.class);
    when(random.nextInt(gists.size())).thenReturn(0, 1, 0, 1);

    goTo("/fight");

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
