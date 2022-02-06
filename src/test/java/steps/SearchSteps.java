package steps;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.IngredientSearchResponse;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

import static io.restassured.RestAssured.given;

public class SearchSteps {

    //TODO: enum?
    private static final int HTTP_200_OK_RESPONSE_CODE = 200;
    Response response;

    @Given("I check the cocktail DB is available")
    public void checkServiceAvailable(){

    }

    @Given("I submit a search request for {word}")
    public void submitSearchRequest(String ingredient){
        System.out.println("Submitting search request for " + ingredient);

        RequestSpecification requestSpec = given()
                .baseUri("https://www.thecocktaildb.com/")
                .basePath("/api/json/v1/1/");

        response = requestSpec.get(getSearchIngredientEndpoint(ingredient));
        System.out.println(response.prettyPrint());
    }

    private String getSearchIngredientEndpoint(String ingredient){
        return "search.php?i=" + ingredient;
    }

    @Then("I check the response matches the schema")
    public void i_check_the_response_matches_the_schema() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/ingredientSearchSchema.json"));
        System.out.println("checking schema");
    }

    @Then("I check the HTTP response code indicates success")
    public void i_check_the_http_status_code_is() throws JsonProcessingException {
        assertThat(response.getStatusCode()).isEqualTo(HTTP_200_OK_RESPONSE_CODE);
        System.out.println("checking http 200");
    }


    @Then("I check the ingredient name is {word}")
    public void i_check_the_ingredient_name_is_vodka(String expectedIngredient) throws JsonProcessingException {

        String jsonRespon = "{\n" +
                "    \"ingredients\": [\n" +
                "        {\n" +
                "            \"idIngredient\": \"1\",\n" +
                "            \"strIngredient\": \"Vodka\",\n" +
                "            \"strDescription\": \"Vodka is a distilled beverage composed primarily of water and ethanol, sometimes with traces of impurities and flavorings. Traditionally, vodka is made by the distillation of fermented cereal grains or potatoes, though some modern brands use other substances, such as fruits or sugar.\\r\\n\\r\\nSince the 1890s, the standard Polish, Russian, Belarusian, Ukrainian, Estonian, Latvian, Lithuanian and Czech vodkas are 40% alcohol by volume ABV (80 US proof), a percentage that is widely misattributed to Dmitri Mendeleev. The European Union has established a minimum of 37.5% ABV for any \\\"European vodka\\\" to be named as such. Products sold as \\\"vodka\\\" in the United States must have a minimum alcohol content of 40%. Even with these loose restrictions, most vodka sold contains 40% ABV. For homemade vodkas and distilled beverages referred to as \\\"moonshine\\\", see moonshine by country.\\r\\n\\r\\nVodka is traditionally drunk neat (not mixed with any water, ice, or other mixer), though it is often served chilled in the vodka belt countries (Belarus, Estonia, Finland, Iceland, Latvia, Lithuania, Norway, Poland, Russia, Sweden, Ukraine). It is also commonly used in cocktails and mixed drinks, such as the vodka martini, Cosmopolitan, vodka tonic, Screwdriver, Greyhound, Black or White Russian, Moscow Mule, and Bloody Mary.\\r\\n\\r\\nScholars debate the beginnings of vodka. It is a contentious issue because very little historical material is available. For many centuries, beverages differed significantly compared to the vodka of today, as the spirit at that time had a different flavor, color and smell, and was originally used as medicine. It contained little alcohol, an estimated maximum of about 14%, as only this amount can be attained by natural fermentation. The still, allowing for distillation (\\\"burning of wine\\\"), increased purity, and increased alcohol content, was invented in the 8th century.\\r\\n\\r\\nA common property of the vodkas produced in the United States and Europe is the extensive use of filtration prior to any additional processing including the addition of flavorants. Filtering is sometimes done in the still during distillation, as well as afterwards, where the distilled vodka is filtered through activated charcoal and other media to absorb trace amounts of substances that alter or impart off-flavors to the vodka. However, this is not the case in the traditional vodka-producing nations, so many distillers from these countries prefer to use very accurate distillation but minimal filtering, thus preserving the unique flavors and characteristics of their products.\\r\\n\\r\\nThe master distiller is in charge of distilling the vodka and directing its filtration, which includes the removal of the \\\"fore-shots\\\", \\\"heads\\\" and \\\"tails\\\". These components of the distillate contain flavor compounds such as ethyl acetate and ethyl lactate (heads) as well as the fusel oils (tails) that impact the usually desired clean taste of vodka. Through numerous rounds of distillation, or the use of a fractioning still, the taste is modified and clarity is increased. In contrast, distillery process for liquors such as whiskey, rum, and baijiu allow portions of the \\\"heads\\\" and \\\"tails\\\" to remain, giving them their unique flavors.\\r\\n\\r\\nRepeated distillation of vodka will make its ethanol level much higher than is acceptable to most end users, whether legislation determines strength limits or not. Depending on the distillation method and the technique of the stillmaster, the final filtered and distilled vodka may have as much as 95–96% ethanol. As such, most vodka is diluted with water prior to bottling.\\r\\n\\r\\nPolish distilleries make a very pure (96%, 192 proof, formerly also 98%) rectified spirit (Polish language: spirytus rektyfikowany). Technically a form of vodka, it is sold in liquor stores rather than pharmacies. Similarly, the German market often carries German, Hungarian, Polish, and Ukrainian-made varieties of vodka of 90 to 95% ABV. A Bulgarian vodka, Balkan 176°, has an 88% alcohol content. Everclear, an American brand, is also sold at 95% ABV.\",\n" +
                "            \"strType\": \"Vodka\",\n" +
                "            \"strAlcohol\": \"Yes\",\n" +
                "            \"strABV\": \"40\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        ObjectMapper mapper = new ObjectMapper();

        IngredientSearchResponse ingredientSearchResponse = mapper.readValue(response.getBody().asString(), IngredientSearchResponse.class);
        assertThat(ingredientSearchResponse.getIngredients().get(0).getStrIngredient()).isEqualTo(expectedIngredient);

    }
    @Then("I check the alcohol by volume is {word}%")
    public void i_check_the_alcohol_by_volume_is(String expectedABV) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        IngredientSearchResponse ingredientSearchResponse = mapper.readValue(response.getBody().asString(), IngredientSearchResponse.class);
        assertThat(ingredientSearchResponse.getIngredients().get(0).getStrABV()).isEqualTo(expectedABV);
    }

}
