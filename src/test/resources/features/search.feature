Feature: Test the search feature of the cocktail DB works correctly

  Scenario Outline: As a user I want to search by ingredient and see a description
    Given I check the cocktail DB is available
    When I submit a search request for <ingredient>
    Then I check the response matches the schema
    Then I check the HTTP status code is 200
    And I check the ingredient name is vodka
    And I check the alcohol by volume is 40%

    Examples:
      | ingredient |
      | vodka           |