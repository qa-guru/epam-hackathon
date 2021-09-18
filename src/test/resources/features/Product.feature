Feature: Product feature

  Scenario: Add to bag and go to basket
    Given Open product page
    And I can see the product code
    When Add to bag
    And I can see the product name
    Then Go to basket

  Scenario: Change quantity add to bag and go to basket
    Given Open product page
    And I can see the product code
    When Increase Item Quantity by one
    When Increase Item Quantity by one
    When Increase Item Quantity by one
    When Decrease Item Quantity by one
    When Add to bag
    Then Go to basket

  Scenario: Add to bag and continue shopping
    Given Open product page
    And I can see the product code
    When Add to bag
    Then Continue shopping

  Scenario: Add to bag and close window
    Given Open product page
    And I can see the product code
    When Add to bag
    Then Close window

  Scenario: Pick up in the store
    Given Open product page
    And I can see the product code
    When Pick up in the store

  Scenario: Pick up in the store and close window
    Given Open product page
    And I can see the product code
    When Pick up in the store
    Then Close window

  Scenario: Add to wishlist
    Given Open product page
    And I can see the product code
    When Add to wishlist

  Scenario: Write a review
    Given Open product page
    And I can see the product code
    When Click on tab review
    And I can see write a review button
    When Fill form
    Then Submit review form
    And Check alert message
