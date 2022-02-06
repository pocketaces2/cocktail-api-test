Feature: Test the search feature of the cocktail DB works correctly

  Scenario Outline: As a user I want to search by ingredient and see a description
    Given I check the cocktail DB is available
    When I submit a search request for <ingredient>
    Then I check the response matches the schema
    Then I check the HTTP response code indicates success
    And I check the ingredient name is <ingredient>
    And I check the alcohol by volume is <expectedABV>%

    Examples:
      | ingredient | expectedABV |
      | Vodka      | 40          |