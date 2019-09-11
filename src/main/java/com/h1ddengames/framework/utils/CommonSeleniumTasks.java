package com.h1ddengames.framework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/*
Contains wrapper methods that extend the default Selenium functionality
that can be used in any script.
*/
public abstract class CommonSeleniumTasks {

    protected abstract WebDriver getDriver();
    protected abstract WebDriverWait getDriverWait();
    protected abstract JavascriptExecutor getDriverJSExecutor();
    protected PropertyFileObjectRepositoryManager objectRepositoryManager = PropertyFileObjectRepositoryManager.getInstance();

    public void checkPageTitle(String expectedTitle) {
        String actualTitle = getDriver().getTitle();
        assertThat(actualTitle, equalTo(expectedTitle));
    }

    // Abstracts away having to type "objectRepositoryManager.getByFromObject..."
    public boolean isElementPresent(String by) {
        return isElementPresent(objectRepositoryManager.getByFromObjectRepositoryLocator(by));
    }

    /**
     * Check to see if a WebElement exists on the page.
     * @param by The locator used to find the WebElement.
     * @return True if the element is found, false if the element is not found.
     */
    public boolean isElementPresent(By by) {
        boolean present;
        try {
            getDriver().findElement(by);
            present = true;
        } catch (NoSuchElementException e) {
            present = false;
        }
        return present;
    }

    public String getElementText(String by) {
        return getElementText(objectRepositoryManager.getByFromObjectRepositoryLocator(by));
    }

    public String getElementText(By by) {
        WebElement element = getDriver().findElement(by);
        return element.getText();
    }

    // Abstracts away having to type "objectRepositoryManager.getByFromObject..."
    public void clickElement(String by) {
        clickElement(objectRepositoryManager.getByFromObjectRepositoryLocator(by));
    }

    /**
     * Uses WebDriverWait to wait until a WebElement is clickable, then clicks it.
     * @param by The locator used to find the WebElement.
     */
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

    // Abstracts away having to type "objectRepositoryManager.getByFromObject..."
    public void enterDataIntoTextbox(String by, String data) {
        enterDataIntoTextbox(objectRepositoryManager.getByFromObjectRepositoryLocator(by), data);
    }

    /**
     * Uses WebDriverWait to wait until a WebElement that is a textbox is both visible and clickable, before clicking it
     * clearing any text already contained in the textbox, then typing data into the textbox.
     * @param by The locator used to find the WebElement.
     * @param data The string to be entered into the textbox.
     */
    public void enterDataIntoTextbox(By by, String data) {
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
