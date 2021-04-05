package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected Logger log;

    private By pageH1 = By.className("page-heading");
    private By headerSignInLink = By.className("login");
    private By headerSignOutLink = By.className("logout");
    private By headerUsernameLink = By.className("account");
    private By headerContactUsLink = By.id("contact-link");
    private By headerLogo = By.id("header_logo");
    private By headerShoppingCartQuantity = By.cssSelector(".shopping_cart span.ajax_cart_quantity");
    private By headerShoppingCartProductTxt = By.cssSelector(".shopping_cart span.ajax_cart_product_txt");
    private By headerShoppingCartProductsTxt = By.cssSelector(".shopping_cart span.ajax_cart_product_txt_s");
    private By headerCartBlock = By.cssSelector(".shopping_cart > a");
    private By headerCartBlockProduct1Name = By.cssSelector(".shopping_cart .first_item .product-name > a");
    private By headerCartBlockProductSize = By.cssSelector(".shopping_cart .product-atributes > a");
    private By headerCartRows = By.cssSelector(".products >dt");

    private By productAddedModalH2 = By.cssSelector(".layer_cart_product h2:nth-child(2)");
    private By productAddedModalProductName = By.cssSelector(".layer_cart_product .product-name");
    private By productAddedModalContinueShoppingBtn = By.cssSelector(".clearfix .continue");

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
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
        log.info("Pressing " + key.name());
        find(locator).sendKeys(key);
    }

    /**
     * Perform mouse hover over element
     * @param element
     */
    protected void hoverOverElement(By element) {
        Actions action = new Actions(driver);
        action.moveToElement(find(element)).build().perform();
    }

    public void waitForVisibility(By locator, int defaultTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void checkIfElementIsDisplayed(By locator) {
        find(locator).isDisplayed();
    }

    protected void addProductToCartFromGrid(By hoverLocator, By clickLocator) {
        waitForVisibility(hoverLocator, 10);
        hoverOverElement(hoverLocator);
        click(clickLocator);
    }

    public String getImageTitle(By locator) {
        WebElement element = find(locator);
        String imageTitle = element.getAttribute("title");
        return imageTitle;
    }

    protected void waitForModalContentToBeDisplayed(By locator) {
        log.info("Waiting for modal to be displayed...");
        waitForVisibility(locator,10);
        checkIfElementIsDisplayed(locator);
        log.info("Modal content displayed!");
    }

    public String getText(By locator) {
        return find(locator).getText();
    }

    protected String getTitleAttribute(By locator) {
        WebElement element = find(locator);
        String elementTitle = element.getAttribute("title");
        return elementTitle;
    }

    //HEADER RELATED METHODS

    /**
     * Clicks on Sign In link from page header
     * @return LoginPage
     */
    public LoginPage clickOnSignInLink() {
        log.info("Clicking on SignIn link");
        click(headerSignInLink);
        return new LoginPage(driver, log);
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
        log.info("Clicking on SignOut link");
        find(headerSignOutLink).click();
    }

    /**
     * This method checks if 'Product' or 'Products' is displayed near cart icon when user has items in cart
     * @return  String number of cart products + 'Product' / 'Products' text
     */
    public String getCartItemNoTxt() {
        String cartItemNoTxt;
        if (find(headerShoppingCartProductTxt).isDisplayed()){
            cartItemNoTxt = getText(headerShoppingCartQuantity) + " " + getText(headerShoppingCartProductTxt);
        } else {
            cartItemNoTxt = getText(headerShoppingCartQuantity) + " " + getText(headerShoppingCartProductsTxt);
        }
        return cartItemNoTxt;
    }

    public void hoverOverShoppingCartBlock() {
        hoverOverElement(headerCartBlock);
    }

    public String getCartProductTitle() {
        String productTitle = getTitleAttribute(headerCartBlockProduct1Name);
        return productTitle;
    }

    public String getProductSize() {
        String productSize = getText(headerCartBlockProductSize);
        return productSize;
    }

    public int countCartItems () {
        List<WebElement> rows = driver.findElements(headerCartRows);
        int count = rows.size();
        return count;
    }

    //MODAL RELATED METHODS
    public void waitForProductAddedModalToBeDisplayed() {
        waitForModalContentToBeDisplayed(productAddedModalH2);
    }

    public String getModalH2() {
        String modalH2 = getText(productAddedModalH2);
        return modalH2;
    }

    public String getModalProductName() {
        String modalProductName = getText(productAddedModalProductName);
        return modalProductName;
    }

    public void clickOnContinueShoppingBtn() {
        log.info("Clicking on Continue Shopping button from Product Added Modal");
        find(productAddedModalContinueShoppingBtn).click();
    }
}
