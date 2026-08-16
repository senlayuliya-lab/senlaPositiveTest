@api @regression @smoke
Feature: Create application via API

  @positive
  Scenario: Successfully create birth application
    Given I prepare data for birth application
    When I send request to create application
    Then response status is 200
    And response has application ID greater than 0
    And application type in response is "birth"

  @negative @validation
  Scenario: Error when creating application without last name
    Given I prepare data without last name
    When I send request to create application
    Then response status is 500