package net.codestory.fight.ranking;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class RankTest {
  Rank rank1 = new Rank();
  Rank rank2 = new Rank();

  @Test
  public void initial_ranking() {
    assertThat(rank1.elo()).isEqualTo(1400);
    assertThat(rank2.elo()).isEqualTo(1400);
  }

  @Test
  public void ranking_after_one_match() {
    rank1.beats(rank2);

    assertThat(rank1.elo()).isEqualTo(1412);
    assertThat(rank2.elo()).isEqualTo(1387);
  }

  @Test
  public void ranking_after_two_matches() {
    rank1.beats(rank2);
    rank1.beats(rank2);

    assertThat(rank1.elo()).isEqualTo(1424);
    assertThat(rank2.elo()).isEqualTo(1375);
  }

  @Test
  public void tie_after_two_matches() {
    rank1.beats(rank2);
    rank2.beats(rank1);

    assertThat(rank1.elo()).isEqualTo(1399);
    assertThat(rank2.elo()).isEqualTo(1400);
  }

  @Test
  public void hundred_matches() {
    for (int i = 0; i < 100; i++) {
      rank1.beats(rank2);
    }

    assertThat(rank1.elo()).isEqualTo(1600);
    assertThat(rank2.elo()).isEqualTo(1199);
  }
}
