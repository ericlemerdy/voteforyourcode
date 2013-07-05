package net.codestory.vote.gists;

import static org.fest.assertions.Assertions.*;

import org.junit.*;

public class RankNameTest {
  RankName rankName = new RankName();

  @Test
  public void rank_names() {
    assertThat(rankName.name(0)).isEqualTo("Crappy Code");
    assertThat(rankName.name(1000)).isEqualTo("Crappy Code");
    assertThat(rankName.name(1400)).isEqualTo("Good Enough Code");
    assertThat(rankName.name(1600)).isEqualTo("Good Code");
    assertThat(rankName.name(1800)).isEqualTo("Very Good Code");
    assertThat(rankName.name(2000)).isEqualTo("Top Code");
    assertThat(rankName.name(2200)).isEqualTo("Amazing Code");
    assertThat(rankName.name(2300)).isEqualTo("OMG! Code");
    assertThat(rankName.name(2400)).isEqualTo("Alien Code");
    assertThat(rankName.name(2500)).isEqualTo("THE Code");
  }
}
