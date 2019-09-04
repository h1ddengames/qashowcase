package com.h1ddengames.framework.pages.spree.basicPOM;

import com.h1ddengames.framework.scriptbases.ScriptBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
        this.driverJSExecutor = (JavascriptExecutor) driver;
    }

    public void checkLoginSuccessMessage() {
        WebElement loginSuccessMessage = driver.findElement(
                By.cssSelector(".alert-success"));
        assertThat(loginSuccessMessage.getText()).startsWith("Logged").endsWith("successfully");
    }

    public void checkSignoutSuccessMessage() {
        WebElement signoutSuccessMessage = driver.findElement(
                By.cssSelector(".alert-notice"));
        Assert.assertEquals(signoutSuccessMessage.getText(), "Signed out successfully.");
    }
}
