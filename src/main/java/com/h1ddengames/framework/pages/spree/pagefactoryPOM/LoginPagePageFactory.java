package com.h1ddengames.framework.pages.spree.pagefactoryPOM;

import com.h1ddengames.framework.scriptbases.ScriptBase;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.Matchers.equalTo;

public class LoginPagePageFactory extends BasePagePageFactory {

    @FindBy(id = "spree_user_email") private WebElement userEmail;
    @FindBy(id = "spree_user_password") private WebElement userPassword;
    @FindBy(name = "commit") private WebElement loginButton;
    @FindBy(css = ".alert-error") private WebElement loginFailedMessage;

    public LoginPagePageFactory(WebDriver driver) {
        this.driver = driver;
        this.driverWait = new WebDriverWait(driver, ScriptBase.DEFAULT_WEB_DRIVER_WAIT);
        this.driverJSExecutor = (JavascriptExecutor) driver;

        PageFactory.initElements(driver, this);
    }

    public void login(String email, String password) {
        userEmail.click();
        userEmail.clear();
        userEmail.sendKeys(email);

        userPassword.click();
        userPassword.clear();
        userPassword.sendKeys(password);

        loginButton.click();
    }

    public void checkLoginFailedMessage() {
        MatcherAssert.assertThat(loginFailedMessage.getText(), equalTo("Invalid email or password."));
    }
}
