Feature: User Registration

  Scenario: Successful registration with valid data
    Given the user is on the login page
    When the user clicks the "Registration" button
    And enters "Ivan" in the First Name field
    And enters "Ivanov" in the Last Name field
    And enters "01/01/1990" in the Date of Birth field
    And enters "ivan.ivanov1978@gmail.com" in the Email field
    And enters "Password123!" in the Password field
    And enters "Password123!" in the Confirm Password field
    And clicks the "Submit" button
    Then the user should be successfully registered and redirected to the login page

  Scenario: Registration with all fields empty
    Given the user is on the login page
    When the user clicks the "Registration" button
    And the user submits the registration form without filling any fields
    Then all required field messages are displayed

  Scenario: Registration button disabled when First Name is empty
    Given the user is on the login page
    When the user clicks the "Registration" button
    And enters "" in the First Name field
    And enters "Ivanov" in the Last Name field
    And enters "01/01/1990" in the Date of Birth field
    And enters "ivan.ivanov1998@gmail.com" in the Email field
    And enters "Password123!" in the Password field
    And enters "Password123!" in the Confirm Password field
    Then the Submit button should be disabled