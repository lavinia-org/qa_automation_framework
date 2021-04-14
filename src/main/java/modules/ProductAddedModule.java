package modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ProductAddedModule extends BasePage {

    private By successTitle = By.cssSelector(".layer_cart_product h2:nth-child(2)");
    private By productName = By.cssSelector(".layer_cart_product .product-name");
    private By continueShoppingBtn = By.cssSelector(".clearfix .continue");
    private By productAttributes = By.id("layer_cart_product_attributes");
    private By productQuantity = By.id("layer_cart_product_quantity");

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
        waitForModalContentToBeDisplayed(successTitle);
    }

    public String getModalSuccessTitle() {
        String modalSuccessTitle = getText(successTitle);
        return modalSuccessTitle;
    }

    public String getModalProductName() {
        String modalProductName = getText(productName);
        return modalProductName;
    }

    public void clickOnContinueShoppingBtn() {
        log.info("Clicking on Continue Shopping button from Product Added Modal");
        find(continueShoppingBtn).click();
    }

    public String getProductAttributes() {
        String attributes = find(productAttributes).getText();
        return attributes;
    }

    public String getProductQuantity() {
        String quantity = find(productQuantity).getText();
        return quantity;
    }
}
