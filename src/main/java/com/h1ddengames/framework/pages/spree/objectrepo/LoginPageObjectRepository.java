package com.h1ddengames.framework.pages.spree.objectrepo;

import com.h1ddengames.framework.scriptbases.ScriptBase;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.Matchers.equalTo;

public class LoginPageObjectRepository extends BasePageObjectRepository {

    public LoginPageObjectRepository(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
        this.driverJSExecutor = (JavascriptExecutor) driver;
    }

    public void login(String email, String password) {
        goToLoginPage();
        enterUsernameAndPassword(email, password);
    }

    public void enterUsernameAndPassword(String email, String password) {
        enterDataIntoElement(objectRepositoryManager.getByFromObjectRepositoryLocator("LoginPage.EmailTextbox"), email);
        enterDataIntoElement(objectRepositoryManager.getByFromObjectRepositoryLocator("LoginPage.PasswordTextbox"), password);
        clickElement(objectRepositoryManager.getByFromObjectRepositoryLocator("LoginPage.LoginButton"));
    }

    public void checkLoginFailedMessage() {
        WebElement loginFailedMessage = driver.findElement(objectRepositoryManager.getByFromObjectRepositoryLocator("LoginPage.LoginFailAlert"));
        MatcherAssert.assertThat(loginFailedMessage.getText(), equalTo("Invalid email or password."));
    }
}
