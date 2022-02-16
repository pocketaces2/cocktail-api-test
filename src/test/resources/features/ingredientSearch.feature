Feature: Test the ingredient search feature of the cocktail DB works correctly

  Scenario Outline: As a user I want to search by ingredient and see a description
    Given I check the cocktail DB is available
    When I submit an ingredient search request by search name: <ingredient>
    Then I check the response matches the schema: schemas/ingredientSearchSchema.json
    Then I check the HTTP response code indicates success
    And I check the ingredient name is <ingredient>
    And I check the alcohol by volume is <expectedABV>%

    Examples:
      | ingredient          | expectedABV |
      | Rum                 | 40          |
      | Vodka               | 40          |
      | Beer                | 4           |
      | Lime_Juice          | null        |
      | Salt                | null        |
      | Baileys_Irish_Cream | 13          |

  Scenario Outline: As a user I want to search an ingredient by ID and see a description
    Given I check the cocktail DB is available
    When I submit an ingredient search request by id: <id>
    Then I check the response matches the schema: schemas/ingredientSearchSchema.json
    Then I check the HTTP response code indicates success
    And I check the ingredient name is <ingredient>
    And I check the alcohol by volume is <expectedABV>%

    Examples:
      | ingredient | expectedABV | id |
      | Rum        | 40          | 3  |
      | Vodka      | 40          | 1  |


  Scenario: As a user I want to check the ingredient search functions correctly for an non-existent ingredient

    Given I check the cocktail DB is available
    When I submit an ingredient search request by id: 999999
    Then I check the response matches the schema: schemas/ingredientNullSearchSchema.json
    Then I check the HTTP response code indicates success
    Then I check the ingredient search response is null
