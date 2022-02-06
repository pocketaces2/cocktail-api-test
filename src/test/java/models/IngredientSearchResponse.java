package models;

import lombok.Data;

import java.util.List;

@Data
public class IngredientSearchResponse {

    private List<Ingredients> ingredients;

    @Data
    public static class Ingredients {

        private String idIngredient;
        private String strIngredient;
        private String strDescription;
        private String strType;
        private String strAlcohol;
        private String strABV;
    }

}
