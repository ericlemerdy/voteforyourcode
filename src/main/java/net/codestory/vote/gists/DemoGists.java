package net.codestory.vote.gists;

public class DemoGists implements Gists {
  @Override
  public Candidates candidates() {
    return new Candidates(
        "dgageot/9895cbae5fbd70892d0d",
        "dgageot/9895cbae5fbd70892d0d"
    );
  }
}
