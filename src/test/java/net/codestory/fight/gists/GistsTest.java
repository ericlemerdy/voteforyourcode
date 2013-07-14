package net.codestory.fight.gists;

import static org.fest.assertions.Assertions.*;

import net.codestory.fight.gists.*;

import org.junit.*;

public class GistsTest {
  @Test
  public void find_by_id() {
    Gists gists = new Gists(new Gist("1", "url1"), new Gist("2", "url2"));

    assertThat(gists.findByName("1").url()).isEqualTo("url1");
    assertThat(gists.findByName("2").url()).isEqualTo("url2");
  }
}
