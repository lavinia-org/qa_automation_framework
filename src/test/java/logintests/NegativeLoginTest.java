package logintests;

import base.BaseTest;
import base.CsvDataProviders;
import constants.ConstantsMessages;
import constants.ConstantsURLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.util.Map;

public class NegativeLoginTest extends BaseTest {

    @Test(dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void negativeLogInTest(Map<String, String> testData) {

        //Get test data from map
        String no = testData.get("no");
        String email = testData.get("email");
        String password = testData.get("password");
        String expectedErrorMessage = testData.get("expectedMessage");
        String description = testData.get("description");

        log.info("Starting negativeLogInTest #" + no + " for " + description);

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Navigate to Login page and sign in user
        HomePage homePage = new HomePage(driver, log);
        LoginPage loginPage = homePage.getHeaderModule().clickOnSignInLink();
        loginPage.logInUserSignInBtn(email, password);

        //Check that error message is displayed and user is kept on Login page
        String message = loginPage.getLogInErrorMessageText();
        Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
        log.info("Expected error message is displayed for this negative scenario");
        Assert.assertEquals(loginPage.getCurrentPageTitle(), ConstantsMessages.loginPageTitle);
        log.info("User is on " + loginPage.getCurrentPageTitle() + " page");
    }
}
