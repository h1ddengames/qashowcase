package com.h1ddengames.framework.pages.spree.pagefactoryPOM;

import com.h1ddengames.framework.scriptbases.ScriptBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePagePageFactory extends BasePagePageFactory {

    @FindBy(css = ".alert-success") private WebElement loginSuccessMessage;
    @FindBy(css = ".alert-notice") private WebElement logoutSuccessMessage;

    public HomePagePageFactory(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
        this.driverJSExecutor = (JavascriptExecutor) driver;

        PageFactory.initElements(driver, this);
    }

    public void checkLoginSuccessMessage() {
        assertThat(loginSuccessMessage.getText()).startsWith("Logged").endsWith("successfully");
    }

    public void checkLogoutSuccessMessage() {
        Assert.assertEquals(logoutSuccessMessage.getText(), "Signed out successfully.");
    }
}
