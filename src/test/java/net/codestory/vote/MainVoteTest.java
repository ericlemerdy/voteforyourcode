package net.codestory.vote;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import net.codestory.vote.misc.*;

import org.junit.*;

import com.jayway.restassured.specification.*;

public class MainVoteTest {
  @Rule
  public WebServerRule webServer = new WebServerRule(new VoteConfiguration());

  @Test
  public void test_http() {
    expect().content(containsString("Hello")).when().get("/");
  }

  private ResponseSpecification expect() {
    return given().port(webServer.port()).expect();
  }
}
