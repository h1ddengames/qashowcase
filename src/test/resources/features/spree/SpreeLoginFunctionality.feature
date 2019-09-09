Feature: Spree Login Functionality - 1

Scenario: 1. Valid user with valid password
    Given an unvalidated user
    When user browses to "http://spree.shiftedtech.com"
    Then home page will display
    When user clicks "Navigation.Login" link
    Then login page will display
    When user enters "shiftqa01@gmail.com" into "LoginPage.EmailTextbox"
    And user enters "shiftedtech" at "LoginPage.PasswordTextbox"
    And user clicks on "LoginPage.LoginButton" button
    Then home page will display
    And login success message displays in "HomePage.LoginSuccessAlert" as "Logged in Successfully"
