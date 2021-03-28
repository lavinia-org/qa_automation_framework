package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By emailLocator = By.id("email");
    private By passwordLocator = By.id("passwd");
    private By forgotPasswordLink = By.className("lost_password");
    private By signInButton = By.id("SubmitLogin");
    private By logInErrorMessage = By.cssSelector("div.alert-danger li");

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /** login user using Sign In button */
    public MyAccountPage logInUserSignInBtn(String email, String password) {
        log.info("Executing LogIn test using SignIn button with email '" + email + "' and password '" + password + "'");
        type(email, emailLocator);
        type(password, passwordLocator);
        click(signInButton);
        return new MyAccountPage(driver, log);
    }

    /** login user using Enter key */
    public MyAccountPage logInUserEnterKey(String email, String password) {
        log.info("Executing LogIn test using Enter Key with email '" + email + "' and password '" + password + "'");
        type(email, emailLocator);
        type(password, passwordLocator);
        pressKey(passwordLocator, Keys.ENTER);
        return new MyAccountPage(driver, log);
    }

    public String getLogInErrorMessageText() {
        return find(logInErrorMessage).getText();
    }
}
