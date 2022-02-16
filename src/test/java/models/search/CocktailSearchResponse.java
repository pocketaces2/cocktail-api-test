package models.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import lombok.Data;

@Data
public class CocktailSearchResponse{
  @JsonProperty("drinks")
  private ArrayList<Cocktail> cocktails;
}


