package com.h1ddengames.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
Contains wrapper methods that extend the default Selenium functionality
that can be used in any script.
*/
public abstract class CommonSeleniumTasks {

    protected abstract WebDriver getDriver();
    protected abstract WebDriverWait getDriverWait();
    protected abstract JavascriptExecutor getDriverJSExecutor();
    protected PropertyFileObjectRepositoryManager objectRepositoryManager = PropertyFileObjectRepositoryManager.getInstance();

    public void clickElement(By by) {
        try {
            // ExpectedConditions.elementToBeClickable returns WebElement
            // if expected condition is true otherwise it will throw TimeoutException.
            // It never returns null so we have to use a try/catch block to catch the exception.
            WebElement clickableElement = getDriverWait().until(ExpectedConditions.elementToBeClickable(by));
            clickableElement.click();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void enterDataIntoElement(By by, String data) {
        // ExpectedConditions.and() returns a boolean rather than a WebElement.
        try {
            boolean isTextFieldReady = getDriverWait().until(
                    ExpectedConditions.and(
                            ExpectedConditions.visibilityOfElementLocated(by),
                            ExpectedConditions.elementToBeClickable(by)
                    )
            );

            // Once both conditions are satisfied, then we can store the WebElement into
            // a variable and perform actions on it.
            if(isTextFieldReady) {
                WebElement textField = getDriver().findElement(by);
                textField.click();
                textField.clear();
                textField.sendKeys(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
