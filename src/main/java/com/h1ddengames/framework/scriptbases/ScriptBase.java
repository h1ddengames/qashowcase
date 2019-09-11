package com.h1ddengames.framework.scriptbases;

import com.h1ddengames.framework.utils.CommonSeleniumTasks;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

/*
Contains required variables/methods to set up and use WebDriver in test scripts.
*/
public class ScriptBase extends CommonSeleniumTasks {
    protected WebDriver driver;
    protected WebDriverWait driverWait;
    protected JavascriptExecutor driverJSExecutor;
    protected String baseURL;

    public static int DEFAULT_WEB_DRIVER_WAIT = 5;
    public static int DEFAULT_IMPLICITLY_WAIT = 3;
    public static int DEFAULT_PAGE_LOAD_TIMEOUT = 5;
    public static int DEFAULT_SET_SCRIPT_TIMEOUT = 5;

    @Override protected WebDriver getDriver() { return driver; }
    @Override protected WebDriverWait getDriverWait() { return driverWait; }
    @Override protected JavascriptExecutor getDriverJSExecutor() { return driverJSExecutor; }

    @Parameters({ "browser" })
    @BeforeClass
    public void setup(@Optional String browser) {
        // @Optional is required so that scripts may be run from the Java file itself rather than
        // using testng.xml

        String userDirectory = System.getProperty("user.dir");
        objectRepositoryManager.load(
                (userDirectory + "/src/main/resources/spree/SpreeNavigationObjectRepository.properties"),
                (userDirectory + "/src/main/resources/spree/SpreeHomePageObjectRepository.properties"),
                (userDirectory + "/src/main/resources/spree/SpreeLoginPageObjectRepository.properties")
        );

        //objectRepositoryManager.printAllProperties();

        // Downloading directly over the network is forbidden so you might
        // not be able to use WebDriverManager.
        WebDriverManager.chromedriver().version("76.0.3809.68").setup();
        WebDriverManager.firefoxdriver().version("0.24.0").setup();

        if(browser == null) {
            driver = new ChromeDriver();
        } else if(browser.toUpperCase().contentEquals("CH")) {
            driver = new ChromeDriver();
        } else if(browser.toUpperCase().contentEquals("FF")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }

        driverWait = new WebDriverWait(driver, DEFAULT_WEB_DRIVER_WAIT);
        driverJSExecutor = (JavascriptExecutor) driver;

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(DEFAULT_SET_SCRIPT_TIMEOUT, TimeUnit.SECONDS);

        // If downloading directly is forbidden in your company,
        // you will instead have to set up the driver locally.
        // The jar files you need will be downloaded for you by whoever has the authority
        // to download and vet them or you will have to use a settings.xml file in your .m2 folder
        // that will point to a repository for jar files hosted by your company.

        // String driverPath = System.getProperty("user.dir")
        //        + "/drivers/chromedriver.exe";
        // System.setProperty("webdriver.chrome.driver", driverPath);
        // driver = new ChromeDriver();
    }

    @AfterMethod
    public void afterMethod() {
        // We delete cookies so that other tests that use the same instance of WebDriver don't
        // get messed up due to previous login data from the previous test method.
        driver.manage().deleteAllCookies();
    }

    @AfterClass
    public void tearDown() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
