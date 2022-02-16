package steps.search;

import enums.Endpoint;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import steps.BaseStep;
import steps.TestContext;

public class RequestSteps extends BaseStep {

  private TestContext testContext;

  public RequestSteps(TestContext testContext) {
    super(testContext);
    this.testContext = testContext;

  }

  @When("I submit an ingredient search request by id: {int}")
  public void submitIngredientSearchById(int id) {
    submitRequest(Endpoint.SEARCH_INGREDIENT_BY_ID, String.valueOf(id));
  }

  @When("I submit an ingredient search request by search name: {word}")
  public void submitIngredientSearchByName(String name) {
    submitRequest(Endpoint.SEARCH_INGREDIENT_BY_NAME, name);
  }

  @When("I submit a cocktail search request by search name: {word}")
  public void submitCocktailSearchByName(String name) {
    submitRequest(Endpoint.SEARCH_COCKTAIL_BY_NAME, name);
  }

  @When("I submit a cocktail search request by search id: {word}")
  public void submitCocktailSearchById(String id) {
    submitRequest(Endpoint.SEARCH_COCKTAIL_BY_ID, id);
  }

  @When("I submit a search request for a random cocktail")
  public void i_submit_a_search_request_for_a_random_cocktail() {
    submitRequest(Endpoint.RANDOM_COCKTAIL);
  }

  private void submitRequest(Endpoint endpoint) {
    Response response = testContext.getRequestSpec().get(endpoint.getEndPoint());
    configureResponse(response);
  }

  private void submitRequest(Endpoint endpoint, String payload) {
    Response response = testContext.getRequestSpec().get(endpoint.getEndPoint() + payload);
    configureResponse(response);
  }

  private void configureResponse(Response response) {
    response.prettyPrint();
    testContext.setResponse(response);
  }


}
