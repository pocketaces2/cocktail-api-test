package models.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Data;

@Data
@JsonTypeName("Drink")
public class Cocktail {

  private String idDrink;
  private String strDrink;
  private Object strDrinkAlternate;
  private String strTags;
  private Object strVideo;
  private String strCategory;
  private Object strIBA;
  private String strAlcoholic;
  private String strGlass;
  private String strInstructions;
  private Object strInstructionsES;
  private Object strInstructionsDE;
  private Object strInstructionsFR;
  private String strInstructionsIT;
  @JsonProperty("strInstructionsZH-HANS")
  private Object strInstructionsZHHANS;
  @JsonProperty("strInstructionsZH-HANT")
  private Object strInstructionsZHHANT;
  private String strDrinkThumb;
  private String strIngredient1;
  private String strIngredient2;
  private String strIngredient3;
  private String strIngredient4;
  private String strIngredient5;
  private String strIngredient6;
  private String strIngredient7;
  private Object strIngredient8;
  private Object strIngredient9;
  private Object strIngredient10;
  private Object strIngredient11;
  private Object strIngredient12;
  private Object strIngredient13;
  private Object strIngredient14;
  private Object strIngredient15;
  private String strMeasure1;
  private String strMeasure2;
  private String strMeasure3;
  private String strMeasure4;
  private String strMeasure5;
  private String strMeasure6;
  private String strMeasure7;
  private Object strMeasure8;
  private Object strMeasure9;
  private Object strMeasure10;
  private Object strMeasure11;
  private Object strMeasure12;
  private Object strMeasure13;
  private Object strMeasure14;
  private Object strMeasure15;
  private String strImageSource;
  private Object strImageAttribution;
  private String strCreativeCommonsConfirmed;
  private Object dateModified;

  public List<String> getIngredientList(){
    List<String> ingredientList = new ArrayList<>();
    Collections.addAll(ingredientList,
        getStrIngredient1(), getStrIngredient2(), getStrIngredient3(), getStrIngredient4(), getStrIngredient5(), getStrIngredient6());

    return ingredientList;
  }
}
