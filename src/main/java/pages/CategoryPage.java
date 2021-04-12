package pages;

import modules.HeaderModule;
import modules.ProductAddedModule;
import modules.ProductListModule;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategoryPage extends BasePage {

    ProductListModule productListModule = new ProductListModule(driver, log);
    ProductAddedModule productAddedModule = new ProductAddedModule(driver, log);
    HeaderModule headerModule = new HeaderModule(driver, log);

    private By addToCartBtnForProductSKUDemo2 = By.cssSelector("li.ajax_block_product:nth-of-type(2) " +
            ".right-block a.ajax_add_to_cart_button ");
    private By productTitleSKUDemo2 = By.cssSelector("li.ajax_block_product:nth-of-type(2)" +
            " .center-block h5>a.product-name");
    private By addToCartBtnForProductSKUDemo1 = By.cssSelector("li.ajax_block_product:nth-of-type(1) " +
            ".right-block a.ajax_add_to_cart_button ");
    private By productTitleSKUDemo1 = By.cssSelector("li.ajax_block_product:nth-of-type(1)" +
            " .center-block h5>a.product-name");

    public CategoryPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public ProductListModule getProductListModule() {
        return productListModule;
    }

    public ProductAddedModule getProductAddedModule() {
        return productAddedModule;
    }

    public HeaderModule getHeaderModule() {
        return headerModule;
    }

    public void addFirstProductToCart() {
        log.info("Adding product to cart: " + getText(productTitleSKUDemo2));
        productListModule.addProductToCartFromListView(addToCartBtnForProductSKUDemo2);
    }

    public void addSecondProductToCart() {
        log.info("Adding product to cart: " + getText(productTitleSKUDemo1));
        productListModule.addProductToCartFromListView(addToCartBtnForProductSKUDemo1);
    }
}
