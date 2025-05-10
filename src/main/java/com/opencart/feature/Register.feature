Feature: The Register flow test suite

  @run
  Scenario: The system redirects the user to account page after successful register with valid data
    Given The "/" endpoint is accessed
    And Register Page is accessed from the Home Page buttons
    And The register from is populated with data
    And The "privacyToggleBar" from "RegisterPage" is clicked
    When The "continueBtn" from "RegisterPage" is clicked
    Then the URL contains the following keyword "success"

  @run
  Scenario: The system keeps the user on Register page when registering without accepting the privacy rules
    Given The "/" endpoint is accessed
    And Register Page is accessed from the Home Page buttons
    And The register from is populated with data
    When The "continueBtn" from "RegisterPage" is clicked
    Then the URL contains the following keyword "route=account/register&language=en-gb"

  @run
  Scenario Outline: Error message is displayed when registering within invalid <errorFieldName> length
    Given The "/" endpoint is accessed
    And Register Page is accessed from the Home Page buttons
    And the register fromm is populated as following:
      | firstName | <firstName> |
      | lastName  | <lastName>  |
      | email     | RANDOM      |
      | password  | <password>  |
    When The "continueBtn" from "RegisterPage" is clicked
    Then the following list of error messages is displayed:
      | <errorFieldName> must be between <min> and <max> characters! |
      | Warning: You must agree to the Privacy Policy!               |
    Examples:
      | firstName |  | lastName                                       | password                                                | errorFieldName | min | max |
      | Random    |  | Lungu                                          | 321                                                     | Password       | 4   | 20  |
      | Random    |  | Lungu                                          | 1234567890123456789341234567890123566787868768767868768 | Password       | 4   | 20  |
      | Random    |  | 1234567890123456789012312345678901234567890123 | 12345678901234567890123                                 | Last Name      | 1   | 32  |

  @fail
  Scenario: User cannot register without entering any data
    Given The "/" endpoint is accessed
    And Register Page is accessed from the Home Page buttons
    When The "continueBtn" from "RegisterPage" is clicked
    Then the following list of error messages is displayed:
      | First Name must be between 1 and 32 characters! |
      | Last Name must be between 1 and 32 characters!  |
      | E-Mail Address does not appear to be valid!     |
      | Password must be between 4 and 20 characters!   |
      | Warning: You must agree to the Privacy Policy!  |

  @fail
  Scenario: User cannot register with invalid email format
    Given The "/" endpoint is accessed
    And Register Page is accessed from the Home Page buttons
    And the register fromm is populated as following:
      | firstName | Random  |
      | lastName  | Random  |
      | email     | invalid_email |
      | password  | ValidPass123 |
    And The "privacyToggleBar" from "RegisterPage" is clicked
    When The "continueBtn" from "RegisterPage" is clicked
    Then the following list of error messages is displayed:
      | E-Mail Address does not appear to be valid! |