package net.codestory.fight.gists;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class GistTest {
  @Test
  public void content() {
    Gist gist = new Gist("FooBarQix Ioke", "");

    assertThat(gist.content()).contains("toRational");
  }
}
