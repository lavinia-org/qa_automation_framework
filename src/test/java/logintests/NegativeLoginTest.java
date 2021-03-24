package logintests;

import base.BaseTest;
import constants.ConstantsCredentials;
import constants.ConstantsMessages;
import constants.ConstantsURLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;

public class NegativeLoginTest extends BaseTest {

    @Test
    public void negativeLogInTest() {
        log.info("Starting negative Log In test");

        BasePage basePage = new BasePage(driver);
        openURL(ConstantsURLs.baseURL);

        LoginPage loginPage = basePage.clickOnSignInLink();
        loginPage.negativeLogIn("incorrectemail@test.com", ConstantsCredentials.validPassword);

        String message = loginPage.getLogInErrorMessageText();
        Assert.assertTrue(message.contains("Authentication failed."), "Message doesn't contain expected text.");
        log.info("Invalid user not logged in");
        Assert.assertEquals(loginPage.getCurrentPageH1(), ConstantsMessages.loginH1);
        log.info("Current page is Login page");
    }
}
