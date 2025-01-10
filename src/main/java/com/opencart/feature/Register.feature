Feature: The Register flow test suite

  @run
  Scenario: The system redirects the user to account page after successful register with valid data
    Given The "https://tekwillacademy-opencart.online/" is accessed
    And Register Page is accessed from the Home Page buttons
    And The register from is populated with data
    And The privacy toggle bar is enabled
    When The continueButton is clicked
    Then the URL contains the following keyword "success"

  @run
  Scenario: The system keeps the user on Register page when registering without accepting the privacy rules
    Given The "https://tekwillacademy-opencart.online/" is accessed
    And Register Page is accessed from the Home Page buttons
    And The register from is populated with data
    When The continueButton is clicked
    Then the URL contains the following keyword "route=account/register&language=en-gb"

  @run
  Scenario Outline: Error message is displayed when registering within invalid <errorFieldName> length
    Given The "https://tekwillacademy-opencart.online/" is accessed
    And Register Page is accessed from the Home Page buttons
    And the register fromm is populated as following:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | email     | RANDOM      |
      | password  | <password>  |
    When The continueButton is clicked
    Then the following list of error messages is displayed:
      | <errorFieldName> must be between <min> and <max> characters! |
      | Warning: You must agree to the Privacy Policy!               |
    Examples:
      | firstName |  | lastName                                       | password                                                | errorFieldName | min | max |
      | Random    |  | Lungu                                          | 321                                                     | Password       | 4   | 20  |
      | Random    |  | Lungu                                          | 1234567890123456789341234567890123566787868768767868768 | Password       | 4   | 20  |
      | Random    |  | 1234567890123456789012312345678901234567890123 | 12345678901234567890123                                 | Last Name      | 1   | 32  |