package steps;


import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.search.IngredientResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;

public class SearchSteps {

    //TODO: enum?
    private static final int HTTP_200_OK_RESPONSE_CODE = 200;
    RequestSpecification requestSpec;
    Response response;

    @Before
    public void setRequestSpec(){
        requestSpec = given()
                .baseUri("https://www.thecocktaildb.com/")
                .basePath("/api/json/v1/1/");
    }

    @Given("I check the cocktail DB is available")
    public void checkServiceAvailable(){
        //Healthcheck call would be made here
    }

    @Given("I submit an ingredient search request by id: {int}")
    public void submitIngredientSearchById(int id){
        System.out.println("Submitting search request for id: " + id);
        submitRequest(getSearchIngredientByIdEndpoint(id));
    }

    @Given("I submit an ingredient search request by search term: {word}")
    public void submitIngredientSearchByTerm(String searchTerm){
        System.out.println("Submitting search request for term:" + searchTerm);
        submitRequest(getSearchIngredientByTermEndpoint(searchTerm));
    }

    @Then("I check the response matches the schema: {word}")
    public void i_check_the_response_matches_the_schema(String schemaPath) {
        response.then().assertThat().body(matchesJsonSchemaInClasspath(schemaPath));
        System.out.println("checking response schema");
    }

    @Then("I check the HTTP response code indicates success")
    public void i_check_the_http_status_code_is(){
        assertThat(response.getStatusCode()).isEqualTo(HTTP_200_OK_RESPONSE_CODE);
        System.out.println("checking http 200");
    }


    @Then("I check the ingredient name is {word}")
    public void i_check_the_ingredient_name_is_vodka(String expectedIngredient){
        expectedIngredient = expectedIngredient.replaceAll("_", " ");
        IngredientResponse ingredientResponse = getIngredientResponse();
        assertThat(ingredientResponse.getStrIngredient()).isEqualTo(expectedIngredient);
    }

    @Then("I check the alcohol by volume is {word}%")
    public void i_check_the_alcohol_by_volume_is(String expectedABV) {
        expectedABV = expectedABV.equals("null") ? null: expectedABV;
        IngredientResponse ingredientResponse = getIngredientResponse();
        assertThat(ingredientResponse.getStrABV()).isEqualTo(expectedABV);
    }

    @Then("I check the ingredient search response is null")
    public void i_check_the_ingredient_search_response_null(){
        assertThat(response.body().jsonPath().getString("ingredients")).isNull();
    }

    private void submitRequest(String endpoint){
        response = requestSpec.get(endpoint);
        response.prettyPrint();
    }

    private String getSearchIngredientByTermEndpoint(String ingredient){
        return "search.php?i=" + ingredient;
    }

    private String getSearchIngredientByIdEndpoint(int ingredient){
        return "lookup.php?iid=" + ingredient;
    }

    private IngredientResponse getIngredientResponse() {
        return response.jsonPath().getObject("ingredients[0]", IngredientResponse.class);
    }

}
