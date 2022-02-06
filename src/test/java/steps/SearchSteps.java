package steps;


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
    Response response;

    @Given("I check the cocktail DB is available")
    public void checkServiceAvailable(){

    }

    @Given("I submit a search request for {word}")
    public void submitSearchRequest(String ingredient){
        System.out.println("Submitting search request for " + ingredient);

        RequestSpecification requestSpec = given()
                .baseUri("https://www.thecocktaildb.com/")
                .basePath("/api/json/v1/1/");

        response = requestSpec.get(getSearchIngredientEndpoint(ingredient));
        response.prettyPrint();
    }

    private String getSearchIngredientEndpoint(String ingredient){
        return "search.php?i=" + ingredient;
    }

    @Then("I check the response matches the schema")
    public void i_check_the_response_matches_the_schema() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/ingredientSearchSchema.json"));
        System.out.println("checking schema");
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

    private IngredientResponse getIngredientResponse() {
        return response.jsonPath().getObject("ingredients[0]", IngredientResponse.class);
    }

}
