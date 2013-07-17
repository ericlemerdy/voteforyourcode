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
  }
}
