package com.h1ddengames.framework.pages.spree.basicPOM;

import com.h1ddengames.framework.scriptbases.ScriptBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
        this.driverJSExecutor = (JavascriptExecutor) driver;
    }
}
