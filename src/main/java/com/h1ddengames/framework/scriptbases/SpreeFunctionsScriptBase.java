package com.h1ddengames.framework.scriptbases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.hamcrest.MatcherAssert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

/*
Contains the non-POM function driven methods that will be used in scripts.

Since this class extends ScriptBase, we get all the variables and methods from
ScriptBase while being able to change the functionality to suit a specific website: Spree.

An added benefit is that all the non-POM function driven methods will get quarantined into
a single class file (the opposite of the wrapper methods found in the base script since
all these methods belong specifically to Spree rather than being able to be used on any website).

The downside of using a function driven or POM framework is that you wont be able to use it
for any other website than the one you're creating the functions for.
*/
public class SpreeFunctionsScriptBase extends ScriptBase {
    @BeforeClass
    public void setURL() { baseURL = "http://spree.shiftedtech.com"; }

    // Methods for function driven tests using methods
    // from the framework being created that extends Selenium functionality
    // such as ScriptBase.clickElement() and ScriptBase.enterDataIntoElement()
    public void goToLoginPage() {
        driver.navigate().to(baseURL);
        clickElement(By.linkText("Login"));
    }

    public void enterUsernameAndPassword(String email, String password) {
        enterDataIntoElement(By.id("spree_user_email"), email);
        enterDataIntoElement(By.id("spree_user_password"), password);
        clickElement(By.name("commit"));
    }

    public void login(String email, String password, boolean shouldLoginWork) {
        goToLoginPage();
        enterUsernameAndPassword(email, password);

        if(shouldLoginWork) {
            checkLoginSuccessMessage();
        } else {
            checkLoginFailedMessage();
        }
    }

    public void logOut() {
        clickElement(By.linkText("Logout"));
        checkSignoutSuccessMessage();
    }

    public void checkLoginSuccessMessage() {
        WebElement loginSuccessMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Logged in successfully')]"));
        assertThat(loginSuccessMessage.getText()).startsWith("Logged").endsWith("successfully");
    }

    public void checkLoginFailedMessage() {
        WebElement loginFailedMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Invalid email or password.')]"));
        MatcherAssert.assertThat(loginFailedMessage.getText(), equalTo("Invalid email or password."));
    }

    public void checkSignoutSuccessMessage() {
        WebElement signoutSuccessMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Signed out successfully.')]"));
        Assert.assertEquals(signoutSuccessMessage.getText(), "Signed out successfully.");
    }
}
