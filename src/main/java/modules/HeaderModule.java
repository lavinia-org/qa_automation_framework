package modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.CategoryPage;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class HeaderModule extends BasePage {

    private By signInLink = By.className("login");
    private By signOutLink = By.className("logout");
    private By usernameLink = By.className("account");
    private By contactUsLink = By.id("contact-link");
    private By headerLogo = By.id("header_logo");
    private By shoppingCartQuantity = By.cssSelector(".shopping_cart span.ajax_cart_quantity");
    private By shoppingCartProductTxt = By.cssSelector(".shopping_cart span.ajax_cart_product_txt");
    private By shoppingCartProductsTxt = By.cssSelector(".shopping_cart span.ajax_cart_product_txt_s");
    private By cartBlock = By.cssSelector(".shopping_cart > a");
    private By cartBlockProductSize = By.cssSelector(".shopping_cart .product-atributes > a");
    private By cartRows = By.cssSelector(".products >dt");
    private By cartItems = By.cssSelector(".cart_block_list .products a.cart-images img");
    private By mainMenuCategory = By.cssSelector("ul.menu-content > li > a");

    public HeaderModule(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Clicks on Sign In link from page header
     *
     * @return LoginPage
     */
    public LoginPage clickOnSignInLink() {
        log.info("Clicking on SignIn link");
        click(signInLink);
        return new LoginPage(driver, log);
    }

    /**
     * Checks if Sign Out link is displayed after user logs in
     *
     * @return boolean
     */
    public boolean isSignOutLinkVisible() {
        return find(signOutLink).isDisplayed();
    }

    /**
     * Gets username from header
     *
     * @return String username text
     */
    public String getUsername() {
        return find(usernameLink).getText();
    }

    /**
     * Clicks on Sign out link from header
     */
    public void signOutUser() {
        log.info("Clicking on SignOut link");
        find(signOutLink).click();
    }

    /**
     * This method checks if 'Product' or 'Products' is displayed near cart icon when user has items in cart
     *
     * @return String number of cart products + 'Product' / 'Products' text
     */
    public String getCartItemNoTxt() {
        String cartItemNoTxt;
        if (find(shoppingCartProductTxt).isDisplayed()) {
            cartItemNoTxt = getText(shoppingCartQuantity) + " " + getText(shoppingCartProductTxt);
        } else {
            cartItemNoTxt = getText(shoppingCartQuantity) + " " + getText(shoppingCartProductsTxt);
        }
        return cartItemNoTxt;
    }

    public void hoverOverShoppingCartBlock() {
        hoverOverElement(cartBlock);
    }

    public String getProductSize() {
        find(cartBlockProductSize).isDisplayed();
        String productSize = getText(cartBlockProductSize);
        return productSize;
    }

    public int countCartRows() {
        List<WebElement> rows = driver.findElements(cartRows);
        int count = rows.size();
        return count;
    }

    /**
     * Gets all cart items names and adds them to a list
     * Also prints out a log.info with every product name that the cart contains
     *
     * @return list of products' names
     */
    public List<String> getCartItems() {
        List<WebElement> cartList = driver.findElements(cartItems);
        List<String> cartProducts = new ArrayList();

        for (WebElement row : cartList) {
            String productName = getAltAttribute(row);
            cartProducts.add(productName);
        }
        return cartProducts;
    }

    /**
     * Iterates over main navigation's categories and clicks on the one given by String parameter
     *
     * @param page
     * @return Category page
     */
    public CategoryPage navigateToCategoryPage(String page) {
        log.info("Navigating to Category page: " + page);
        List<WebElement> mainNavList = driver.findElements(mainMenuCategory);
        Iterator<WebElement> itr = mainNavList.iterator();

        while (itr.hasNext()) {
            WebElement category = itr.next();
            if (category.getAttribute("title").contains(page)) {
                category.click();
                break;
            } else {
                throw new NoSuchElementException("No such category found: " + page);
            }
        }
        return new CategoryPage(driver, log);
    }
}
