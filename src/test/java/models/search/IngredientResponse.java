package models.search;

import lombok.Data;

@Data
public class IngredientResponse {

  private String idIngredient;
  private String strIngredient;
  private String strDescription;
  private String strType;
  private String strAlcohol;
  private String strABV;
}
