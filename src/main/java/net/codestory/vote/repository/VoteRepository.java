package net.codestory.vote.repository;

public interface VoteRepository {
  Iterable<Vote> all();

  void save(Vote vote);
}
