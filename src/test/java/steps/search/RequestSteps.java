package steps.search;

import api.ApiResponse;
import enums.Context;
import io.cucumber.java.en.When;
import models.search.CocktailSearchResponse;
import models.search.IngredientSearchResponse;
import steps.BaseStep;
import steps.context.TestContext;

public class RequestSteps extends BaseStep {

  public RequestSteps(TestContext testContext) {
    super(testContext);
  }

  @When("I submit an ingredient search request by id: {word}")
  public void submitIngredientSearchById(String id){
    ApiResponse<IngredientSearchResponse> ingredientSearchResponse = getEndPoints().getIngredientSearchById(id);
    getScenarioContext().setContext(Context.API_RESPONSE, ingredientSearchResponse);
  }

  @When("I submit an ingredient search request by search name: {word}")
  public void submitIngredientSearchByName(String name){
    ApiResponse<IngredientSearchResponse> ingredientSearchResponse = getEndPoints().getIngredientSearchByName(name);
    getScenarioContext().setContext(Context.API_RESPONSE, ingredientSearchResponse);
  }

  @When("I submit a cocktail search request by search name: {word}")
  public void submitCocktailSearchByName(String name){
    ApiResponse<CocktailSearchResponse> cocktailSearchResponse = getEndPoints().getCocktailSearchByName(name);
    getScenarioContext().setContext(Context.API_RESPONSE, cocktailSearchResponse);
  }

  @When("I submit a cocktail search request by search id: {word}")
  public void submitCocktailSearchById(String id){
    ApiResponse<CocktailSearchResponse> cocktailSearchResponse = getEndPoints().getCocktailSearchById(id);
    getScenarioContext().setContext(Context.API_RESPONSE, cocktailSearchResponse);
  }

  @When("I submit a search request for a random cocktail")
  public void i_submit_a_search_request_for_a_random_cocktail(){
    ApiResponse<CocktailSearchResponse> cocktailSearchResponse = getEndPoints().getSearchRandomCocktail();
    getScenarioContext().setContext(Context.API_RESPONSE, cocktailSearchResponse);
  }

}
