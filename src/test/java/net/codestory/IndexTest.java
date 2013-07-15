package net.codestory;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class IndexTest extends AbstractWebTest {
  @Test
  public void redirect_to_fight() {
    goTo("/");

    assertThat(title()).isEqualTo("{{Code}} Fight by Code-Story");
  }
}
