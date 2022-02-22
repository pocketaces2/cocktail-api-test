package api;

import static enums.Route.RANDOM_COCKTAIL;
import static enums.Route.SEARCH_COCKTAIL_BY_ID;
import static enums.Route.SEARCH_COCKTAIL_BY_NAME;
import static enums.Route.SEARCH_INGREDIENT_BY_ID;
import static enums.Route.SEARCH_INGREDIENT_BY_NAME;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.search.CocktailSearchResponse;
import models.search.IngredientSearchResponse;

/**
 * Provides methods that submit requests to API endpoints and returns the response object.
 */
public class EndPoints {

  private final RequestSpecification requestSpec;

  public EndPoints(String baseUri, String basePath) {
    RestAssured.baseURI = baseUri;
    RestAssured.basePath = basePath;

    requestSpec = RestAssured.given();
  }

  public ApiResponse<IngredientSearchResponse> getIngredientSearchById(String payload) {
    Response response = requestSpec.get(SEARCH_INGREDIENT_BY_ID.getRouteWithPayload(payload));
    return getIngredientSearchResponse(response);
  }

  public ApiResponse<IngredientSearchResponse> getIngredientSearchByName(String payload) {
    Response response = requestSpec.get(SEARCH_INGREDIENT_BY_NAME.getRouteWithPayload(payload));
    return getIngredientSearchResponse(response);
  }

  public ApiResponse<CocktailSearchResponse> getCocktailSearchById(String payload) {
    Response response = requestSpec.get(SEARCH_COCKTAIL_BY_ID.getRouteWithPayload(payload));
    return getCocktailSearchResponse(response);
  }

  public ApiResponse<CocktailSearchResponse> getCocktailSearchByName(String payload) {
    Response response = requestSpec.get(SEARCH_COCKTAIL_BY_NAME.getRouteWithPayload(payload));
    return getCocktailSearchResponse(response);
  }

  public ApiResponse<CocktailSearchResponse> getSearchRandomCocktail() {
    Response response = requestSpec.get(RANDOM_COCKTAIL.getRoute());
    return getCocktailSearchResponse(response);
  }

  private ApiResponse<IngredientSearchResponse> getIngredientSearchResponse(Response response) {
    response.prettyPrint();
    return new ApiResponse<>(IngredientSearchResponse.class, response);
  }

  private ApiResponse<CocktailSearchResponse> getCocktailSearchResponse(Response response) {
    response.prettyPrint();
    return new ApiResponse<>(CocktailSearchResponse.class, response);
  }


}
