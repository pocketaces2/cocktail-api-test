package steps;

import api.EndPoints;
import steps.context.ScenarioContext;
import steps.context.TestContext;


public abstract class BaseStep {

  private EndPoints endPoints;
  private ScenarioContext scenarioContext;

  public BaseStep(TestContext testContext) {
    endPoints = testContext.getEndpoints();
    scenarioContext = testContext.getScenarioContext();
  }

  public EndPoints getEndPoints() {
    return endPoints;
  }

  public ScenarioContext getScenarioContext() {
    return scenarioContext;
  }
}
