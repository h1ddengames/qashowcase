package com.h1ddengames.testcases;

import com.h1ddengames.framework.SpreeFunctionsScriptBase;
import org.testng.annotations.*;

/*
Contains test scripts using the function driven methods that also extends Selenium functionality.

This will keep test scripts small because all the logic is contained within the ScriptBase/SpreeScriptBase
*/
public class TestCase2 extends SpreeFunctionsScriptBase {

    @Test()
    public void positiveLoginCaseWithFunctions() {
        login("shiftedtech0000@gmail.com", "shiftedtech", true);
    }

    @Test()
    public void negativeLoginCaseWithFunctions() {
        login("shiftedtech0000@gmail.com", "shiftedtec", false);
    }

    @Test()
    public void logoutCaseWithFunctions() {
        login("shiftedtech0000@gmail.com", "shiftedtech", true);
        logOut();
    }

    @Test(groups = { "broken" })
    public void markingTestAsBroken() {
        System.out.println("Test will not run when using testng.xml. " +
                "If you run from this class file however, it will run.");
    }
}
