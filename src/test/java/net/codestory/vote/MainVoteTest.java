package net.codestory.vote;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.when;

import net.codestory.vote.gists.*;

import org.junit.*;

public class MainVoteTest extends AbstractWebTest {
  @Test
  public void show_candidates() {
    Gists gists = getInstance(Gists.class);
    when(gists.left()).thenReturn("dgageot/9895cbae5fbd70892d0d");
    when(gists.right()).thenReturn("dgageot/4718233");

    goTo("/");

    assertThat(title()).isEqualTo("Code-Story - Vote For Your Code");
    assertThat(find("h1").getText()).isEqualTo("Vote For Your Code");
    assertThat(find("#left").getText()).contains("module hello.World");
    assertThat(find("#right").getText()).contains("CodeStoryStatusTest");
  }
}
