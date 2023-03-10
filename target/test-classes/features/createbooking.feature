@reservation_tests
Feature: user should be able to create reservation

  Scenario: user can create and delete hotel reservation
    Given user creates a new reservation
    And user gives the information required for the reservation
    When user creates hotel reservation
    Then user verifies that the reservation was created successfully
    And user deletes the created reservation