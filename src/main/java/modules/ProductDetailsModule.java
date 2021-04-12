package modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.BasePage;

public class ProductDetailsModule extends BasePage {

    private By productName = By.cssSelector(".product .pb-center-column >h1");
    private By addToCartBtn = By.cssSelector(".product .pb-right-column button.exclusive");
    private By quantityField = By.id("quantity_wanted");
    private By increaseQuantityBtn = By.cssSelector(".product .pb-right-column .button-plus");
    private By decreaseQuantityBtn = By.cssSelector(".product .pb-right-column .button-minus");
    private By sizeDropdown = By.id("group_1");

    public ProductDetailsModule(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void addProductToCart() {
        click(addToCartBtn);
        log.info("Product added to cart");
    }

    public void increaseProductQuantity() {
        log.info("Increasing product quantity");
        find(increaseQuantityBtn).click();
    }

    public void decreaseProductQuantity() {
        log.info("Decreasing product quantity");
        find(decreaseQuantityBtn).click();
    }

    public void updateProductQuantity(int quantity) {
        log.info("Updating product quantity to: " + quantity);
        type(quantity, quantityField);
    }

    public String getProductQuantity() {
        waitForVisibility(quantityField, 10);
        Assert.assertTrue(find(quantityField).isDisplayed());
        String productQuantity = find(quantityField).getText();

        return productQuantity;
    }

    public void selectProductSize(int i) {
        log.info("Selecting option " + i + " from Size dropdown");
        WebElement dropdownElement = find(sizeDropdown);

        Select fromDropdown = new Select(dropdownElement);
        fromDropdown.selectByIndex(i);
    }

    public void selectProductSize(String size) {
        log.info("Selecting option " + size + " from Size dropdown");
        WebElement dropdownElement = find(sizeDropdown);

        Select fromDropdown = new Select(dropdownElement);
        fromDropdown.selectByVisibleText(size);
    }

    public String getSelectedProductSize() {
        WebElement dropdownElement = find(sizeDropdown);
        Select fromDropdown = new Select(dropdownElement);
        String selectedOption = fromDropdown.getFirstSelectedOption().getText();
        log.info("Product size selected is: " + selectedOption);
        return selectedOption;
    }

    public String getProductDemo1Title() {
        waitForVisibility(productName, 10);
        String modalDemo1H1 = getProductTitle(productName);
        return modalDemo1H1;
    }
}
