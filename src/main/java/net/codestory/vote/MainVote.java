package net.codestory.vote;

import net.codestory.http.*;
import net.codestory.vote.gists.*;

import com.google.inject.*;

public class MainVote {
  public static void main(String[] args) {
    new MainVote().start(8181);
  }

  private void start(int port) {
    Injector injector = Guice.createInjector(new MainVoteModule());

    WebServer web = new WebServer();
    web.configure(injector.getInstance(VoteConfiguration.class));
    web.start(port);
  }

  public static class MainVoteModule extends AbstractModule {
    @Override
    protected void configure() {
      bind(Gists.class).to(DemoGists.class);
    }
  }
}
