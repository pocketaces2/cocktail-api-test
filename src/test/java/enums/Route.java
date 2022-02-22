package enums;

/**
 * Holds all of the routes which are available in the cocktail DB API, these form part of the entire
 * submit request URL e.g. www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11000
 */
public enum Route {

  SEARCH_INGREDIENT_BY_NAME("search.php?i="),
  SEARCH_INGREDIENT_BY_ID("lookup.php?iid="),
  SEARCH_COCKTAIL_BY_NAME("search.php?s="),
  SEARCH_COCKTAIL_BY_ID("lookup.php?i="),
  RANDOM_COCKTAIL("random.php");


  private String route;

  Route(String route) {
    this.route = route;
  }

  public String getRoute(){
    return route;
  }

  public String getRouteWithPayload(String payload) {
    return route + payload;
  }

}
