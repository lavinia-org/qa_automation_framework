package logintests;

import base.BaseTest;
import base.CsvDataProviders;
import constants.ConstantsCredentials;
import constants.ConstantsMessages;
import constants.ConstantsURLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
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

        BasePage basePage = new BasePage(driver, log);
        openURL(ConstantsURLs.baseURL);

        LoginPage loginPage = basePage.clickOnSignInLink();
        loginPage.logInUserSignInBtn(email, password);

        String message = loginPage.getLogInErrorMessageText();
        Assert.assertTrue(message.contains(expectedErrorMessage), "Message doesn't contain expected text.");
        log.info("Expected error message is displayed for this negative scenario");
        Assert.assertEquals(loginPage.getCurrentPageH1(), ConstantsMessages.loginH1);
        log.info("User is on " + loginPage.getCurrentPageH1() + " page");
    }
}
