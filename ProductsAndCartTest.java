package Tests;

import Elements.ElementAction;
import Pages.ProductsAndCartPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class ProductsAndCartTest {

    WebDriver driver;
    ProductsAndCartPage productsPageobj;
    List<WebElement> webElementList;
    List<WebElement> webElementList2;
    List<WebElement> webElementList3;

    SoftAssert softAssert;
    String URL = "http://automationexercise.com";

    @BeforeMethod
    public void setup()
    {
//        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        productsPageobj = new ProductsAndCartPage(driver);
        ElementAction action = new ElementAction(driver);
    }
    @Test
    public void verifyNavigationToHome()
    {
        Assert.assertEquals(driver.getCurrentUrl().toString(),"https://automationexercise.com/");
    }
    @Test
    public void verify_navigation_to_ALL_PRODUCTS_page()
    {
        productsPageobj.navigateToProductsPage();
        Assert.assertEquals(driver.getCurrentUrl().toString(),"https://automationexercise.com/products");
    }
    @Test
    public void verify_search_products()
    {
        softAssert = new SoftAssert();
        ///////////// NAVIGATE TO PRODUCTS PAGE /////////////
        productsPageobj.navigateToProductsPage();
        //////////// ENTER VALUE IN SEARCH FIELD //////////////
        String value = "blue";
        value = value.toLowerCase();
        productsPageobj.enterTextInSearchField(value);
        productsPageobj.clickOnSearchIcon();
        ElementAction.scrollDown();
        //////// GET ALL PRODUCTS IN PRODUCTS' PAGE AFTER CLICK SEARCH ICON //////
        webElementList = productsPageobj.getAllProductsAfterSearch();
        //////// ASSERT THAT ALL SEARCH RESULT CONTAINS SEARCH VALUE //////
        for(int i=0;i<webElementList.size();i++)
        {
            softAssert.assertTrue(webElementList.get(i).getText().toLowerCase().contains(value));
            System.out.println(webElementList.get(i).getText().toLowerCase());
        }
        softAssert.assertAll();
    }
    @Test
    public void verify_ALL_PRODUCTS_in_cart()
    {
        softAssert = new SoftAssert();
        ///////////////// NAVIGATE TO PRODUCTS PAGE ///////////
        productsPageobj.navigateToProductsPage();
        //////////////// GET ALL PRODUCTS' NAME SAVE THEM IN STRING ARRAY//////////
        webElementList = productsPageobj.getAllProductsAfterSearch();
        String arr[] = new String[webElementList.size()];
        for (int i=0;i<webElementList.size();i++)
        {
            arr[i] = String.valueOf(webElementList.get(i).getText());
        }
        //////////// ADD THE FIRST TWO ITEMS IN CART /////////////////
        productsPageobj.hoverOverOnItemAndAddToCard();
        //////////////// GET PRODUCTS IN THE CART ///////////////////
        webElementList2 = productsPageobj.getSelectedProductsInCart();
        /////////////// ASSERT THAT SELECTED ITEMS IN THE CART////////
        for(int i=0;i<webElementList2.size();i++)
        {
            softAssert.assertEquals(arr[i],String.valueOf(webElementList2.get(i).getText()));
            ///////// PRINT VALUES TO MAKE SURE THAT FUNCTION
            System.out.println(arr[i]);
            System.out.println(webElementList2.get(i).getText());
        }
        softAssert.assertAll();
    }
    @Test
    public void verifyPricesAndQuantityOfCartProducts(){
        softAssert = new SoftAssert();
        ///////////////// NAVIGATE TO PRODUCTS PAGE ///////////
        productsPageobj.navigateToProductsPage();
        //////////////// GET ALL PRODUCTS' NAME SAVE THEM IN STRING ARRAY//////////
        webElementList = productsPageobj.getPriceOfAllProducts();
        String itemName[] = new String[webElementList.size()];
        for (int i=0;i<webElementList.size();i++)
        {
            itemName[i] = String.valueOf(webElementList.get(i).getText());
        }
        //////////// ADD THE FIRST TWO ITEMS IN CART /////////////////
        productsPageobj.hoverOverOnItemAndAddToCard();
        //////////////// GET PRODUCTS IN THE CART ///////////////////
        webElementList2 = productsPageobj.getPriceOfCartProducts();
        /////////////// ASSERT THAT SELECTED ITEMS IN THE CART////////
        for(int i=0;i<webElementList2.size();i++)
        {
            softAssert.assertEquals(itemName[i],String.valueOf(webElementList2.get(i).getText()));

            System.out.println(itemName[i]);
            System.out.println(webElementList2.get(i).getText());
        }
        softAssert.assertAll();

    }
    @Test
    public void verifyPrices(){
        softAssert = new SoftAssert();
        ///////////////// NAVIGATE TO PRODUCTS PAGE ///////////
        productsPageobj.navigateToProductsPage();
        //////////////// ADD ITEMS TO THE CART //////////
        productsPageobj.hoverOverOnItemAndAddToCard();
        ////////////// GET THE PRICE OF IN CART PRODUCTS ////////
        webElementList = productsPageobj.getPriceOfCartProducts();
        ////////////// GET THE QUANTITY OF IN CART PRODUCTS /////
        webElementList2 = productsPageobj.getQuantityOfCart();
        ////////////// GET THE TOTAL OF IN CART PRODUCTS //////
        webElementList3 = productsPageobj.getTotalOfCart();
        // SAVE EACH LIST IN STRING ARRAY //////////////
        String priceOfCartProducts[] = new String[webElementList.size()];
        String quantityOfCartProducts[] = new String[webElementList.size()];
        String totalOfCartProducts[] = new String[webElementList.size()];
        int actualResultOfTotal[] = new int[webElementList.size()];

        for (int i=0;i<webElementList.size();i++)
        {
            priceOfCartProducts[i] = productsPageobj.getValueOfPriceWithout_RS(String.valueOf(webElementList.get(i).getText()));
            quantityOfCartProducts[i] = String.valueOf(webElementList2.get(i).getText());
            totalOfCartProducts[i] = productsPageobj.getValueOfPriceWithout_RS(String.valueOf(webElementList3.get(i).getText()));
            actualResultOfTotal[i] = Integer.parseInt(priceOfCartProducts[i]) * Integer.parseInt(quantityOfCartProducts[i]);
            System.out.println(priceOfCartProducts[i]);
            System.out.println(quantityOfCartProducts[i]);
            System.out.println(totalOfCartProducts[i]);
            System.out.println(actualResultOfTotal[i]);
            softAssert.assertEquals(actualResultOfTotal[i],Integer.parseInt(totalOfCartProducts[i]));
        }

        softAssert.assertAll();

    }
    @AfterMethod
    public void closeTab(){
        driver.close();
    }

}
