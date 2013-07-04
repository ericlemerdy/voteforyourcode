package net.codestory.vote.repository;

import org.jongo.*;

import com.google.common.base.*;
import com.mongodb.*;

public class VoteRepository {
  private final Supplier<MongoCollection> votes;

  public VoteRepository(String uri) {
    votes = Suppliers.memoize(() -> connect(uri));
  }

  public Iterable<Vote> all() {
    return votes.get().find().as(Vote.class);
  }

  public void save(Vote vote) {
    votes.get().save(vote);
  }

  private static MongoCollection connect(String uri) {
    MongoClientURI mongoUri = new MongoClientURI(uri);

    DB db;
    try {
      db = new MongoClient(mongoUri).getDB(mongoUri.getDatabase());
    } catch (Exception e) {
      throw new IllegalStateException("Unable to connect to mongo " + uri, e);
    }

    return new Jongo(db).getCollection("votes");
  }
}
