Feature: Spree Login Functionality
    # Product owner will write certain scenarios for you to create here:
    # 1. Valid user with valid password
    # 2. Logged in user does not see login option but does see logout option
    # 3. Valid user with invalid password
    # 4. Invalid user with valid password
    # 5. Invalid user with invalid password
    # 6. Valid user with no password
    # 7. No user and no password
    # 8. Valid user with valid password displays valid account information

# For each scenario, either a BA or the product owner will write out the steps below:
    # (But if they don't, then it's up to you, the QA person to write the steps
    # and confirm/review with the product owner)
# Try to use wording that is generic enough for you to reuse step definition methods.
# or try to use regular expressions to allow users of your BDD framework to use looser language.
    # For example:
    # When user clicks on "Navigation.Login" link/button
    # In the above example, a feature file/scenario creator can use either the word link or button
    # but the same step method will be used.
# Use Given for the things that are expected at the start of a test.
    # For example:
    # Given a user browses to "http://spree.shiftedtech.com"
    # OR
    # Given a user browses to "http://spree.shiftedtech.com"
    # And user clicks on "Navigation.Login" button
# Use When for things that are considered actions.
    # For example:
    # When user enters "shiftqa01@gmail.com" into "LoginPage.EmailTextbox"
    # When user clicks "Navigation.Login" link
# Use Then for things that require verification.
    # For example:
    # Then home page will display
    # Then login page will display
# Use And for things that need to happen in addition to the step before it.
    # For example:
    # When user enters "shiftqa01@gmail.com" into "LoginPage.EmailTextbox"
    # And user enters "shiftedtech" at "LoginPage.PasswordTextbox"
    # And user clicks on "LoginPage.LoginButton" button
    # In the above example, we are giving a username and password then clicking login.
    # Since these steps fit into a larger step and it makes sense to group them, we group them.
    # We can use And with Given when we want to specify more than one start condition,
    # with Then when we want to specify multiple points that we need to verify, and with When
    # when we want to do multiple actions together as a parts of a larger action such as logging in.
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

#Scenario: 6. Valid user with no password

#Scenario: 7. No user and no password

#Scenario: 8. Valid user with valid password displays valid account information
