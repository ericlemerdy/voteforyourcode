package net.codestory.vote.repository;

import org.jongo.*;

import com.google.common.base.*;
import com.mongodb.*;

public class MongoVoteRepository implements VoteRepository {
  private final Supplier<MongoCollection> votes;

  public MongoVoteRepository() {
    votes = Suppliers.memoize(() -> {
      try {
        MongoClientURI uri = new MongoClientURI(uri());

        DB db = new MongoClient(uri).getDB(uri.getDatabase());
        Jongo jongo = new Jongo(db);

        return jongo.getCollection("votes");
      } catch (Exception e) {
        throw new IllegalStateException("Unable to read votes", e);
      }
    });
  }

  private static String uri() {
    return System.getProperty("mongo.uri", "mongodb://localhost/code-story-votes");
  }

  @Override
  public Iterable<Vote> all() {
    return votes.get().find().as(Vote.class);
  }

  @Override
  public void save(Vote vote) {
    votes.get().save(vote);
  }
}
