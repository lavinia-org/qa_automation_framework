package addproducttocarttests;

import base.BaseTest;
import constants.ConstantsMessages;
import constants.ConstantsURLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;

public class AddProductToCartTest extends BaseTest {

    @Test
    public void addProductToCartFromHomepage() {
        log.info("Starting Add Product To Cart from Homepage Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Adding product to cart from Homepage grid
        HomePage homePage = new HomePage(driver, log);
        homePage.addProductToCart();

        //Waiting for Product Added modal to be displayed and check if correct product was added to cart
        BasePage basePage = new BasePage(driver, log);
        basePage.waitForProductAddedModalToBeDisplayed();
        Assert.assertEquals(basePage.getModalH2(), ConstantsMessages.productAddedSuccessfullyH2);
        Assert.assertEquals(basePage.getModalProductName(), ConstantsMessages.productNameSKUDemo1);
        log.info("Product successfully added to cart from Homepage: " + basePage.getModalProductName());

        //Close Product Added modal and check number of items displayed in header
        basePage.clickOnContinueShoppingBtn();
        Assert.assertEquals(basePage.getCartItemNoTxt(), ConstantsMessages.cartItem1Product);
        log.info("Shopping cart contains " + basePage.getCartItemNoTxt());

        //Check item displayed in shopping cart dropdown from header
        basePage.hoverOverShoppingCartBlock();
        Assert.assertEquals(basePage.countCartItems(), 1, "Incorrect number of products in cart");
        Assert.assertEquals(basePage.getCartProductTitle(), ConstantsMessages.productNameSKUDemo1);
        Assert.assertTrue(basePage.getProductSize().contains(ConstantsMessages.productSizeS));
        log.info("Expected product is displayed in Shopping cart dropdown from header: " + basePage.getCartProductTitle());
    }
}
