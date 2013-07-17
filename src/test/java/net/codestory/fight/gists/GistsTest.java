package net.codestory.fight.gists;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class GistsTest {
  @Test
  public void find_by_id() {
    Gists gists = new Gists();

    assertThat(gists.findByName("FooBarQix Java Simple").url()).isEqualTo("https://gist.github.com/MeddahJ/1374633.js");
    assertThat(gists.findByName("FooBarQix C").url()).isEqualTo("http://gist-it.appspot.com/github/framiere/FooBarQix/blob/master/src/main/c/foobarqix.c");
  }
}
