package steps.search;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import java.util.List;
import models.search.Cocktail;
import models.search.CocktailSearchResponse;
import models.search.IngredientResponse;
import steps.BaseStep;
import steps.TestContext;

public class ResponseSteps extends BaseStep {


  private TestContext testContext;

  public ResponseSteps(TestContext testContext) {
    super(testContext);
    this.testContext = testContext;

  }

  @Then("I check the response matches the schema: {word}")
  public void i_check_the_response_matches_the_schema(String schemaPath) {
    testContext.getResponse().then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
    System.out.println("checking response schema");
  }

  @Then("I check the HTTP response code indicates success")
  public void i_check_the_http_status_code_is() {
    final int HTTP_200_OK_RESPONSE_CODE = 200;
    assertThat(testContext.getResponse().getStatusCode()).isEqualTo(HTTP_200_OK_RESPONSE_CODE);
    System.out.println("checking http 200");
  }

  @Then("I check the ingredient name is {word}")
  public void i_check_the_ingredient_name_is_vodka(String expectedIngredient) {
    expectedIngredient = expectedIngredient.replaceAll("_", " ");
    IngredientResponse ingredientResponse = getIngredientResponse();
    assertThat(ingredientResponse.getStrIngredient()).isEqualTo(expectedIngredient);
  }

  @Then("I check the alcohol by volume is {word}%")
  public void i_check_the_alcohol_by_volume(String expectedABV) {
    expectedABV = expectedABV.equals("null") ? null : expectedABV;
    IngredientResponse ingredientResponse = getIngredientResponse();
    assertThat(ingredientResponse.getStrABV()).isEqualTo(expectedABV);
  }

  @Then("I check the ingredient search response is null")
  public void i_check_the_ingredient_search_response_null() {
    assertThat(testContext.getResponse().body().jsonPath().getString("ingredients")).isNull();
  }

  @Then("I check the response has returned exactly {int} drink(s)")
  public void i_check_the_response_has_returned_exactly_drink(Integer numberOfCocktailResults) {
    assertThat(getCocktailSearchResponse().getCocktails().size())
        .as("expected %s number of cocktails/drinks in the search response",
            numberOfCocktailResults)
        .isEqualTo(numberOfCocktailResults);
  }

  @Then("I check the cocktail name is {string}")
  public void i_check_the_cocktail_name(String expectedCocktail) {
    assertThat(getFirstCocktailSearchResult().getStrDrink()).isEqualTo(expectedCocktail);
  }

  @And("I check the cocktail contains the following ingredients:")
  public void iCheckTheCocktailContainsTheFollowingIngredients(DataTable table) {
    List<String> expectedIngredients = table.asList();
    Cocktail actualCocktail = getFirstCocktailSearchResult();

    for (String expectedIngredient : expectedIngredients) {
      assertThat(actualCocktail.getIngredientList()).contains(expectedIngredient);
    }
  }

  @Then("I check the search response contain the following cocktails:")
  public void i_check_the_search_response_contain_the_following_cocktails(DataTable table) {
    List<String> expectedCocktails = table.asList();
    CocktailSearchResponse cocktailSearchResponse = getCocktailSearchResponse();

    for (int i = 0; i < cocktailSearchResponse.getCocktails().size(); i++) {
      assertThat(cocktailSearchResponse.getCocktails().get(i).getStrDrink())
          .as("list of returned cocktails does not matched expected list")
          .isIn(expectedCocktails);
    }
  }

  //Ingredient search json response only ever returns one result in the ingredients array
  private IngredientResponse getIngredientResponse() {
    return testContext.getResponse().jsonPath()
        .getObject("ingredients[0]", IngredientResponse.class);
  }

  private CocktailSearchResponse getCocktailSearchResponse() {
    return testContext.getResponse().as(CocktailSearchResponse.class);
  }

  private Cocktail getFirstCocktailSearchResult() {
    return getCocktailSearchResponse().getCocktails().get(0);
  }


}
