package net.codestory.misc;

import net.codestory.http.*;

import org.junit.rules.*;
import org.junit.runner.*;
import org.junit.runners.model.*;

public class WebServerRule extends WebServer implements TestRule {
  public WebServerRule(Configuration configuration) {
    configure(configuration);
  }

  @Override
  public Statement apply(Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        startOnRandomPort();
        try {
          base.evaluate();
        } finally {
          stop();
        }
      }
    };
  }
}
