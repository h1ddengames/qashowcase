package com.h1ddengames.framework.pages.spree.objectrepoPOM;

import com.h1ddengames.framework.scriptbases.ScriptBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageObjectRepository extends BasePageObjectRepository {

    public HomePageObjectRepository(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
        this.driverJSExecutor = (JavascriptExecutor) driver;
    }

    public void checkLoginSuccessMessage() {
        WebElement loginSuccessMessage = driver.findElement(objectRepositoryManager.getByFromObjectRepositoryLocator("HomePage.LoginSuccessAlert"));
        assertThat(loginSuccessMessage.getText()).startsWith("Logged").endsWith("successfully");
    }

    public void checkSignoutSuccessMessage() {
        WebElement signoutSuccessMessage = driver.findElement(objectRepositoryManager.getByFromObjectRepositoryLocator("HomePage.LogoutSuccessAlert"));
        Assert.assertEquals(signoutSuccessMessage.getText(), "Signed out successfully.");
    }
}
