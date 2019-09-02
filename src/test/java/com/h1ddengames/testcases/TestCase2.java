package com.h1ddengames.testcases;

import com.h1ddengames.framework.ScriptBase;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

/*
Contains test scripts using the function driven methods that also extends Selenium functionality.
*/
public class TestCase2 extends ScriptBase {
    @BeforeClass
    public void setURL() {
        baseURL = "http://spree.shiftedtech.com";
    }

    // Methods for function driven tests using methods
    // from the framework being created that extends Selenium functionality
    // such as ScriptBase.clickElement() and ScriptBase.enterDataIntoElement()
    public void goToLoginPage() {
        driver.navigate().to(baseURL);
        clickElement(By.linkText("Login"));
    }

    public void login(String email, String password) {
        enterDataIntoElement(By.id("spree_user_email"), email);
        enterDataIntoElement(By.id("spree_user_password"), password);
        clickElement(By.name("commit"));
    }

    public void signout() {
        clickElement(By.linkText("Logout"));
    }

    public void checkLoginSuccessMessageIsExactlyAsExpected() {
        assertElementText(By.xpath("//div[@id='content']/div[contains(text(),'Logged in successfully')]"), "Logged in successfully");
    }

    public void checkLoginSuccessMessage() {
        WebElement loginSuccessMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Logged in successfully')]"));
        assertThat(loginSuccessMessage.getText()).startsWith("Logged").endsWith("successfully");
    }

    public void checkLoginFailedMessage() {
        WebElement loginFailedMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Invalid email or password.')]"));
        //Assert.assertEquals(loginFailedMessage.getText(), "Invalid email or password.");
        assertThat(loginFailedMessage.getText(), equalTo("Invalid email or password."));
    }

    public void checkSignoutSuccessMessage() {
        WebElement signoutSuccessMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Signed out successfully.')]"));
        Assert.assertEquals(signoutSuccessMessage.getText(), "Signed out successfully.");
    }

    @Test()
    public void positiveLoginCaseWithFunctions() {
        goToLoginPage();
        login("shiftedtech0000@gmail.com", "shiftedtech");
        checkLoginSuccessMessage();
    }

    @Test()
    public void positiveLoginCaseWithScriptBaseFunctions() {
        goToLoginPage();
        login("shiftedtech0000@gmail.com", "shiftedtech");
        checkLoginSuccessMessageIsExactlyAsExpected();
    }

    @Test()
    public void negativeLoginCaseWithFunctions() {
        goToLoginPage();
        login("shiftedtech0000@gmail.com", "shiftedtec");
        checkLoginFailedMessage();
    }

    @Test()
    public void logoutCaseWithFunctions() {
        goToLoginPage();
        login("shiftedtech0000@gmail.com", "shiftedtech");
        checkLoginSuccessMessage();
        signout();
        checkSignoutSuccessMessage();
    }

    @Test(groups = { "broken" })
    public void brokenTestWillNotRunInThisSuite() {
        System.out.println("Test will not run when using testng.xml.");
    }
}
