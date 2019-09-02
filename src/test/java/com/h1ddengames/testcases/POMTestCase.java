package com.h1ddengames.testcases;

import com.h1ddengames.framework.spree.pages.SpreeScriptBase;
import org.testng.annotations.Test;

/*
Contains test scripts using POM function driven methods that also extends Selenium functionality.

This will keep test scripts small because all the logic is contained within the Page files.

While it takes a bit more work to get POM classes and logic setup, each function is kept within
the page where the function can be used. This means that non-technical people can't call the login
function from the homePage since the login function requires you to be on the loginPage. Additionally,
this means that Page class files will be shorter due to the functions being spread out across multiple pages.
*/
public class POMTestCase extends SpreeScriptBase {
    @Test()
    public void positiveLoginCaseWithFunctions() {
        homePage().goToLoginPage();
        loginPage().login("shiftedtech0000@gmail.com", "shiftedtech", true);
    }

    @Test()
    public void negativeLoginCaseWithFunctions() {
        homePage().goToLoginPage();
        loginPage().login("shiftedtech0000@gmail.com", "shiftedtec", false);
    }

    @Test()
    public void logoutCaseWithFunctions() {
        homePage().goToLoginPage();
        loginPage().login("shiftedtech0000@gmail.com", "shiftedtech", true);
        loginPage().logOut();
    }
}
