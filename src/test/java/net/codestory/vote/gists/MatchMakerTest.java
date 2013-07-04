package net.codestory.vote.gists;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;

public class MatchMakerTest {
  Random random = mock(Random.class);

  MatchMaker matchMaker = new MatchMaker(random, new Gists(new Gist(0, "url1"), new Gist(1, "url2")));

  @Test
  public void random_candidates() {
    when(random.nextInt(2)).thenReturn(0, 1);

    Fight fight = matchMaker.randomFight();

    assertThat(fight.left().url()).isEqualTo("url1");
    assertThat(fight.right().url()).isEqualTo("url2");
  }

  @Test
  public void no_doubles() {
    when(random.nextInt(2)).thenReturn(1, 1, 1, 0);

    Fight fight = matchMaker.randomFight();

    assertThat(fight.left()).isNotSameAs(fight.right());
  }
}
