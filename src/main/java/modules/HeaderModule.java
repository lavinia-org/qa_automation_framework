package modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.CategoryPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class HeaderModule extends BasePage {

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

    private By mainMenuWomen = By.cssSelector(".menu-content >li:nth-child(1) a");

    public HeaderModule(WebDriver driver, Logger log) {
        super(driver, log);
    }

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

    /**
     * Gets all cart items names and adds them to a list
     * Also prints out a log.info with every product name that the cart contains
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

    public CategoryPage openWomenPage() {
        log.info("Opening Women page");
        click(mainMenuWomen);
        return new CategoryPage(driver, log);
    }
}
