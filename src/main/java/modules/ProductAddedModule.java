package modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ProductAddedModule extends BasePage {

    private By productAddedModalH2 = By.cssSelector(".layer_cart_product h2:nth-child(2)");
    private By productAddedModalProductName = By.cssSelector(".layer_cart_product .product-name");
    private By productAddedModalContinueShoppingBtn = By.cssSelector(".clearfix .continue");
    private By productAddedModalAttributes = By.id("layer_cart_product_attributes");
    private By productAddedModalQuantity = By.id("layer_cart_product_quantity");

    public ProductAddedModule(WebDriver driver, Logger log) {
        super(driver, log);
    }

    protected void waitForModalContentToBeDisplayed(By locator) {
        log.info("Waiting for modal to be displayed...");
        waitForVisibility(locator, 10);
        checkIfElementIsDisplayed(locator);
        log.info("Modal content displayed!");
    }

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
}
