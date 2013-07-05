package net.codestory.vote.gists;

public class RankName {
  public String name(int elo) {
    if (elo >= 2500) return "THE Code";
    if (elo >= 2400) return "Alien Code";
    if (elo >= 2300) return "OMG! Code";
    if (elo >= 2200) return "Amazing Code";
    if (elo >= 2000) return "Top Code";
    if (elo >= 1800) return "Very Good Code";
    if (elo >= 1600) return "Good Code";
    if (elo >= 1400) return "Good Enough Code";

    return "Crappy Code";
  }
}
