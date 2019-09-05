package com.h1ddengames.framework.pages.spree.pagefactoryPOM;

import com.h1ddengames.framework.scriptbases.ScriptBase;

public class SpreePageFactoryScriptBase extends ScriptBase {
    private HomePagePageFactory homePage;
    private LoginPagePageFactory loginPage;
    private CartPagePageFactory cartPage;

    public HomePagePageFactory homePage() {
        if(homePage == null) {
            homePage = new HomePagePageFactory(driver);
            // When homePage is null, that means the script has not used driver.navigate() to get
            // to the homePage's baseURL. Calling any other method than navigate... will cause
            // WebElements to not be found.
            driver.navigate().to(homePage.baseURL);
        }
        return homePage;
    }

    public LoginPagePageFactory loginPage() {
        if(loginPage == null) {
            loginPage = new LoginPagePageFactory(driver);
        }
        return loginPage;
    }

    public CartPagePageFactory cartPage() {
        if(cartPage == null) {
            cartPage = new CartPagePageFactory(driver);
        }
        return cartPage;
    }
}
