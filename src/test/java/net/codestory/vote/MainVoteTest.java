package net.codestory.vote;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import net.codestory.vote.gists.*;
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
    Gists gists = getInstance(Gists.class);
    doReturn(new Candidates("dgageot/9895cbae5fbd70892d0d", "dgageot/4718233")).when(gists).candidates();

    goTo("/");

    assertThat(find("#left").getText()).contains("module hello.World");
    assertThat(find("#right").getText()).contains("CodeStoryStatusTest");
  }
}
