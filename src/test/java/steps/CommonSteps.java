package steps;

import static io.restassured.RestAssured.given;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class CommonSteps {

  TestContext testContext;

  public CommonSteps(TestContext testContext){
    this.testContext = testContext;
  }

  @Before
  public void setRequestSpec(){
    testContext.setRequestSpec(given()
        .baseUri("https://www.thecocktaildb.com/")
        .basePath("/api/json/v1/1/"));
  }

  @Given("I check the cocktail DB is available")
  public void checkServiceAvailable(){
    //Healthcheck call would be made here
  }

}
