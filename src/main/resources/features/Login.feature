Feature: Login to account

  # Positive scenario
  Scenario: Login to the account with correct data
    Given Open Login page
    And Login with username as "loginaccount@ya.ru" and password as "123456"
    Then Check that user as "LOGIN" is logged in

  # Negative scenario
  Scenario: Try to login with incorrect password
    Given Open Login page
    And Login with username as "loginaccount@ya.ru" and password as "123"
    Then Check invalid login data error

  Scenario: Try to login with empty password
    Given Open Login page
    And Login with username as "loginaccount@ya.ru" and password as ""
    Then Check invalid login data error