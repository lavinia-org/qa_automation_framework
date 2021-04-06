package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
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
    private By headerCartItems = By.cssSelector(".cart_block_list .products a.cart-images img");

    private By productAddedModalH2 = By.cssSelector(".layer_cart_product h2:nth-child(2)");
    private By productAddedModalProductName = By.cssSelector(".layer_cart_product .product-name");
    private By productAddedModalContinueShoppingBtn = By.cssSelector(".clearfix .continue");
    private By productAddedModalAttributes = By.id("layer_cart_product_attributes");
    private By productAddedModalQuantity = By.id("layer_cart_product_quantity");

    private By quickViewFrame = By.className("fancybox-iframe");
    private By quickViewProductSKUDemo1 = By.cssSelector("#homefeatured li:nth-child(1) a.quick-view");

    private By productSKUDemo1H1 = By.cssSelector(".product .pb-center-column >h1");
    private By addToCartBtn = By.cssSelector(".product .pb-right-column button.exclusive");
    private By quantityField = By.id("quantity_wanted");
    private By increaseQuantityBtn = By.cssSelector(".product .pb-right-column .button-plus");
    private By decreaseQuantityBtn = By.cssSelector(".product .pb-right-column .button-minus");
    private By sizeDropdown = By.id("group_1");

    private By mainMenuWomen = By.cssSelector(".menu-content >li:nth-child(1) a");
    private By listView = By.id("list");
    private By imageForProductSKUDemo1 = By.cssSelector("img[title='Faded Short Sleeve T-shirts']");

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Find element using given locator
     *
     * @param locator
     * @return Webelement
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Click on element with given locator
     *
     * @param locator
     */
    protected void click(By locator) {
        find(locator).click();
    }

    /**
     * Clears input and types given text into element with given locator
     *
     * @param text
     * @param locator
     */
    protected void type(String text, By locator) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    protected void type(int i, By locator) {
        find(locator).clear();
        find(locator).sendKeys("" + i);
    }

    /**
     * Get URL of current page from browser
     *
     * @return URL String
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get title of current page
     *
     * @return String title
     */
    public String getCurrentPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get H1 of current page
     *
     * @return String H1
     */
    public String getCurrentPageH1() {
        return find(pageH1).getText();
    }

    /**
     * Press Key on locator
     *
     * @param locator
     * @param key
     */
    protected void pressKey(By locator, Keys key) {
        log.info("Pressing " + key.name());
        find(locator).sendKeys(key);
    }

    /**
     * Perform mouse hover over element
     *
     * @param element
     */
    protected void hoverOverElement(By element) {
        Actions action = new Actions(driver);
        action.moveToElement(find(element)).build().perform();
    }

    /**
     * Waits for By locator's visibility
     *
     * @param locator
     * @param defaultTimeout
     */
    public void waitForVisibility(By locator, int defaultTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void checkIfElementIsDisplayed(By locator) {
        find(locator).isDisplayed();
    }

    protected void addProductToCartFromGridView(By hoverLocator, By clickLocator) {
        waitForVisibility(hoverLocator, 10);
        hoverOverElement(hoverLocator);
        click(clickLocator);
    }

    protected void addProductToCartFromListView(By locator) {
        click(locator);
    }

    public void addProductToCart() {
        click(addToCartBtn);
        log.info("Product added to cart");
    }

    protected void waitForModalContentToBeDisplayed(By locator) {
        log.info("Waiting for modal to be displayed...");
        waitForVisibility(locator, 10);
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

    protected String getAltAttribute(WebElement element) {
        String elementTitle = element.getAttribute("alt");
        return elementTitle;
    }

    public void changeLayoutToListView() {
        log.info("Switching to List View");
        click(listView);
    }

    /**
     * Gets all cart items names and adds them to a list
     * Also prints out a log.info with every product name that the cart contains
     *
     * @return list of products' names
     */
    public List<String> getCartItems() {
        List<WebElement> cartList = driver.findElements(headerCartItems);
        List<String> cartProducts = new ArrayList();

        for (WebElement row : cartList) {
            String productName = getAltAttribute(row);
            cartProducts.add(productName);
        }

        for (String product : cartProducts) {
            log.info("Shopping cart contains: " + product);
        }
        return cartProducts;
    }

    public void increaseProductQuantity() {
        log.info("Increasing product quantity");
        find(increaseQuantityBtn).click();
    }

    public void decreaseProductQuantity() {
        log.info("Decreasing product quantity");
        find(decreaseQuantityBtn).click();
    }

    public void updateProductQuantity(int quantity) {
        log.info("Updating product quantity to: " + quantity);
        type(quantity, quantityField);
    }

    public String getProductQuantity() {
        waitForVisibility(quantityField, 10);
        Assert.assertTrue(find(quantityField).isDisplayed());
        String productQuantity = find(quantityField).getText();

        return productQuantity;
    }

    public void selectProductSize(int i) {
        log.info("Selecting option " + i + " from Size dropdown");
        WebElement dropdownElement = find(sizeDropdown);

        Select fromDropdown = new Select(dropdownElement);
        fromDropdown.selectByIndex(i);
    }

    public void selectProductSize(String size) {
        log.info("Selecting option " + size + " from Size dropdown");
        WebElement dropdownElement = find(sizeDropdown);

        Select fromDropdown = new Select(dropdownElement);
        fromDropdown.selectByVisibleText(size);
    }

    public String getSelectedProductSize() {
        WebElement dropdownElement = find(sizeDropdown);
        Select fromDropdown = new Select(dropdownElement);
        String selectedOption = fromDropdown.getFirstSelectedOption().getText();
        log.info("Product size selected is: " + selectedOption);
        return selectedOption;
    }

    protected void navigateToProductPage(By hoverLocator, By clickLocator) {
        hoverOverElement(hoverLocator);
        click(clickLocator);
    }

    protected String getProductH1(By locator) {
        String modalH1 = find(locator).getText();
        return modalH1;
    }

    public String getProductDemo1H1() {
        waitForVisibility(productSKUDemo1H1, 10);
        String modalDemo1H1 = getProductH1(productSKUDemo1H1);
        return modalDemo1H1;
    }

    //HEADER RELATED METHODS

    /**
     * Clicks on Sign In link from page header
     *
     * @return LoginPage
     */
    public LoginPage clickOnSignInLink() {
        log.info("Clicking on SignIn link");
        click(headerSignInLink);
        return new LoginPage(driver, log);
    }

    /**
     * Checks if Sign Out link is displayed after user logs in
     *
     * @return boolean
     */
    public boolean isSignOutLinkVisible() {
        return find(headerSignOutLink).isDisplayed();
    }

    /**
     * Gets username from header
     *
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
     *
     * @return String number of cart products + 'Product' / 'Products' text
     */
    public String getCartItemNoTxt() {
        String cartItemNoTxt;
        if (find(headerShoppingCartProductTxt).isDisplayed()) {
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
        find(headerCartBlockProductSize).isDisplayed();
        String productSize = getText(headerCartBlockProductSize);
        return productSize;
    }

    public int countCartRows() {
        List<WebElement> rows = driver.findElements(headerCartRows);
        int count = rows.size();
        return count;
    }

    public WomenPage openWomenPage() {
        log.info("Opening Women page");
        click(mainMenuWomen);
        return new WomenPage(driver, log);
    }

    //PRODUCT ADDED MODAL RELATED METHODS
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

    public String getProductAddedModalAttributes() {
        String attributes = find(productAddedModalAttributes).getText();
        return attributes;
    }

    public String getProductAddedModalQuantity() {
        String quantity = find(productAddedModalQuantity).getText();
        return quantity;
    }


    //QUICK VIEW MODAL RELATED METHODS
    public void switchToFrame(By iFrameLocator) {
        driver.switchTo().frame(find(iFrameLocator));
    }

    public void switchToFrameQ() {
        driver.switchTo().frame(find(quickViewFrame));
    }

    /**
     * Hovers over a product to show QuickView link and clicks on it
     * Switches to QuickView iFrame and waits for H1 to be visible - checks if it is
     *
     * @param hoverLocator
     * @param clickLocator
     * @param iFrameLocator
     * @param H1Locator
     */
    protected void openQuickViewModal(By hoverLocator, By clickLocator, By iFrameLocator, By H1Locator) {
        hoverOverElement(hoverLocator);
        click(clickLocator);
        switchToFrame(iFrameLocator);
        waitForVisibility(H1Locator, 10);
        find(H1Locator).isDisplayed();
        log.info("Opened Quick View Modal for Product: " + find(H1Locator).getText());
    }

    public void openQuickViewModalForDemo1() {
        openQuickViewModal(imageForProductSKUDemo1, quickViewProductSKUDemo1, quickViewFrame, productSKUDemo1H1);
    }

}
