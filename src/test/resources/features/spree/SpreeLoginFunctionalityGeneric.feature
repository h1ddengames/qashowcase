Feature: Spree Login Functionality

@Functional @Positive
Scenario: 1. Valid user with valid password
    Given an unvalidated user browses to "http://spree.shiftedtech.com"
    Then "home page" will display
    When user clicks "Navigation.Login" link
    Then "login page" will display
    When user enters "shiftqa01@gmail.com" into "LoginPage.EmailTextbox"
    And user enters "shiftedtech" into "LoginPage.PasswordTextbox"
    And user clicks "LoginPage.LoginButton" button
    Then "home page" will display
    And message in "HomePage.LoginSuccessAlert" displays as "Logged in successfully"

@Functional @Positive @Usability
Scenario: 2. Logged in user does not see login option but does see logout option
    Given an unvalidated user browses to "http://spree.shiftedtech.com"
    Then "home page" will display
    When user clicks "Navigation.Login" link
    Then "login page" will display
    When user enters "shiftqa01@gmail.com" into "LoginPage.EmailTextbox"
    And user enters "shiftedtech" into "LoginPage.PasswordTextbox"
    And user clicks "LoginPage.LoginButton" button
    Then "home page" will display
    And message in "HomePage.LoginSuccessAlert" displays as "Logged in successfully"
    Then link found by "Navigation.Login" is "not displayed"
    Then button found by "Navigation.Logout" is "displayed"

#Scenario: 3. Valid user with invalid password

#Scenario: 4. Invalid user with valid password

#Scenario: 5. Invalid user with invalid password

#Scenario: 6. Valid user with no password

#Scenario: 7. No user and no password

#Scenario: 8. Valid user with valid password displays valid account information
