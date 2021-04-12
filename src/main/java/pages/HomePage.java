package pages;

import modules.HeaderModule;
import modules.ProductAddedModule;
import modules.ProductDetailsModule;
import modules.ProductListModule;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    ProductDetailsModule productDetailsModule = new ProductDetailsModule(driver, log);
    ProductListModule productListModule = new ProductListModule(driver, log);
    ProductAddedModule productAddedModule = new ProductAddedModule(driver, log);
    HeaderModule headerModule = new HeaderModule(driver, log);

    private By imageForProductSKUDemo1 = By.cssSelector("img[title='Faded Short Sleeve T-shirts']");
    private By addToCartBtnForProductSKUDemo1 = By.cssSelector("a[data-id-product='1']");
    private By moreBtnForProductSKUDemo1 = By.cssSelector("#homefeatured li.ajax_block_product " +
            ":nth-child(1) a.lnk_view ");

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public ProductDetailsModule getProductDetailsModule() {
        return productDetailsModule;
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

    public void addProductToCart() {
        log.info("Adding product to cart: " + getTitleAttribute(imageForProductSKUDemo1));
        productListModule.addProductToCartFromGridView(imageForProductSKUDemo1, addToCartBtnForProductSKUDemo1);
    }

    public ProductDetailsPage navigateToProductPageDemo1() {
        log.info("Navigate to products' page: " + getTitleAttribute(imageForProductSKUDemo1) );
        productListModule.navigateToProductPage(imageForProductSKUDemo1, moreBtnForProductSKUDemo1);
        return new ProductDetailsPage(driver, log);
    }
}
