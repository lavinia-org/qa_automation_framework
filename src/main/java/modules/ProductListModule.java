package modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import pages.ProductDetailsPage;

public class ProductListModule extends BasePage {

    private By quickViewFrame = By.className("fancybox-iframe");
    private By quickViewButton = By.cssSelector(".product_list >li a.quick-view");
    private By addToCartButton = By.cssSelector(".button-container > a.ajax_add_to_cart_button");
    private By moreButton = By.cssSelector(".button-container > a.lnk_view");
    private By productName = By.cssSelector(".product .pb-center-column >h1");
    private By listView = By.id("list");

    public ProductListModule(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void openQuickViewModalFor(String product) {
        log.info("Opening Quick View modal for: " + product);
        WebElement productBlock = chooseItem(product);
        hoverOverElement(productBlock);

        WebElement button = productBlock.findElement(quickViewButton);
        button.click();

        switchToFrame(quickViewFrame);
        checkIfDisplayed(productName);
        log.info("Opened Quick View Modal for Product: " + find(productName).getText());
    }

    public void addProductToCartFromGridView(String product) {
        WebElement productBlock = chooseItem(product);
        hoverOverElement(productBlock);

        WebElement button = productBlock.findElement(addToCartButton);
        button.click();
        log.info("Product added to cart: " + product);
    }

    public void addProductToCartFromListView(String product) {
        WebElement productBlock = chooseItem(product);
        WebElement button = productBlock.findElement(addToCartButton);
        button.click();
        log.info("Product added to cart: " + product);
    }

    public ProductDetailsPage navigateToProductPage(String product) {
        WebElement productBlock = chooseItem(product);
        hoverOverElement(productBlock);

        WebElement button = productBlock.findElement(moreButton);
        button.click();
        log.info("Navigate to product's page: " + product);
        return new ProductDetailsPage(driver, log);
    }

    public void changeLayoutToListView() {
        log.info("Switching to List View");
        click(listView);
    }
}
