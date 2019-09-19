package com.h1ddengames.framework.steps.spree;

import com.h1ddengames.framework.scriptbases.ScriptBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SpreeLoginSteps extends ScriptBase {

    @Before
    public void before() {
        setup("CH");
    }

    @After
    public void after() {
        tearDown();
    }

//    @Given("an unvalidated user browses to {string}")
//    public void an_unvalidated_user_browses_to(String string) {
//        getDriver().navigate().to(string);
//        assertThat(isElementPresent("Navigation.Login"), equalTo(true));
//    }
//
//    @Then("home page will display")
//    public void home_page_will_display() {
//        checkPageTitle("Spree Demo Site");
//    }
//
//    @When("user clicks {string} link")
//    public void user_clicks_link(String string) {
//        clickElement(string);
//    }
//
//    @Then("login page will display")
//    public void login_page_will_display() {
//        checkPageTitle("Login - Spree Demo Site");
//    }
//
//    @When("user enters {string} into {string}")
//    public void user_enters_into(String string, String string2) {
//        enterDataIntoTextbox(string2, string);
//    }
//
//    @And("user enters {string} at {string}")
//    public void user_enters_at(String string, String string2) {
//        enterDataIntoTextbox(string2, string);
//    }
//
//    @And("user clicks on {string} button")
//    public void user_clicks_on_button(String string) {
//        clickElement(string);
//    }
//
//    @Then("login success message displays in {string} as {string}")
//    public void login_success_message_displays_in_as(String string, String string2) {
//        assertThat(isElementPresent("Navigation.Logout"), equalTo(true));
//        assertThat(getElementText(string), equalTo(string2));
//    }
//
//    @Then("login link found by {string} is not displayed")
//    public void login_link_found_by_is_not_displayed(String string) {
//        assertThat(isElementPresent("Navigation.Login"), equalTo(false));
//    }
//
//    @Then("logout link found by {string} is displayed")
//    public void logout_link_found_by_is_displayed(String string) {
//        assertThat(isElementPresent("Navigation.Logout"), equalTo(true));
//    }
}
