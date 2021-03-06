package modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
        log.info("Increasing product quantity by 1");
        find(increaseQuantityBtn).click();
    }

    public void decreaseProductQuantity() {
        log.info("Decreasing product quantity by 1");
        find(decreaseQuantityBtn).click();
    }

    public void updateProductQuantity(int quantity) {
        log.info("Updating product quantity to: " + quantity);
        type(quantity, quantityField);
    }

    public String getProductQuantity() {
        fluentWaitForVisibility(productName, 10, 1);
        String productQuantity = find(quantityField).getAttribute("value");
        log.info("Product quantity is now: " + productQuantity);
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

    public String getProductName() {
        fluentWaitForVisibility(productName, 10, 1);
        String name = getProductTitle(productName);
        return name;
    }
}
