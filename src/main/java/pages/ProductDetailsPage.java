package pages;

import modules.HeaderModule;
import modules.ProductAddedModule;
import modules.ProductDetailsModule;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    ProductDetailsModule productDetailsModule = new ProductDetailsModule(driver, log);
    ProductAddedModule productAddedModule = new ProductAddedModule(driver, log);
    HeaderModule headerModule = new HeaderModule(driver, log);

    public ProductDetailsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public ProductDetailsModule getProductDetailsModule() {
        return productDetailsModule;
    }

    public ProductAddedModule getProductAddedModule() {
        return productAddedModule;
    }

    public HeaderModule getHeaderModule() {
        return headerModule;
    }
}
