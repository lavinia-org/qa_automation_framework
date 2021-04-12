package modules;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ProductListModule extends BasePage {

    private By quickViewFrame = By.className("fancybox-iframe");
    private By quickViewProductSKUDemo1 = By.cssSelector("#homefeatured li:nth-child(1) a.quick-view");
    private By imageForProductSKUDemo1 = By.cssSelector("img[title='Faded Short Sleeve T-shirts']");
    private By productName = By.cssSelector(".product .pb-center-column >h1");
    private By listView = By.id("list");

    public ProductListModule(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Hovers over a product to show QuickView link and clicks on it
     * Switches to QuickView iFrame and waits for H1 to be visible - checks if it is
     *
     * @param hoverLocator
     * @param clickLocator
     * @param iFrameLocator
     * @param H1Locator
     */
    protected void openQuickViewModal(By hoverLocator, By clickLocator, By iFrameLocator, By H1Locator) {
        hoverOverElement(hoverLocator);
        click(clickLocator);
        switchToFrame(iFrameLocator);
        waitForVisibility(H1Locator, 10);
        find(H1Locator).isDisplayed();
        log.info("Opened Quick View Modal for Product: " + find(H1Locator).getText());
    }

    public void addProductToCartFromGridView(By hoverLocator, By clickLocator) {
        waitForVisibility(hoverLocator, 10);
        hoverOverElement(hoverLocator);
        click(clickLocator);
    }

    public void addProductToCartFromListView(By locator) {
        click(locator);
    }

    public void navigateToProductPage(By hoverLocator, By clickLocator) {
        hoverOverElement(hoverLocator);
        click(clickLocator);
    }

    public void openQuickViewModalForDemo1() {
        openQuickViewModal(imageForProductSKUDemo1, quickViewProductSKUDemo1, quickViewFrame, productName);
    }

    public void changeLayoutToListView() {
        log.info("Switching to List View");
        click(listView);
    }
}
