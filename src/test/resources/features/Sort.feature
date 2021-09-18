Feature: Sorting feature

  Scenario: Sort by price ascending
    Given Open store brand page
    When Set sorting by price ascending
    Then Check sorted price results ascending

  Scenario: Sort by price descending
    Given Open store brand page
    When Set sorting by price descending
    Then Check sorted price results descending


  Scenario: Sort by name ascending
    Given Open store brand page
    When Set sorting by name ascending
    Then Check sorted results ascending

  Scenario: Sort by name descending
    Given Open store brand page
    When Set sorting by name descending
    Then Check sorted results descending

