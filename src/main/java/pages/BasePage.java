package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected Logger log;

    private By pageTitle = By.className("page-heading");
    private By productBlock = By.cssSelector(".product_list .product-container");

    public BasePage(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Find element using given locator
     * @param locator
     * @return Webelement
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Click on element with given locator
     * @param locator
     */
    protected void click(By locator) {
        find(locator).click();
    }

    /**
     * Clears input and types given text into element with given locator
     * @param text
     * @param locator
     */
    protected void type(String text, By locator) {
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    protected void type(int i, By locator) {
        find(locator).clear();
        find(locator).sendKeys("" + i);
    }

    /**
     * Get URL of current page from browser
     * @return URL String
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Get H1 of current page
     * @return String page title
     */
    public String getCurrentPageTitle() {
        waitForVisibility(pageTitle, 10);
        return find(pageTitle).getText();
    }

    /**
     * Press Key on locator
     * @param locator
     * @param key
     */
    protected void pressKey(By locator, Keys key) {
        log.info("Pressing " + key.name());
        find(locator).sendKeys(key);
    }

    /**
     * Perform mouse hover over element
     * @param element
     */
    protected void hoverOverElement(By element) {
        Actions action = new Actions(driver);
        action.moveToElement(find(element)).build().perform();
    }

    protected void hoverOverElement(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    /**
     * Waits for By locator's visibility
     * @param locator
     * @param defaultTimeout
     */
    public void waitForVisibility(By locator, int defaultTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, defaultTimeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void checkIfElementIsDisplayed(By locator) {
        find(locator).isDisplayed();
    }

    public String getText(By locator) {
        return find(locator).getText();
    }

    protected String getTitleAttribute(By locator) {
        WebElement element = find(locator);
        String elementTitle = element.getAttribute("title");
        return elementTitle;
    }

    protected String getAltAttribute(WebElement element) {
        String elementTitle = element.getAttribute("alt");
        return elementTitle;
    }

    protected String getProductTitle(By locator) {
        String modalH1 = find(locator).getText();
        return modalH1;
    }

    protected void switchToFrame(By iFrameLocator) {
        driver.switchTo().frame(find(iFrameLocator));
    }

    /**
     * Takes all product blocks from Product List section, adds them to a list and iterates over.
     * Stops at the product specified as a parameter and returns it
     * @param product
     * @return chosen product
     */
    protected WebElement chooseItem(String product) {
        List<WebElement> products = driver.findElements(productBlock);
        Iterator<WebElement> itr = products.iterator();
        WebElement chosenItem = null;

        while(itr.hasNext()) {
            WebElement item = itr.next();
            if (item.getText().contains(product)&&item.isDisplayed()) {
                chosenItem = item;
                break;
            }
        }
        return chosenItem;
    }

    protected void checkIfDisplayed(By locator) {
        waitForVisibility(locator, 10);
        find(locator).isDisplayed();
    }
}
