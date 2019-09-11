Feature: Spree Login Functionality

Scenario: 1. Valid user with valid password
    Given an unvalidated user browses to "http://spree.shiftedtech.com"
    Then home page will display
    When user clicks "Navigation.Login" link
    Then login page will display
    When user enters "shiftqa01@gmail.com" into "LoginPage.EmailTextbox"
    And user enters "shiftedtech" at "LoginPage.PasswordTextbox"
    And user clicks on "LoginPage.LoginButton" button
    Then home page will display
    And login success message displays in "HomePage.LoginSuccessAlert" as "Logged in successfully"

Scenario: 2. Logged in user does not see login option but does see logout option
    Given an unvalidated user browses to "http://spree.shiftedtech.com"
    Then home page will display
    When user clicks "Navigation.Login" link
    Then login page will display
    When user enters "shiftqa01@gmail.com" into "LoginPage.EmailTextbox"
    And user enters "shiftedtech" at "LoginPage.PasswordTextbox"
    And user clicks on "LoginPage.LoginButton" button
    Then home page will display
    And login success message displays in "HomePage.LoginSuccessAlert" as "Logged in successfully"
    Then login link found by "Navigation.Login" is not displayed
    Then logout link found by "Navigation.Logout" is displayed

#Scenario: 3. Valid user with invalid password

#Scenario: 4. Invalid user with valid password

#Scenario: 5. Invalid user with invalid password

#Scenario: 6. Valid user with valid password displays valid account information
