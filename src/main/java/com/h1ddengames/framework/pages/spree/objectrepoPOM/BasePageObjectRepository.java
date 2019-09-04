package com.h1ddengames.framework.pages.spree.objectrepoPOM;

import com.h1ddengames.framework.utils.CommonSeleniumTasks;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
Contains navigation methods.
Since navigation is the same across all pages, the elements contained here will
always be used by every other page class.
*/
public class BasePageObjectRepository extends CommonSeleniumTasks {
    public String baseURL = "http://spree.shiftedtech.com";

    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected JavascriptExecutor driverJSExecutor;

    @Override protected WebDriver getDriver() { return driver; }
    @Override protected WebDriverWait getDriverWait() { return driverWait; }
    @Override protected JavascriptExecutor getDriverJSExecutor() { return driverJSExecutor; }

    public void goToHomePage() {
        clickElement(objectRepositoryManager.getByFromObjectRepositoryLocator("Navigation.Home"));
    }

    public void goToLoginPage() {
        clickElement(objectRepositoryManager.getByFromObjectRepositoryLocator("Navigation.Login"));
    }

    public void goToCart() {
        clickElement(objectRepositoryManager.getByFromObjectRepositoryLocator("Navigation.Cart"));
    }

    public void logOut() {
        clickElement(objectRepositoryManager.getByFromObjectRepositoryLocator("Navigation.Logout"));
    }
}
