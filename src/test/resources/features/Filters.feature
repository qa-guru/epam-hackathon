Feature: Filters usage

Scenario: Filter by price
  Given Open brands page
  When Use price filter
  Then Check if the price is right

Scenario: Filter by size
  Given Open brands page
  When Use size filter
  Then Check if the size is right

Scenario: Filter by color
  Given Open brands page
  When Use color filter
  Then Check if the color is right

Scenario: Filter by gender
  Given Open brands page
  When Use gender filter
  Then Check if the gender is right

Scenario: Filter by collection
  Given Open brands page
  When Use collection filter
  Then Check if the collection is right

Scenario: Filter by category
  Given Open brands page
  When Use category filter
  Then Check if the category is right

Scenario: Filter by Brand
  Given Open brands page
  When Use Brand filter
  Then Check if the Brand is right