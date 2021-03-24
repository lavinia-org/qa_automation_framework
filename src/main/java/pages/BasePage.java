package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class BasePage {

    protected WebDriver driver;

    private By headerSignInLink = By.className("login");
    private By headerSignOutLink = By.className("logout");
    private By headerUsernameLink = By.className("account");
    private By headerContactUsLink = By.id("contact-link");
    private By headerLogo = By.id("header_logo");
    private By pageH1 = By.className("page-heading");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Clicks on Sign In link from page header
     * @return LoginPage
     */
    public LoginPage clickOnSignInLink() {
        click(headerSignInLink);
        return new LoginPage(driver);
    }

    /**
     * Checks if Sign Out link is displayed after user logs in
     * @return boolean
     */
    public boolean isSignOutLinkVisible() {
        return find(headerSignOutLink).isDisplayed();
    }

    /**
     * Gets username from header
     * @return String username text
     */
    public String getUsername() {
        return find(headerUsernameLink).getText();
    }

    /**
     * Clicks on Sign out link from header
     */
    public void signOutUser() {
        find(headerSignOutLink).click();
    }

    /**
     * Find element using given locator
     * @param locator
     * @return Webelement
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Click on element with given locator
     * @param locator
     */
    protected void click(By locator) {
        find(locator).click();
    }

    /**
     * Clears input and types given text into element with given locator
     * @param text
     * @param locator
     */
    protected void type(String text, By locator) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    /**
     * Get URL of current page from browser
     * @return URL String
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     *  Get title of current page
     * @return String title
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get H1 of current page
     * @return String H1
     */
    public String getCurrentPageH1() {
        return find(pageH1).getText();
    }

    /**
     * Press Key on locator
     * @param locator
     * @param key
     */
    protected void pressKey(By locator, Keys key) {
        find(locator).sendKeys(key);
    }

    /**
     * Perform mouse hover over element
     * @param element
     */
    protected void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }
}
