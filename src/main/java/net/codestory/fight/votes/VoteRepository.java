package net.codestory.fight.votes;

import java.util.function.*;

import org.jongo.*;

import com.google.common.base.Supplier;
import com.google.common.base.*;
import com.mongodb.*;

public class VoteRepository {
  private final Supplier<MongoCollection> votes;

  public VoteRepository() {
    String mongoUri = System.getProperty("mongo.uri", "mongodb://localhost/code-story-votes");

    votes = Suppliers.memoize(() -> connect(mongoUri));
  }

  public void forEach(Consumer<Vote> action) {
    votes.get().find().as(Vote.class).forEach(action);
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
