package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By imageForProductSKUDemo1 = By.cssSelector("img[title='Faded Short Sleeve T-shirts']");
    private By addToCartBtnForProductSKUDemo1 = By.cssSelector("a[data-id-product='1']");
    private By moreBtnForProductSKUDemo1 = By.cssSelector("#homefeatured li.ajax_block_product " +
            ":nth-child(1) a.lnk_view ");

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void addProductToCart() {
        log.info("Adding product to cart: " + getTitleAttribute(imageForProductSKUDemo1));
        addProductToCartFromGridView(imageForProductSKUDemo1, addToCartBtnForProductSKUDemo1);
    }

    public void navigateToProductPageDemo1() {
        log.info("Navigate to products' page: " + getTitleAttribute(imageForProductSKUDemo1) );
        navigateToProductPage(imageForProductSKUDemo1, moreBtnForProductSKUDemo1);
    }
}
