package net.codestory.vote.repository;

import org.jongo.*;

import com.google.common.base.*;
import com.mongodb.*;

public class VoteRepository {
  private final Supplier<MongoCollection> votes;

  public VoteRepository(String uri) {
    votes = Suppliers.memoize(() -> {
      try {
        MongoClientURI mongoUri = new MongoClientURI(uri);

        DB db = new MongoClient(mongoUri).getDB(mongoUri.getDatabase());
        Jongo jongo = new Jongo(db);

        return jongo.getCollection("votes");
      } catch (Exception e) {
        throw new IllegalStateException("Unable to read votes", e);
      }
    });
  }

  public Iterable<Vote> all() {
    return votes.get().find().as(Vote.class);
  }

  public void save(Vote vote) {
    votes.get().save(vote);
  }
}
