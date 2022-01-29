package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SearchSteps {

    @Given("I check the cocktail DB is available")
    public void checkServiceAvailable(){

    }

    @Given("I submit a search request for {word}")
    public void submitSearchRequest(String ingredient){
        System.out.println("Submitting search request for " + ingredient);
    }

    @Then("I check the response matches the schema")
    public void i_check_the_response_matches_the_schema() {
        System.out.println("checking schema");
    }


    @Then("I check the HTTP status code is {int}")
    public void i_check_the_http_status_code_is(Integer httpStatusCode) {
        System.out.println("checking http 200");
    }


    @Then("I check the ingredient name is vodka")
    public void i_check_the_ingredient_name_is_vodka() {

    }
    @Then("I check the alcohol by volume is {int}%")
    public void i_check_the_alcohol_by_volume_is(Integer int1) {

    }

}
