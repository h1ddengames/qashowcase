package com.h1ddengames.testcases;

import com.h1ddengames.TestScriptBase;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static com.h1ddengames.framework.matcher.RegexMatcher.*;
import static org.assertj.core.api.Assertions.*;

public class TestCase1 extends TestScriptBase {
    @BeforeClass
    public void setURL() {
        baseURL = "http://spree.shiftedtech.com";
    }

    @Test()
    public void positiveLoginCase() {
        driver.navigate().to(baseURL);

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        WebElement emailTextbox = driver.findElement(By.id("spree_user_email"));
        emailTextbox.clear();
        emailTextbox.sendKeys("shiftedtech0000@gmail.com");

        WebElement passwordTextbox = driver.findElement(By.id("spree_user_password"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys("shiftedtech");

        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();

        WebElement successMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Logged in successfully')]"));

        // A test can only be a test if you assert that something should happen.
        // Additionally, a false positive is worse than a false negative because no one
        // cares to look at a test when it passes. Once the bug slips through this crack,
        // it becomes much harder to find later on.

        // There are two ways to assert in this project: using TestNG's Assert class
        // or Hamcrest's MatcherAssert.
        // The following line is using TestNG's Assert class:
        // Assert.assertEquals(successMessage.getText(), "Logged in successfully");

        // The following line is using Hamcrest's Matcher class:
        //assertThat(successMessage.getText(), equalTo("Logged in successfully"));

        // NOTE: the custom matcher comes from: https://www.vogella.com/tutorials/Hamcrest/article.html
        // Since we are using regex, we can use the .* (PLEASE note the period before the star)
        // after "Logged in " to show we don't care what the rest of the line says
        // 'we just want the line to start with:' "Logged in "
        // The following line is using a custom matcher that extends Hamcrest matchers:
        // assertThat(successMessage.getText(), regexMatches("Logged in .*"));

        // Assertj is an assertion library like Testng's Assert class or Hamcrest's assertThat method.
        // The difference between Assertj and the other assertions is that Assertj allows you to write
        // more "fluent" assertions. That just means it's more English with "proper" sentence structure.
        // The following line is using Assertj's assertion class.
        assertThat(successMessage.getText()).startsWith("Logged").endsWith("successfully");

        // We delete cookies so that other tests that use the same instance of WebDriver don't
        // get messed up due to previous login data.
        driver.manage().deleteAllCookies();
    }

    @Test()
    public void negativeLoginCase() {
        driver.navigate().to(baseURL);

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        WebElement emailTextbox = driver.findElement(By.id("spree_user_email"));
        emailTextbox.clear();
        emailTextbox.sendKeys("shiftedtech0000@gmail.com");

        WebElement passwordTextbox = driver.findElement(By.id("spree_user_password"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys("shiftedtec");

        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();

        WebElement loginFailedMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Invalid email or password.')]"));
        //Assert.assertEquals(loginFailedMessage.getText(), "Invalid email or password.");
        assertThat(loginFailedMessage.getText(), equalTo("Invalid email or password."));
        driver.manage().deleteAllCookies();
    }

    @Test()
    public void logoutCase() {
        driver.navigate().to(baseURL);

        WebElement loginLink = driver.findElement(By.linkText("Login"));
        loginLink.click();

        WebElement emailTextbox = driver.findElement(By.id("spree_user_email"));
        emailTextbox.clear();
        emailTextbox.sendKeys("shiftedtech0000@gmail.com");

        WebElement passwordTextbox = driver.findElement(By.id("spree_user_password"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys("shiftedtech");

        WebElement loginButton = driver.findElement(By.name("commit"));
        loginButton.click();

        WebElement successMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Logged in successfully')]"));
        Assert.assertEquals(successMessage.getText(), "Logged in successfully");

        WebElement logoutLink = driver.findElement(By.linkText("Logout"));
        logoutLink.click();

        WebElement signoutSuccessMessage = driver.findElement(
                By.xpath("//div[@id='content']/div[contains(text(),'Signed out successfully.')]"));
        Assert.assertEquals(signoutSuccessMessage.getText(), "Signed out successfully.");
        driver.manage().deleteAllCookies();
    }
}
