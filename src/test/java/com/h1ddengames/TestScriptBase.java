package com.h1ddengames;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class TestScriptBase {
    protected WebDriver driver;
    protected String baseURL;

    @Parameters({ "browser" })
    @BeforeClass
    public void setup(String browser) {
        // Downloading directly over the network is forbidden so you might
        // not be able to use WebDriverManager.
        WebDriverManager.chromedriver().version("76.0.3809.68").setup();
        WebDriverManager.firefoxdriver().version("0.24.0").setup();

        if(browser.toUpperCase().contentEquals("CH")) {
            driver = new ChromeDriver();
        } else if(browser.toUpperCase().contentEquals("FF")) {
            driver = new FirefoxDriver();
        } else {
            driver = new ChromeDriver();
        }


        // You will instead have to set up the driver locally
        // String driverPath = System.getProperty("user.dir")
        //        + "/drivers/chromedriver.exe";
        // System.setProperty("webdriver.chrome.driver", driverPath);
        // driver = new ChromeDriver();
    }

    @AfterClass
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
