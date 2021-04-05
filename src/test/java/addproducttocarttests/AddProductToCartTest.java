package addproducttocarttests;

import base.BaseTest;
import constants.ConstantsMessages;
import constants.ConstantsURLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.HomePage;
import pages.WomenPage;

public class AddProductToCartTest extends BaseTest {

    @Test
    public void addProductToCartFromHomepage() {
        log.info("Starting Add Product To Cart from Homepage Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Adding product to cart from Homepage grid view
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
        Assert.assertEquals(basePage.countCartRows(), 1, "Incorrect number of products in cart");
        Assert.assertEquals(basePage.getCartProductTitle(), ConstantsMessages.productNameSKUDemo1);
        Assert.assertTrue(basePage.getProductSize().contains(ConstantsMessages.productSizeS));
        log.info("Expected product is displayed in Shopping cart dropdown from header: " + basePage.getCartItems());
    }

    @Test
    public void addTwoProductsToCartFromWomenPage() {
        log.info("Starting Add Product To Cart from Women Page Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Navigate to Women page
        BasePage basePage = new BasePage(driver, log);
        WomenPage womenPage = basePage.openWomenPage();
        Assert.assertTrue(basePage.getCurrentPageH1().contains(ConstantsMessages.womenPageH1));
        log.info("Women page is opened");

        //Change to layout to list view
        basePage.changeLayoutToListView();

        //Adding product to cart from Women page list view
        //Waiting for Product Added modal to be displayed and check if correct product was added to cart
        womenPage.addFirstProductToCart();
        basePage.waitForProductAddedModalToBeDisplayed();
        Assert.assertEquals(basePage.getModalH2(), ConstantsMessages.productAddedSuccessfullyH2);
        Assert.assertEquals(basePage.getModalProductName(), ConstantsMessages.productNameSKUDemo2);
        log.info("Product successfully added to cart from Women page: " + basePage.getModalProductName());

        //Close Product Added modal and check number of items displayed in header
        basePage.clickOnContinueShoppingBtn();
        Assert.assertEquals(basePage.getCartItemNoTxt(), ConstantsMessages.cartItem1Product);
        log.info("Shopping cart contains " + basePage.getCartItemNoTxt());

        //Adding product to cart again from Women page list view
        //Waiting for Product Added modal to be displayed and check if correct product was added to cart
        womenPage.addSecondProductToCart();
        basePage.waitForProductAddedModalToBeDisplayed();
        Assert.assertEquals(basePage.getModalH2(), ConstantsMessages.productAddedSuccessfullyH2);
        Assert.assertEquals(basePage.getModalProductName(), ConstantsMessages.productNameSKUDemo1);
        log.info("Product successfully added to cart from Women page: " + basePage.getModalProductName());

        //Close Product Added modal and check number of items displayed in header
        basePage.clickOnContinueShoppingBtn();
        Assert.assertEquals(basePage.getCartItemNoTxt(), ConstantsMessages.cartItem2Products);
        log.info("Shopping cart contains " + basePage.getCartItemNoTxt());

        //Check items are displayed in shopping cart dropdown from header
        basePage.hoverOverShoppingCartBlock();
        Assert.assertEquals(basePage.countCartRows(), 2, "Incorrect number of product rows in cart");
        log.info("Expected products are displayed in Shopping cart dropdown from header: " + basePage.getCartItems());
    }
}
