Feature: Pagination feature

  Scenario: Go from first page to last
    Given Open store brand page
    When Go forward 3 pages page by page
    Then Check if it is a 4th page
