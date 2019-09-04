package com.h1ddengames.framework.pages.spree.basicPOM;

import com.h1ddengames.framework.scriptbases.ScriptBase;

public class SpreeScriptBase extends ScriptBase {
    private HomePage homePage;
    private LoginPage loginPage;
    private CartPage cartPage;

    public HomePage homePage() {
        if(homePage == null) {
            homePage = new HomePage(driver);
            // When homePage is null, that means the script has not used driver.navigate() to get
            // to the homePage's baseURL. Calling any other method than navigate... will cause
            // WebElements to not be found.
            driver.navigate().to(homePage.baseURL);
        }
        return homePage;
    }

    public LoginPage loginPage() {
        if(loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public CartPage cartPage() {
        if(cartPage == null) {
            cartPage = new CartPage(driver);
        }
        return cartPage;
    }
}
