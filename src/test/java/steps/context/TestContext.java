package steps.context;

import api.EndPoints;

/**
 * Allows for sharing any data between steps definitions, test data specifically is shared using the Scenario Context
 * @see ScenarioContext
 */
public class TestContext {

  private final EndPoints endpoints;
  private final ScenarioContext scenarioContext;

  private static final String BASE_URL = "https://www.thecocktaildb.com/";
  private static final String BASE_PATH = "/api/json/v1/1/";

  public TestContext(){
    endpoints = new EndPoints(BASE_URL, BASE_PATH );
    scenarioContext = new ScenarioContext();
  }

  public EndPoints getEndpoints(){
    return endpoints;
  }

  public ScenarioContext getScenarioContext() {
    return scenarioContext;
  }

}
