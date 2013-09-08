package net.codestory;

import net.codestory.fight.*;
import net.codestory.fight.captcha.*;
import net.codestory.http.*;
import net.codestory.http.injection.*;
import net.codestory.http.routes.*;

public class WebConfiguration implements Configuration {
  private final Singletons singletons;

  public WebConfiguration(Singletons singletons) {
    this.singletons = singletons;
  }

  @Override
  public void configure(Routes routes) {
    routes.staticDir("classpath:app");
    routes.filter(singletons.get(ThrottleFilter.class));
    routes.get("/", () -> Payload.seeOther("/fight/"));
    routes.add("/fight", singletons.get(FightResource.class));

    redirectOldBlogUrls(routes);
    redirectTeamUrls(routes);
  }

  private void redirectOldBlogUrls(Routes routes) {
    // Keep old blog entry urls alive : S01
    routes.get("/2012/11/16/what-has-been-done-at-devoxx.html", () -> Payload.movedPermanently("/blog/posts/s01e13"));
    routes.get("/2012/11/07/CodeStory-screencast-add-guice.html", () -> Payload.movedPermanently("/blog/posts/s01e12"));
    routes.get("/2012/10/31/CodeStory-screencast-screename-feature.html", () -> Payload.movedPermanently("/blog/posts/s01e11"));
    routes.get("/2012/09/03/CodeStoryWorld.html", () -> Payload.movedPermanently("/blog/posts/s01e10"));
    routes.get("/2012/02/17/CodeStory-au-ParisJug.html", () -> Payload.movedPermanently("/blog/posts/s01e09"));
    routes.get("/2012/02/16/Finale-Intro.html", () -> Payload.movedPermanently("/blog/posts/s01e08"));
    routes.get("/2012/02/09/GildedRose-WebStyle.html", () -> Payload.movedPermanently("/blog/posts/s01e07"));
    routes.get("/2012/02/02/les-finalistes.html", () -> Payload.movedPermanently("/blog/posts/s01e06"));
    routes.get("/2012/01/16/deuxieme-tour.html", () -> Payload.movedPermanently("/blog/posts/s01e05"));
    routes.get("/2011/12/22/bravo.html", () -> Payload.movedPermanently("/blog/posts/s01e04"));
    routes.get("/2011/12/17/selectionfoobarqix.html", () -> Payload.movedPermanently("/blog/posts/s01e03"));
    routes.get("/2011/11/17/but.html", () -> Payload.movedPermanently("/blog/posts/s01e02"));
    routes.get("/2011/11/16/foobarqix.html", () -> Payload.movedPermanently("/blog/posts/s01e01"));

    // Keep old blog entry urls alive : S02
    routes.get("/2013/02/22/un-nouveau-binome.html", () -> Payload.movedPermanently("/blog/posts/s02e08"));
    routes.get("/2013/02/21/concours-2013-phase-2.html", () -> Payload.movedPermanently("/blog/posts/s02e07"));
    routes.get("/2013/02/02/jajascript.html", () -> Payload.movedPermanently("/blog/posts/s02e06"));
    routes.get("/2013/02/01/concours-2013-phase-1.html", () -> Payload.movedPermanently("/blog/posts/s02e05"));
    routes.get("/2013/01/30/scalaskel-java.html", () -> Payload.movedPermanently("/blog/posts/s02e04"));
    routes.get("/2013/01/22/scalaskel.html", () -> Payload.movedPermanently("/blog/posts/s02e03"));
    routes.get("/2013/01/08/concours-2013-FAQ.html", () -> Payload.movedPermanently("/blog/posts/s02e02"));
    routes.get("/2013/01/04/concours-2013.html", () -> Payload.movedPermanently("/blog/posts/s02e01"));
  }

  private void redirectTeamUrls(Routes routes) {
    routes.get("/about/david.html", () -> Payload.movedPermanently("/team/"));
    routes.get("/about/eric.html", () -> Payload.movedPermanently("/team/"));
    routes.get("/about/jean-laurent.html", () -> Payload.movedPermanently("/team/"));
    routes.get("/about/sebastian.html", () -> Payload.movedPermanently("/team/"));
    routes.get("/about/xavier.html", () -> Payload.movedPermanently("/team/"));
    routes.get("/about/christophe.html", () -> Payload.movedPermanently("/team/"));
  }
}
