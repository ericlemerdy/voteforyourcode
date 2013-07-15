package net.codestory;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class TeamTest extends AbstractWebTest {
  @Test
  public void team() {
    goTo("/team/");

    assertThat(title()).isEqualTo("Code-Story Team");
  }
}
