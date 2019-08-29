package com.h1ddengames;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestScriptBase {
    protected WebDriver driver;
    protected String baseURL;

    @BeforeClass
    public void setup() {
        // Downloading directly over the network is forbidden so you might
        // not be able to use WebDriverManager.
        WebDriverManager.chromedriver().version("76.0.3809.68").setup();
        WebDriverManager.firefoxdriver().version("0.24.0").setup();

        driver = new ChromeDriver();

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
