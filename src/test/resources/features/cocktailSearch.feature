Feature: Test the cocktail search by name feature of the cocktail DB works correctly

  Scenario: As a user I want to search by for a mojito cocktail by ID and see a description
    Given I check the cocktail DB is available
    When I submit a cocktail search request by search id: 11000
    Then I check the response matches the schema: schemas/cocktailSearchSchema.json
    And I check the response has returned exactly 1 drink
    And I check the cocktail name is "Mojito"
    And I check the cocktail contains the following ingredients:
      | Light rum  |
      | Lime       |
      | Sugar      |
      | Mint       |
      | Soda water |

  Scenario: As a user I want to do a general search for all Daiquiri cocktails
    Given I check the cocktail DB is available
    When I submit a cocktail search request by search name: Daiquiri
    Then I check the response matches the schema: schemas/cocktailSearchSchema.json
    And I check the response has returned exactly 7 drinks
    And I check the search response contain the following cocktails:
      | Daiquiri                         |
      | Banana Daiquiri                  |
      | Frozen Daiquiri                  |
      | Strawberry Daiquiri              |
      | Frozen Mint Daiquiri             |
      | Frozen Pineapple Daiquiri        |
      | Banana Strawberry Shake Daiquiri |

  Scenario: As a user I want to search for a random cocktail and see a description
    Given I check the cocktail DB is available
    When I submit a search request for a random cocktail
    Then I check the response matches the schema: schemas/cocktailSearchSchema.json
    And I check the response has returned exactly 1 drink

  Scenario: As a user I want to test that searching for an non-existent cocktail returns a null response
    Given I check the cocktail DB is available
    When I submit a cocktail search request by search id: 999999
    Then I check the response matches the schema: schemas/noCocktailResponseSchema.json