Feature: Registration

    #  Positive scenario
    #  Use RANDOM_EMAIL to get generated email each time

  Scenario: Register to the account
    Given Open Registration page
    And Fill in registration data
      | title | firstname | lastname  | email        | password | confirmation |
      | Miss  | Epamsha   | Epamovich | RANDOM_EMAIL | admin123 | admin123     |
    And Agree with terms and conditions
    And Confirm consent
    And Click to register
    Then Check that user is registered

      # Negative scenarios with invalid data

  Scenario: Try to register with existing account
    Given Open Registration page
    And Fill in registration data
      | title | firstname | lastname  | email                    | password | confirmation |
      | Miss  | Epamsha   | Epamovich | EpamshaEpamovich@mail.ru | admin123 | admin123     |
    And Agree with terms and conditions
    And Confirm consent
    And Click to register
    Then Check error that user already exists

  Scenario: Try to register with invalid email
    Given Open Registration page
    And Fill in registration data
      | title | firstname | lastname  | email         | password | confirmation |
      | Miss  | Epamsha   | Epamovich | wtfemail.epam | admin123 | admin123     |
    And Agree with terms and conditions
    And Confirm consent
    And Click to register
    Then Check email error

##       Negative scenarios with empty data

  Scenario: Try to register with empty first name
    Given Open Registration page
    And Fill in registration data
      | title | firstname | lastname  | email                    | password | confirmation |
      | Miss  |           | Epamovich | EpamshaEpamovich@mail.ru | admin123 | admin123     |
    And Agree with terms and conditions
    And Confirm consent
    And Click to register
    Then Check empty first name error

  Scenario: Try to register with empty last name
    Given Open Registration page
    And Fill in registration data
      | title | firstname | lastname | email                    | password | confirmation |
      | Miss  | Epamsha   |          | EpamshaEpamovich@mail.ru | admin123 | admin123     |
    And Agree with terms and conditions
    And Confirm consent
    And Click to register
    Then Check empty last name error

  Scenario: Try to register with empty email
    Given Open Registration page
    And Fill in registration data
      | title | firstname | lastname  | email | password | confirmation |
      | Miss  | Epamsha   | Epamovich |       | admin123 | admin123     |
    And Agree with terms and conditions
    And Confirm consent
    And Click to register
    Then Check email error
