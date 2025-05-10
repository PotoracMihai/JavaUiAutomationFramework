Feature: Search functionality tests

  @run
  Scenario: The system displays relevant products when a valid keyword is searched
    Given The "/" endpoint is accessed
    And The "searchInput" from "HomePage" is populated with "iphone"
    When The "searchBtn" from "HomePage" is clicked
    Then The search results contain at least one product with title containing "iphone"

  @run
  Scenario: The system displays a message when searching for an invalid product
    Given The "/" endpoint is accessed
    And The "searchInput" from "HomePage" is populated with "abcdefxyz123"
    When The "searchBtn" from "HomePage" is clicked
    Then The message "There is no product that matches the search criteria." is displayed

  @run
  Scenario Outline: The system handles different casing in search keywords
    Given The "/" endpoint is accessed
    And The "searchInput" from "HomePage" is populated with "<keyword>"
    When The "searchBtn" from "HomePage" is clicked
    Then The search results contain at least one product with title containing "iphone"
    Examples:
      | keyword   |
      | Iphone    |
      | IPHONE    |
      | iPhOnE    |