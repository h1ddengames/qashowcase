package com.h1ddengames.framework.pages.spree.pagefactoryPOM;

import com.h1ddengames.framework.utils.CommonSeleniumTasks;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
Contains navigation methods.
Since navigation is the same across all pages, the elements contained here will
always be used by every other page class.
*/
public class BasePagePageFactory extends CommonSeleniumTasks {
    public String baseURL = "http://spree.shiftedtech.com";

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected JavascriptExecutor driverJSExecutor;

    @FindBy(linkText = "Home") private WebElement homeButton;
    @FindBy(linkText = "Login") private WebElement loginButton;
    @FindBy(css=".cart-info") private WebElement cartButton;
    @FindBy(linkText = "Logout") private WebElement logoutButton;

    @Override protected WebDriver getDriver() { return driver; }
    @Override protected WebDriverWait getDriverWait() { return driverWait; }
    @Override protected JavascriptExecutor getDriverJSExecutor() { return driverJSExecutor; }

    public void goToHomePage() { homeButton.click(); }
    public void goToLoginPage() { loginButton.click(); }
    public void goToCart() {
        cartButton.click();
    }
    public void logOut() {
        logoutButton.click();
    }
}
