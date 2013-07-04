package net.codestory.vote.gists;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import net.codestory.vote.repository.*;

import org.junit.*;

public class MatchMakerTest {
  Random random = spy(new Random());
  VoteRepository voteRepository = mock(VoteRepository.class);
  Gist gist1 = new Gist("", "");
  Gist gist2 = new Gist("", "");

  MatchMaker matchMaker;

  @Before
  public void setUp() {
    when(voteRepository.all()).thenReturn(Collections.emptyList());
    matchMaker = new MatchMaker(random, new Gists(gist1, gist2), voteRepository);
  }

  @Test
  public void random_candidates() {
    when(random.nextInt(2)).thenReturn(0, 1);

    Fight fight = matchMaker.randomFight();

    assertThat(fight.left()).isSameAs(gist1);
    assertThat(fight.right()).isSameAs(gist2);
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

    matchMaker.fightWonByLeft(fight.uniqueId());

    assertThat(fight.leftElo()).isEqualTo(1212);
    assertThat(fight.rightElo()).isEqualTo(1187);
  }

  @Test
  public void right_can_win() {
    Fight fight = matchMaker.randomFight();

    assertThat(fight.leftElo()).isEqualTo(1200);
    assertThat(fight.rightElo()).isEqualTo(1200);

    matchMaker.fightWonByRight(fight.uniqueId());

    assertThat(fight.leftElo()).isEqualTo(1187);
    assertThat(fight.rightElo()).isEqualTo(1212);
  }

  @Test
  public void can_vote_only_once() {
    Fight fight = matchMaker.randomFight();

    matchMaker.fightWonByLeft(fight.uniqueId());

    assertThat(fight.leftElo()).isEqualTo(1212);
    assertThat(fight.rightElo()).isEqualTo(1187);

    matchMaker.fightWonByLeft(fight.uniqueId());

    assertThat(fight.leftElo()).isEqualTo(1212);
    assertThat(fight.rightElo()).isEqualTo(1187);
  }
}
