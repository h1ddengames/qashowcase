package com.h1ddengames.testcases;

import com.h1ddengames.framework.pages.spree.objectrepoPOM.SpreeObjectRepositoryScriptBase;
import org.testng.annotations.Test;

/*
Contains test scripts using POM with Object Repository.
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
