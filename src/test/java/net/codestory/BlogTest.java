package net.codestory;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class BlogTest extends AbstractWebTest {
  @Test
  public void blog() {
    goTo("/blog/");

    assertThat(title()).isEqualTo("Code-Story Blog");
  }
}
