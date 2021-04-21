package addproducttocarttests;

import base.BaseTest;
import constants.ConstantsMessages;
import constants.ConstantsURLs;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductDetailsPage;

import static constants.ConstantsMessages.productNameSKUDemo1;
import static constants.ConstantsMessages.productNameSKUDemo2;

public class AddProductToCartTest extends BaseTest {

    @Test
    public void addProductToCartFromGridView() {
        log.info("Starting Add Product To Cart from Grid View Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Adding product to cart from Homepage grid view
        HomePage homePage = new HomePage(driver, log);
        homePage.getProductListModule().addProductToCartFromGridView("Faded Short Sleeve T-shirts");

        //Waiting for Product Added modal to be displayed and check if correct product was added to cart
        homePage.getProductAddedModule().waitForModalContentToBeDisplayed();
        Assert.assertEquals(homePage.getProductAddedModule().getModalSuccessTitle(), ConstantsMessages.productAddedSuccessfullyTitle);
        Assert.assertEquals(homePage.getProductAddedModule().getModalProductName(),
                productNameSKUDemo1);
        log.info("Product successfully added to cart from Homepage: " +
                homePage.getProductAddedModule().getModalProductName());

        //Close Product Added modal and check number of items displayed in header
        homePage.getProductAddedModule().clickOnContinueShoppingBtn();
        Assert.assertEquals(homePage.getHeaderModule().getCartItemNoTxt(), ConstantsMessages.cartItem1Product);
        log.info("Shopping cart contains " + homePage.getHeaderModule().getCartItemNoTxt());

        //Check item displayed in shopping cart dropdown from header
        homePage.getHeaderModule().hoverOverShoppingCartBlock();
        Assert.assertEquals(homePage.getHeaderModule().countCartRows(), 1, "Incorrect number of products in cart");
        Assert.assertTrue(homePage.getHeaderModule().getCartItems().contains(productNameSKUDemo1));
        Assert.assertTrue(homePage.getHeaderModule().getProductSize().contains(ConstantsMessages.productSizeS));
        log.info("Expected product is displayed in Shopping cart dropdown from header: " +
                homePage.getHeaderModule().getCartItems());
    }

    @Test
    public void addTwoProductsToCartFromListView() throws InterruptedException {
        log.info("Starting Add Product To Cart from List View Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Navigate to Category page -> Women page
        HomePage homePage = new HomePage(driver, log);
        CategoryPage categoryPage = homePage.getHeaderModule().navigateToCategoryPage("Women");
        Assert.assertTrue(categoryPage.getCurrentPageTitle().contains(ConstantsMessages.womenPageTitle));
        log.info("Navigated to Category page");

        //Change to layout to list view
        categoryPage.getProductListModule().changeLayoutToListView();

        //Adding product to cart from Category page list view
        //Waiting for Product Added modal to be displayed and check if correct product was added to cart
        categoryPage.getProductListModule().addProductToCartFromListView("Blouse");
        categoryPage.getProductAddedModule().waitForModalContentToBeDisplayed();
        Assert.assertEquals(categoryPage.getProductAddedModule().getModalSuccessTitle(),
                ConstantsMessages.productAddedSuccessfullyTitle);
        Assert.assertEquals(categoryPage.getProductAddedModule().getModalProductName(),
                ConstantsMessages.productNameSKUDemo2);
        log.info("Product successfully added to cart from Women page: " +
                categoryPage.getProductAddedModule().getModalProductName());
        Thread.sleep(3000);

        //Close Product Added modal and check number of items displayed in header
        categoryPage.getProductAddedModule().clickOnContinueShoppingBtn();
        Assert.assertEquals(categoryPage.getHeaderModule().getCartItemNoTxt(), ConstantsMessages.cartItem1Product);
        log.info("Shopping cart contains " + categoryPage.getHeaderModule().getCartItemNoTxt());

        //Adding product to cart again from Women page list view
        //Waiting for Product Added modal to be displayed and check if correct product was added to cart
        categoryPage.getProductListModule().addProductToCartFromListView("Faded Short Sleeve T-shirts");
        categoryPage.getProductAddedModule().waitForModalContentToBeDisplayed();
        Assert.assertEquals(categoryPage.getProductAddedModule().getModalSuccessTitle(),
                ConstantsMessages.productAddedSuccessfullyTitle);
        Assert.assertEquals(categoryPage.getProductAddedModule().getModalProductName(),
                productNameSKUDemo1);
        log.info("Product successfully added to cart from Women page: " +
                categoryPage.getProductAddedModule().getModalProductName());
        Thread.sleep(3000);

        //Close Product Added modal and check number of items displayed in header
        categoryPage.getProductAddedModule().clickOnContinueShoppingBtn();
        Assert.assertEquals(categoryPage.getHeaderModule().getCartItemNoTxt(), ConstantsMessages.cartItem2Products);
        log.info("Shopping cart contains " + categoryPage.getHeaderModule().getCartItemNoTxt());
        Thread.sleep(3000);

        //Check items are displayed in shopping cart dropdown from header
        categoryPage.getHeaderModule().hoverOverShoppingCartBlock();
        Assert.assertEquals(categoryPage.getHeaderModule().countCartRows(), 2,
                "Incorrect number of product rows in cart");
        log.info("Expected products are displayed in Shopping cart dropdown from header: " +
                categoryPage.getHeaderModule().getCartItems());
    }

    @Test
    public void addProductToCartFromQuickView() throws InterruptedException {
        log.info("Starting Add Product To Cart from Quick View Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Open Quick View Modal
        HomePage homePage = new HomePage(driver, log);
        homePage.getProductListModule().openQuickViewModalFor("Blouse");
        Assert.assertEquals(homePage.getProductDetailsModule().getProductName(),
                productNameSKUDemo2, "Product name does not correspond");

        //Increase product quantity, choose size M and then add to cart
        homePage.getProductDetailsModule().updateProductQuantity(5);
        homePage.getProductDetailsModule().decreaseProductQuantity();
        Assert.assertEquals(homePage.getProductDetailsModule().getProductQuantity(), "4");
        homePage.getProductDetailsModule().selectProductSize(1);
        Assert.assertEquals(homePage.getProductDetailsModule().getSelectedProductSize(), "M");
        homePage.getProductDetailsModule().addProductToCart();

        //Wait for Product Added modal to be displayed and check that correct product was added to cart
        homePage.getProductAddedModule().waitForModalContentToBeDisplayed();
        Assert.assertEquals(homePage.getProductAddedModule().getModalSuccessTitle(), ConstantsMessages.productAddedSuccessfullyTitle);
        Assert.assertEquals(homePage.getProductAddedModule().getModalProductName(), productNameSKUDemo2);
        log.info("Product successfully added to cart from Quick View modal: " +
                homePage.getProductAddedModule().getModalProductName());

        //Close Product Added modal and check number of items displayed in header
        homePage.getProductAddedModule().clickOnContinueShoppingBtn();
        Assert.assertEquals(homePage.getHeaderModule().getCartItemNoTxt(), ConstantsMessages.cartItem4Products);
        log.info("Shopping cart contains " + homePage.getHeaderModule().getCartItemNoTxt());

        //Check items are displayed in shopping cart dropdown from header and size is correct
        homePage.getHeaderModule().hoverOverShoppingCartBlock();
        Assert.assertEquals(homePage.getHeaderModule().countCartRows(), 1,
                "Incorrect number of product rows in cart");
        Assert.assertTrue(homePage.getHeaderModule().getProductSize().contains(ConstantsMessages.productSizeM));
        log.info("Expected products are displayed in Shopping cart dropdown from header: " +
                homePage.getHeaderModule().getCartItems());
    }

    @Test
    public void addProductToCartFromProductPage() throws InterruptedException {
        log.info("Starting Add Product To Cart from Product page Test");

        //Open main URL
        openURL(ConstantsURLs.baseURL);

        //Navigate to Demo1 Product Page
        HomePage homePage = new HomePage(driver, log);
        ProductDetailsPage productDetailsPage = homePage.getProductListModule()
                .navigateToProductPage("Faded Short Sleeve T-shirts");
        Assert.assertEquals(productDetailsPage.getProductDetailsModule().getProductName(),
                productNameSKUDemo1, "H1 does not correspond");

        //Increase quantity, update product size and add it to cart
        productDetailsPage.getProductDetailsModule().increaseProductQuantity();
        productDetailsPage.getProductDetailsModule().selectProductSize("L");
        Assert.assertEquals(productDetailsPage.getProductDetailsModule().getSelectedProductSize(), "L");
        productDetailsPage.getProductDetailsModule().addProductToCart();

        //Wait for Product Added modal to be displayed and check that correct product was added to cart
        productDetailsPage.getProductAddedModule().waitForModalContentToBeDisplayed();
        Assert.assertEquals(productDetailsPage.getProductAddedModule().getModalSuccessTitle(),
                ConstantsMessages.productAddedSuccessfullyTitle);
        Assert.assertEquals(productDetailsPage.getProductAddedModule().getModalProductName(),
                productNameSKUDemo1);
        log.info("Product successfully added to cart from Product page: "
                + productDetailsPage.getProductAddedModule().getModalProductName());
        Assert.assertTrue(productDetailsPage.getProductAddedModule().getProductAttributes()
                .contains(ConstantsMessages.productSizeL));
        log.info("Product added in size: " + productDetailsPage.getProductAddedModule().getProductAttributes());
        Assert.assertEquals(productDetailsPage.getProductAddedModule().getProductQuantity(), "2");
        log.info("Product quantity added: " + productDetailsPage.getProductAddedModule().getProductQuantity());

        //Close Product Added modal and check number of items displayed in header
        productDetailsPage.getProductAddedModule().clickOnContinueShoppingBtn();
        Assert.assertEquals(productDetailsPage.getHeaderModule().getCartItemNoTxt(), ConstantsMessages.cartItem2Products);
        log.info("Shopping cart contains " + productDetailsPage.getHeaderModule().getCartItemNoTxt());

        //Check items are displayed in shopping cart dropdown from header and size is correct
        productDetailsPage.getHeaderModule().hoverOverShoppingCartBlock();
        Assert.assertEquals(productDetailsPage.getHeaderModule().countCartRows(), 1,
                "Incorrect number of product rows in cart");
        log.info("Expected products are displayed in Shopping cart dropdown from header: " +
                productDetailsPage.getHeaderModule().getCartItems());
        log.info("Expected product size is displayed in Shopping cart dropdown from header: " +
                productDetailsPage.getHeaderModule().getProductSize());
    }
}
