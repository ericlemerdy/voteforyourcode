package net.codestory;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class BlogTest extends AbstractWebTest {
  @Test
  public void blog() {
    goTo("/blog/");

    assertThat(title()).isEqualTo("Code-Story Blog");
    assertThat(text(".season3")).contains("Season S03 - September 2013\n\" Harder, Better, Faster, Stronger. \"\nThe one that starts in September\nS03E01");
  }
}
