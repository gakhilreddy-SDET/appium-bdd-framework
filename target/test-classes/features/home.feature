Feature: Home Screen Functionality

  Background:
    Given I launch the application
    When I enter username "akhil@gmail.com"
    And I enter password "889702"
    And I select DOB "1998-06-01"
    And I click submit
    Then I should see home screen

  @smoke
  Scenario: Verify Hotels Tab
    Given I am on home screen
    When I click on Hotels tab
    Then I should see hotel cards
    And product images should be displayed
    And rating images should be displayed

  Scenario: Verify Holidays Tab
    Given I am on home screen
    When I click on Holidays tab
    Then I should see holiday cards
    And I should see 1 or more product cards

  Scenario: Verify All Tab
    Given I am on home screen
    When I click on All tab
    Then I should see all products
    And product images should be displayed

  Scenario: Verify Scroll Functionality
    Given I am on home screen
    When I scroll through cards
    Then I should see additional cards

  Scenario: Validate Product Details
    Given I am on home screen
    Then I should see product title
    And I should see location
    And I should see rating
    And I should see price
    And I should see board type

  Scenario: Verify Product Card Elements
    Given I am on home screen
    Then the first product card should be displayed
    And product images should be displayed
    And rating images should be displayed
    And I should see product title
    And I should see location
    And I should see rating
    And I should see price
    And I should see board type

  Scenario: Click on First Product
    Given I am on home screen
    When I click on the first product card
    Then the first product card should be displayed
