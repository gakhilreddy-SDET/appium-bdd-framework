Feature: Login Functionality

  @smoke
  Scenario: Valid Login
    Given I launch the application
    When I enter username "akhil@gmail.com"
    And I enter password "889702"
    And I select DOB "1998-06-01"
    And I click submit
    Then I should see home screen

  @negative
    #This scenario is not fesible as app is taking any input as valid
  Scenario: Invalid Login
    Given I launch the application
    When I enter username "invalid@gmail.com"
    And I enter password "wrong"
    And I select DOB "1990-01-01"
    And I click submit
    Then I should see validation error