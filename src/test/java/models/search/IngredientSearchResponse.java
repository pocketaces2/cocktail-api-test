package models.search;

import java.util.ArrayList;
import lombok.Data;

@Data
public class IngredientSearchResponse {
  private ArrayList<Ingredient> ingredients;
}
