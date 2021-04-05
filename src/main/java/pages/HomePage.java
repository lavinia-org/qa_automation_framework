package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By imageForProductSKUDemo1 = By.cssSelector("img[title='Faded Short Sleeve T-shirts']");
    private By addToCartBtnForProductSKUDemo1 = By.cssSelector("a[data-id-product='1']");

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void addProductToCart() {
        log.info("Adding product to cart: " + getTitleAttribute(imageForProductSKUDemo1));
        addProductToCartFromGridView(imageForProductSKUDemo1, addToCartBtnForProductSKUDemo1);
    }
}
