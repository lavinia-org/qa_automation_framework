package logintests;

import base.BaseTest;
import constants.ConstantsCredentials;
import constants.ConstantsMessages;
import constants.ConstantsURLs;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.MyAccountPage;

public class PositiveLoginTest extends BaseTest {

    @Test
    public void logInTestUsingSignInButton() {
        log.info("Starting Log In test using Sign in button");

        BasePage basePage = new BasePage(driver);
        openURL(ConstantsURLs.baseURL);
        basePage.clickOnSignInLink();

        LoginPage loginPage = new LoginPage(driver);
        MyAccountPage myAccountPage = loginPage.logInUserSignInBtn(ConstantsCredentials.validEmail,
                ConstantsCredentials.validPassword);
        log.info("User successfully logged in");

        Assert.assertEquals(ConstantsURLs.myAccountURL, myAccountPage.getCurrentUrl());
        Assert.assertEquals(ConstantsMessages.myAccountTitle, myAccountPage.getCurrentPageTitle());
        Assert.assertTrue(basePage.isSignOutLinkVisible());
        Assert.assertEquals(ConstantsMessages.validUsername, basePage.getUsername());
    }

    @Test
    public void logInTestUsingEnterKey() {
        log.info("Starting Log In test using Enter key");

        BasePage basePage = new BasePage(driver);
        openURL(ConstantsURLs.baseURL);
        basePage.clickOnSignInLink();

        LoginPage loginPage = new LoginPage(driver);
        MyAccountPage myAccountPage = loginPage.logInUserEnterKey(ConstantsCredentials.validEmail,
                ConstantsCredentials.validPassword);
        log.info("User successfully logged in");

        Assert.assertEquals(ConstantsURLs.myAccountURL, myAccountPage.getCurrentUrl());
        Assert.assertEquals(ConstantsMessages.myAccountTitle, myAccountPage.getCurrentPageTitle());
        Assert.assertTrue(basePage.isSignOutLinkVisible());
        Assert.assertEquals(ConstantsMessages.validUsername, basePage.getUsername());
    }
}
