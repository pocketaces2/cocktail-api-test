package enums;

public enum Endpoint {

  SEARCH_INGREDIENT_BY_NAME("search.php?i="),
  SEARCH_INGREDIENT_BY_ID("lookup.php?iid="),
  SEARCH_COCKTAIL_BY_NAME("search.php?s="),
  SEARCH_COCKTAIL_BY_ID("lookup.php?i="),
  RANDOM_COCKTAIL("random.php");


  private String endPoint;

  Endpoint(String endPoint) {
    this.endPoint = endPoint;
  }

  public String getEndPoint() {
    return endPoint;
  }

  public void setEndPoint(String endPoint) {
    this.endPoint = endPoint;
  }
}
