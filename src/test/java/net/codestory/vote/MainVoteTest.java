package net.codestory.vote;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class MainVoteTest extends AbstractWebTest {
  @Test
  public void test_http() {
    goTo("/");

    assertThat(title()).isEqualTo("Code-Story - Vote For Your Code");
    assertThat(find("body").getText()).isEqualTo("Vote For Your Code");
  }
}
