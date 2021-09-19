Feature: Quick order feature

  Scenario: Add to bag
    Given Open quick order page
    When Add order by code
    Then Check order data
    When Add to basket

 # Scenario: Reset form
 #   Given Open quick order page
 #   When Add order by code
 #   When Reset form
 #   Then Check empty data

  Scenario: Delete order
    Given Open quick order page
    When Add order by code
    When Delete order
    Then Check empty data


