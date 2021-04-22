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
}
