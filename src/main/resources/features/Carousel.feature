Feature: Best products

  Scenario: A user can see at least one best selling product
    Given Open main page
    And Check first best selling product
    Then Click on product best selling link
    And Check product page
