package net.codestory.vote.gists;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;

public class GistsTest {
  Gists gists = new Gists(new Gist(0, "url1"), new Gist(1, "url2"), new Gist(2, "url3"));

  @Test
  public void initial_ranking() {
    assertThat(gists.score(0)).isEqualTo(1200);
    assertThat(gists.score(1)).isEqualTo(1200);
    assertThat(gists.score(2)).isEqualTo(1200);
  }

  @Test
  public void ranking_after_one_match() {
    gists.match(0, 1);

    assertThat(gists.score(0)).isEqualTo(1212);
    assertThat(gists.score(1)).isEqualTo(1187);
    assertThat(gists.score(2)).isEqualTo(1200);
  }

  @Test
  public void ranking_after_two_matches() {
    gists.match(0, 1);
    gists.match(0, 1);

    assertThat(gists.score(0)).isEqualTo(1224);
    assertThat(gists.score(1)).isEqualTo(1175);
  }

  @Test
  public void tie_after_two_matches() {
    gists.match(0, 1);
    gists.match(1, 0);

    assertThat(gists.score(0)).isEqualTo(1199);
    assertThat(gists.score(1)).isEqualTo(1200);
  }

  @Test
  public void tie_after_hundred_of_matches() {
    for (int i = 0; i < 100; i++) {
      gists.match(0, 1);
    }

    assertThat(gists.score(0)).isEqualTo(1400);
    assertThat(gists.score(1)).isEqualTo(999);
  }
}
