Feature: The Register flow test suite

  @run
  Scenario: The system redirects the user to account page after successful register with valid data
    Given The Home Page is displayed
    And Register Page is accessed from the Home Page buttons
    And The register from is populated with data
    And The privacy toggle bar is enabled
    When The continueButton is clicked
    Then the URL contains the following keyword "success"

  @run
  Scenario: The system keeps the user on Register page when registering without accepting the privacy rules
    Given The Home Page is displayed
    And Register Page is accessed from the Home Page buttons
    And The register from is populated with data
    When The continueButton is clicked
    Then the URL contains the following keyword "register"