package steps;

import io.cucumber.java.en.Given;
import steps.context.TestContext;

public class HealthSteps extends BaseStep{

  TestContext testContext;

  public HealthSteps(TestContext testContext) {
    super(testContext);
    this.testContext = testContext;
  }

  @Given("I check the cocktail DB is available")
  public void checkServiceAvailable() {
    //Healthcheck call would be made here to establish environment is ready for testing, this is unavailable for the cocktail DB
  }

}
