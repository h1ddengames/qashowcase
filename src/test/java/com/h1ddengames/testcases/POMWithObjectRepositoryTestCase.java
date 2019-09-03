package com.h1ddengames.testcases;

import com.h1ddengames.framework.pages.spree.objectrepo.SpreeObjectRepositoryScriptBase;
import org.testng.annotations.Test;

/*
Contains test scripts using POM function driven methods that also extends Selenium functionality.

This will keep test scripts small because all the logic is contained within the Page files.

While it takes a bit more work to get POM classes and logic setup, each function is kept within
the page where the function can be used. This means that non-technical people can't call the login
function from the homePage since the login function requires you to be on the loginPage. Additionally,
this means that Page class files will be shorter due to the functions being spread out across multiple pages.
*/
public class POMWithObjectRepositoryTestCase extends SpreeObjectRepositoryScriptBase {
    @Test()
    public void positiveLoginCaseWithObjectRepository() {
        homePage().goToLoginPage();
        loginPage().login("shiftedtech0000@gmail.com", "shiftedtech");
        homePage().checkLoginSuccessMessage();
    }

    @Test()
    public void negativeLoginCaseWithObjectRepository() {
        homePage().goToLoginPage();
        loginPage().login("shiftedtech0000@gmail.com", "shiftedtec");
        loginPage().checkLoginFailedMessage();
    }

    @Test()
    public void logoutCaseWithObjectRepository() {
        homePage().goToLoginPage();
        loginPage().login("shiftedtech0000@gmail.com", "shiftedtech");
        homePage().checkLoginSuccessMessage();
        loginPage().logOut();
        homePage().checkSignoutSuccessMessage();
    }
}
