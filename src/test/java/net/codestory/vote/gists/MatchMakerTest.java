package net.codestory.vote.gists;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.*;

public class MatchMakerTest {
  Random random = spy(new Random());

  MatchMaker matchMaker = new MatchMaker(random, new Gists(new Gist("url1"), new Gist("url2")));

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

  @Test
  public void uniqueId() {
    Fight fight = matchMaker.randomFight();

    assertThat(fight.uniqueId()).isNotEmpty();
  }

  @Test
  public void left_can_win() {
    Fight fight = matchMaker.randomFight();

    assertThat(fight.leftElo()).isEqualTo(1200);
    assertThat(fight.rightElo()).isEqualTo(1200);

    matchMaker.leftWins(fight.uniqueId());

    assertThat(fight.leftElo()).isEqualTo(1212);
    assertThat(fight.rightElo()).isEqualTo(1187);
  }

  @Test
  public void right_can_win() {
    Fight fight = matchMaker.randomFight();

    assertThat(fight.leftElo()).isEqualTo(1200);
    assertThat(fight.rightElo()).isEqualTo(1200);

    matchMaker.rightWins(fight.uniqueId());

    assertThat(fight.leftElo()).isEqualTo(1187);
    assertThat(fight.rightElo()).isEqualTo(1212);
  }

  @Test
  public void can_vote_only_once() {
    Fight fight = matchMaker.randomFight();

    matchMaker.leftWins(fight.uniqueId());

    assertThat(fight.leftElo()).isEqualTo(1212);
    assertThat(fight.rightElo()).isEqualTo(1187);

    matchMaker.leftWins(fight.uniqueId());

    assertThat(fight.leftElo()).isEqualTo(1212);
    assertThat(fight.rightElo()).isEqualTo(1187);
  }
}
