package steps.search;


import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import enums.Endpoint;
import steps.BaseStep;
import steps.TestContext;

public class RequestSteps extends BaseStep {

    private TestContext testContext;

    public RequestSteps(TestContext testContext){
        super(testContext);
        this.testContext = testContext;

    }

    @Given("I submit an ingredient search request by id: {int}")
    public void submitIngredientSearchById(int id){
        submitRequest(Endpoint.SEARCH_INGREDIENT_BY_ID, String.valueOf(id));
    }

    @Given("I submit an ingredient search request by search name: {word}")
    public void submitIngredientSearchByName(String name){
        submitRequest(Endpoint.SEARCH_INGREDIENT_BY_NAME, name);
    }

    @Given("I submit a cocktail search request by search name: {word}")
    public void submitCocktailSearchByName(String name){
        submitRequest(Endpoint.SEARCH_COCKTAIL_BY_NAME, name);
    }

    @Given("I submit a cocktail search request by search id: {word}")
    public void submitCocktailSearchById(String id){
        submitRequest(Endpoint.SEARCH_COCKTAIL_BY_ID, id);
    }

    private void submitRequest(Endpoint endpoint, String payload){
        Response response = testContext.getRequestSpec().get(endpoint.getEndPoint() + payload);
        response.prettyPrint();
        testContext.setResponse(response);
    }


}
