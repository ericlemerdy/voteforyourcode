package net.codestory.fight.ranking;

import static org.fest.assertions.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import net.codestory.fight.gists.*;
import net.codestory.fight.votes.*;

import org.junit.*;

public class MatchMakerTest {
  Random random = spy(new Random());
  VoteRepository voteRepository = mock(VoteRepository.class);
  Gists gists = new Gists();
  Gist gist0 = gists.get(0);
  Gist gist1 = gists.get(1);

  MatchMaker matchMaker;

  @Before
  public void setUp() {
    matchMaker = new MatchMaker(random, gists, voteRepository);
  }

  @Test
  public void random_candidates() {
    when(random.nextInt(gists.size())).thenReturn(0, 1);

    Fight fight = matchMaker.randomFight();

    assertThat(fight.left()).isSameAs(gist0);
    assertThat(fight.right()).isSameAs(gist1);
  }

  @Test
  public void no_doubles() {
    when(random.nextInt(gists.size())).thenReturn(1, 1, 1, 0);

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

    assertThat(fight.left().rank().elo()).isEqualTo(1400);
    assertThat(fight.right().rank().elo()).isEqualTo(1400);

    matchMaker.fightWonByLeft(fight.uniqueId());

    assertThat(fight.left().rank().elo()).isEqualTo(1412);
    assertThat(fight.right().rank().elo()).isEqualTo(1387);
  }

  @Test
  public void right_can_win() {
    Fight fight = matchMaker.randomFight();

    assertThat(fight.left().rank().elo()).isEqualTo(1400);
    assertThat(fight.right().rank().elo()).isEqualTo(1400);

    matchMaker.fightWonByRight(fight.uniqueId());

    assertThat(fight.left().rank().elo()).isEqualTo(1387);
    assertThat(fight.right().rank().elo()).isEqualTo(1412);
  }

  @Test
  public void can_vote_only_once() {
    Fight fight = matchMaker.randomFight();

    matchMaker.fightWonByLeft(fight.uniqueId());

    assertThat(fight.left().rank().elo()).isEqualTo(1412);
    assertThat(fight.right().rank().elo()).isEqualTo(1387);

    matchMaker.fightWonByLeft(fight.uniqueId());

    assertThat(fight.left().rank().elo()).isEqualTo(1412);
    assertThat(fight.right().rank().elo()).isEqualTo(1387);
  }
}
