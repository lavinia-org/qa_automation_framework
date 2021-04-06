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
    public void addProductToCartFromGridView() {
        log.info("Starting Add Product To Cart from Grid View Test");

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
    public void addTwoProductsToCartFromListView() {
        log.info("Starting Add Product To Cart from List View Test");

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

    @Test
    public void addProductToCartFromQuickView() {
        log.info("Starting Add Product To Cart from Quick View Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Open Quick View Modal
        BasePage basePage = new BasePage(driver, log);
        basePage.openQuickViewModalForDemo1();
        Assert.assertEquals(basePage.getProductDemo1H1(), ConstantsMessages.productNameSKUDemo1, "H1 does not correspond");

        //Increase product quantity, choose size M and then add to cart
        basePage.updateProductQuantity(5);
        basePage.decreaseProductQuantity();
        basePage.selectProductSize(1);
        Assert.assertEquals(basePage.getSelectedProductSize(), "M");
        basePage.addProductToCart();

        //Wait for Product Added modal to be displayed and check that correct product was added to cart
        basePage.waitForProductAddedModalToBeDisplayed();
        Assert.assertEquals(basePage.getModalH2(), ConstantsMessages.productAddedSuccessfullyH2);
        Assert.assertEquals(basePage.getModalProductName(), ConstantsMessages.productNameSKUDemo1);
        log.info("Product successfully added to cart from Quick View modal: " + basePage.getModalProductName());

        //Close Product Added modal and check number of items displayed in header
        basePage.clickOnContinueShoppingBtn();
        Assert.assertEquals(basePage.getCartItemNoTxt(), ConstantsMessages.cartItem4Products);
        log.info("Shopping cart contains " + basePage.getCartItemNoTxt());

        //Check items are displayed in shopping cart dropdown from header and size is correct
        basePage.hoverOverShoppingCartBlock();
        Assert.assertEquals(basePage.countCartRows(), 1, "Incorrect number of product rows in cart");
        Assert.assertTrue(basePage.getProductSize().contains(ConstantsMessages.productSizeM));
        log.info("Expected products are displayed in Shopping cart dropdown from header: " + basePage.getCartItems());
    }

    @Test
    public void addProductToCartFromProductPage() throws InterruptedException {
        log.info("Starting Add Product To Cart from Product page Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Navigate to Demo1 Product Page
        HomePage homePage = new HomePage(driver, log);
        homePage.navigateToProductPageDemo1();
        BasePage basePage = new BasePage(driver, log);
        Assert.assertEquals(basePage.getProductDemo1H1(), ConstantsMessages.productNameSKUDemo1, "H1 does not correspond");

        //Increase quantity, update product size and add it to cart
        basePage.increaseProductQuantity();
        basePage.selectProductSize("L");
        Assert.assertEquals(basePage.getSelectedProductSize(), "L");
        basePage.addProductToCart();

        //Wait for Product Added modal to be displayed and check that correct product was added to cart
        basePage.waitForProductAddedModalToBeDisplayed();
        Assert.assertEquals(basePage.getModalH2(), ConstantsMessages.productAddedSuccessfullyH2);
        Assert.assertEquals(basePage.getModalProductName(), ConstantsMessages.productNameSKUDemo1);
        log.info("Product successfully added to cart from Product page: " + basePage.getModalProductName());
        Assert.assertTrue(basePage.getProductAddedModalAttributes().contains(ConstantsMessages.productSizeL));
        log.info("Product added in size: " + basePage.getProductAddedModalAttributes());
        Assert.assertEquals(basePage.getProductAddedModalQuantity(), "2");
        log.info("Product quantity added: " + basePage.getProductAddedModalQuantity());

        //Close Product Added modal and check number of items displayed in header
        basePage.clickOnContinueShoppingBtn();
        Assert.assertEquals(basePage.getCartItemNoTxt(), ConstantsMessages.cartItem2Products);
        log.info("Shopping cart contains " + basePage.getCartItemNoTxt());

        //Check items are displayed in shopping cart dropdown from header and size is correct
        basePage.hoverOverShoppingCartBlock();
        Assert.assertEquals(basePage.countCartRows(), 1, "Incorrect number of product rows in cart");
        log.info("Expected products are displayed in Shopping cart dropdown from header: " + basePage.getCartItems());
        log.info("Expected product size is displayed in Shopping cart dropdown from header: " + basePage.getProductSize());
    }
}
