package net.codestory.vote;

import static org.fest.assertions.Assertions.*;

import net.codestory.vote.gists.*;

import org.junit.*;

public class MainVoteTest extends AbstractWebTest {
  @Test
  public void display_home() {
    goTo("/");

    assertThat(title()).isEqualTo("Code-Story - Vote For Your Code");
    assertThat(find("h1").getText()).isEqualTo("Vote For Your Code");
  }

  @Test
  public void show_candidates() {
    goTo("/");

    assertThat(find("#left").getText()).contains("module hello.World");
    assertThat(find("#right").getText()).contains("CodeStoryStatusTest");
  }
}
