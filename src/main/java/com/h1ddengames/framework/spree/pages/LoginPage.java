package com.h1ddengames.framework.spree.pages;

import com.h1ddengames.framework.ScriptBase;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
        this.driverJSExecutor = (JavascriptExecutor) driver;
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

    public void enterUsernameAndPassword(String email, String password) {
        enterDataIntoElement(By.id("spree_user_email"), email);
        enterDataIntoElement(By.id("spree_user_password"), password);
        clickElement(By.name("commit"));
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
}
