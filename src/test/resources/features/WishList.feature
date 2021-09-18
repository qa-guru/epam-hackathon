Feature: Wishlist feature

  Scenario: Create wishlist
    Given Open wishlist page
    Then Check empty wishlist
    When Create a new list
    Then Check wishlist
    When Remove wishlist
    Then Check empty wishlist
