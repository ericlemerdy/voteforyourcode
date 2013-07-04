package net.codestory.vote.ranking;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class VotesTest {
  Votes votes = new Votes();

  @Test
  public void initial_ranking() {
    assertThat(votes.score(0)).isEqualTo(1200);
    assertThat(votes.score(1)).isEqualTo(1200);
    assertThat(votes.score(2)).isEqualTo(1200);
  }

  @Test
  public void ranking_after_one_match() {
    votes.match(0, 1);

    assertThat(votes.score(0)).isEqualTo(1212);
    assertThat(votes.score(1)).isEqualTo(1187);
    assertThat(votes.score(2)).isEqualTo(1200);
  }

  @Test
  public void ranking_after_two_matches() {
    votes.match(0, 1);
    votes.match(0, 1);

    assertThat(votes.score(0)).isEqualTo(1224);
    assertThat(votes.score(1)).isEqualTo(1175);
  }

  @Test
  public void tie_after_two_matches() {
    votes.match(0, 1);
    votes.match(1, 0);

    assertThat(votes.score(0)).isEqualTo(1199);
    assertThat(votes.score(1)).isEqualTo(1200);
  }

  @Test
  public void tie_after_hundred_of_matches() {
    for (int i = 0; i < 100; i++) {
      votes.match(0, 1);
    }

    assertThat(votes.score(0)).isEqualTo(1400);
    assertThat(votes.score(1)).isEqualTo(999);
  }
}
