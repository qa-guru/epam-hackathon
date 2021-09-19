Feature: Change password in profile

    #   Negative scenarios

  Scenario: Try to change password with incorrect current password
    Given Open main page
    And Api Login with username as "testaccount@ya.ru" and password as "123456"
    And Open Change password page
    And Fill in currentPassword as "123" and newPassword as "1234567" and corfirmPassword as "1234567"
    And Click update password
    Then Check that error is displayed that current password is incorrect


