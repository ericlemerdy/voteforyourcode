package net.codestory;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class BlogTest extends AbstractWebTest {
  @Test
  public void blog() {
    goTo("/blog/");

    assertThat(title()).isEqualTo("Code-Story Blog");
    assertThat(text(".season3")).contains("Season S03 - September 2013\n" +
      "\" Harder, Better, Faster, Stronger. \"\n" +
      "The one that starts in September\n" +
      "S03E00\n" +
      "The one in the elevator\n" +
      "S03E01");
  }
}
