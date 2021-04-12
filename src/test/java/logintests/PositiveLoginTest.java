package logintests;

import base.BaseTest;
import constants.ConstantsCredentials;
import constants.ConstantsMessages;
import constants.ConstantsURLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;

public class PositiveLoginTest extends BaseTest {

    @Test
    public void logInTestUsingSignInButton() {
        log.info("Starting Log In test using Sign in button");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Navigate to Login page and sign in
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = homePage.getHeaderModule().clickOnSignInLink();
        MyAccountPage myAccountPage = loginPage.logInUserSignInBtn(ConstantsCredentials.validEmail,
                ConstantsCredentials.validPassword);
        log.info("User successfully logged in");

        //Check that correct user is signed in and landed on My Account page
        Assert.assertEquals(ConstantsURLs.myAccountURL, myAccountPage.getCurrentUrl());
        Assert.assertEquals(ConstantsMessages.myAccountTitle, myAccountPage.getCurrentPageTitle());
        log.info("User is on " + myAccountPage.getCurrentPageTitle() + " page");
        Assert.assertEquals(ConstantsMessages.validUsername, myAccountPage.getHeaderModule().getUsername());
        log.info("Correct username is displayed in header");
        Assert.assertTrue(myAccountPage.getHeaderModule().isSignOutLinkVisible());
        log.info("Sign out button is displayed in header");
    }

    @Test
    public void logInTestUsingEnterKey() {
        log.info("Starting Log In test using Enter key");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Navigate to Login page and sign in
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = homePage.getHeaderModule().clickOnSignInLink();
        MyAccountPage myAccountPage = loginPage.logInUserEnterKey(ConstantsCredentials.validEmail,
                ConstantsCredentials.validPassword);
        log.info("User successfully logged in");

        //Check that correct user is signed in and landed on My Account page
        Assert.assertEquals(ConstantsURLs.myAccountURL, myAccountPage.getCurrentUrl());
        Assert.assertEquals(ConstantsMessages.myAccountTitle, myAccountPage.getCurrentPageTitle());
        log.info("User is on " + myAccountPage.getCurrentPageTitle() + " page");
        Assert.assertEquals(ConstantsMessages.validUsername, myAccountPage.getHeaderModule().getUsername());
        log.info("Correct username is displayed in header");
        Assert.assertTrue(myAccountPage.getHeaderModule().isSignOutLinkVisible());
        log.info("Sign out button is displayed in header");
    }
}
