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

public class SpreeLoginStepsGeneric extends ScriptBase {

    @Before
    public void before() {
        setup("CH");
    }

    @After
    public void after() {
        tearDown();
    }

    @Given("an unvalidated user browses to {string}")
    public void an_unvalidated_user_browses_to(String url) {
        
    }

    @Then("{string} will display")
    public void will_display(String page) {
        
    }

    @When("user clicks {string} link/button")
    public void user_clicks_link(String button) {
        
    }

    @When("user enters {string} into {string}")
    public void user_enters_into(String data, String by) {
        
    }

    @Then("message in {string} displays as {string}")
    public void login_success_message_displays_in_as(String string, String string2) {
        
    }

    @Then("link/button found by {string} is {string}")
    public void link_found_by_is(String by, String message) {
        
    }
}
