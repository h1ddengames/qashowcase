package com.h1ddengames.framework.pages.spree.basicPOM;

import com.h1ddengames.framework.scriptbases.ScriptBase;
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

    public void login(String email, String password) {
        goToLoginPage();
        enterUsernameAndPassword(email, password);
    }

    public void enterUsernameAndPassword(String email, String password) {
        enterDataIntoTextbox(By.id("spree_user_email"), email);
        enterDataIntoTextbox(By.id("spree_user_password"), password);
        clickElement(By.name("commit"));
    }

    public void checkLoginFailedMessage() {
        WebElement loginFailedMessage = driver.findElement(
                By.cssSelector(".alert-error"));
        MatcherAssert.assertThat(loginFailedMessage.getText(), equalTo("Invalid email or password."));
    }
}
