package com.h1ddengames.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class ScriptBase {
    protected WebDriver driver;
    protected WebDriverWait driverWait;

    protected String baseURL;

    public static int DEFAULT_WEB_DRIVER_WAIT = 10;
    public static int DEFAULT_IMPLICITLY_WAIT = 10;
    public static int DEFAULT_PAGE_LOAD_TIMEOUT = 15;
    public static int DEFAULT_SET_SCRIPT_TIMEOUT = 10;

    @Parameters({ "browser" })
    @BeforeClass
    public void setup(@Optional String browser) {
        // @Optional is required so that scripts may be run from the Java file itself rather than
        // using testng.xml

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

    public void clickElement(By by) {
        try {
            // ExpectedConditions.elementToBeClickable returns WebElement
            // if expected condition is true otherwise it will throw TimeoutException.
            // It never returns null so we have to use a try/catch block to catch the exception.
            WebElement clickableElement = driverWait.until(ExpectedConditions.elementToBeClickable(by));
            clickableElement.click();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void enterDataIntoElement(By by, String data) {
        // ExpectedConditions.and() returns a boolean rather than a WebElement.
        try {
            boolean isTextFieldReady = driverWait.until(
                    ExpectedConditions.and(
                            ExpectedConditions.visibilityOfElementLocated(by),
                            ExpectedConditions.elementToBeClickable(by)
                    )
            );

            // Once both conditions are satisfied, then we can store the WebElement into
            // a variable and perform actions on it.
            if(isTextFieldReady) {
                WebElement textField = driver.findElement(by);
                textField.click();
                textField.clear();
                textField.sendKeys(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // I do not recommend doing it this way because you as the automation framework creator will need to create
    // all the different permutations of assertThat() such as isEqualTo(), startsWith(), endsWith(), etc.
    // If you don't have complicated assertions or many types of assertions, then you can try making as many methods
    // as required by your testers/script creators.
    public void assertElementText(By by, String expectedText) {
        try {
            WebElement elementToVerifyText = driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            assertThat(elementToVerifyText.getText()).isEqualTo(expectedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
