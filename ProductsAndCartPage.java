package Pages;

import Elements.ElementAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsAndCartPage {

    WebDriver driver;
    Login_SignupPage loginSignUpObj;
    ElementAction action;
    ///////////////////////////////////  LOCATORS  /////////////////////////
    By PRODUCTStab = By.xpath("//a[@href='/products']");
    By SEARCHLABEL = By.id("search_product");
    By SEARCHICON = By.id("submit_search");
    By LISTOFSEARCHRESULTITEMS = By.xpath("//div[@class='productinfo text-center']//p");
    By PRICEOFALLELEMENTS = By.xpath("//div[@class='productinfo text-center']//h2");
    By PRICEOFCARTELEMENTS = By.xpath("//td[@class='cart_price']//p");
    By CARTQUANTITY = By.xpath("//td[@class='cart_quantity']//button");
    By CARTTOTALPRICE = By.xpath("//td[@class='cart_total']//p");
    By FIRSTPRODUCTINFO = By.xpath("(//div[@class='productinfo text-center'])[1]");
    By ADDTOCARTFIRSTPROD = By.xpath("(//a[@data-product-id='1'])[1]");
    By CONTINUESHOPING = By.xpath("//button[@class='btn btn-success close-modal btn-block']");
    By SECONDPRODUCTINFO = By.xpath("(//div[@class='productinfo text-center'])[2]");
    By ADDTOCARTSECONDPROD = By.xpath("(//a[@data-product-id='2'])[1]");
    By VIEWCART = By.xpath("//a[@href='/view_cart']//u");
    By CARTPRODUCTSDes = By.xpath("//td[@class='cart_description']//a");


    public ProductsAndCartPage(WebDriver driver){
        this.driver = driver;
        action = new ElementAction(this.driver);
    }
    // GO TO PRODUCTS PAGE
    public void navigateToProductsPage(){
        ElementAction.insertwaitToBeVisible(PRODUCTStab);
        ElementAction.clickBtn(PRODUCTStab);
    }
    // SEARCH ON SPECIFIC COLLECTION OF ITEMS OR ONE ITEM
    public void enterTextInSearchField(String searchedValue){
        ElementAction.clearTxtField(SEARCHLABEL);
        driver.findElement(SEARCHLABEL).sendKeys(searchedValue);
    }
    public void clickOnSearchIcon(){
        ElementAction.insertwaitToBeVisible(SEARCHICON);
        ElementAction.clickBtn(SEARCHICON);
    }
    // ADD TO CART FIRST AND SECOND PRODUCTS
    public void hoverOverOnItemAndAddToCard() {
        ElementAction.insertwaitToBeVisible(FIRSTPRODUCTINFO);
        ElementAction.mouseOverOnItem(FIRSTPRODUCTINFO,ADDTOCARTFIRSTPROD);
        ElementAction.clickBtn(CONTINUESHOPING);

        //ADDED THE FIRST ITEM TWICE TO MAKE SURE THAT THE TOTAL CALCULATIONS ARE CORRECT
        /*ElementAction.mouse_over_on_item(FIRSTPRODUCTINFO,ADDTOCARTFIRSTPROD);
        ElementAction.clickbtn(CONTINUESHOPING);*/

        ElementAction.insertwaitToBeVisible(SECONDPRODUCTINFO);
        ElementAction.mouseOverOnItem(SECONDPRODUCTINFO,ADDTOCARTSECONDPROD);
        ElementAction.clickBtn(VIEWCART);
    }
    //GET ANY LIST OF ITEMS FUNCTION
    public List<WebElement> getListOfItems(By items){
        ElementAction.insertwaitToBeVisible(items);
        List<WebElement> products;
        products = driver.findElements(items);
        return products;
    }
    // GET ALL PRODUCTS IN PRODUCTS PAGE AFTER SEARCH
    public List<WebElement> getAllProductsAfterSearch(){
        return  getListOfItems(LISTOFSEARCHRESULTITEMS);
    }
    //  GET IN CART PRODUCTS
    public List<WebElement> getSelectedProductsInCart(){
        return  getListOfItems(CARTPRODUCTSDes);
    }
    //  GET PRICE OF ALL PRODUCTS
    public List<WebElement> getPriceOfAllProducts(){
        return getListOfItems(PRICEOFALLELEMENTS);
    }
    //  GET PRICE OF IN CART PRODUCTS
    public List<WebElement> getPriceOfCartProducts(){
        return  getListOfItems(PRICEOFCARTELEMENTS);
    }
    //  GET QUANTITY OF IN CART PRODUCTS
    public List<WebElement> getQuantityOfCart() {
        return getListOfItems(CARTQUANTITY);
    }
    // GET TOTAL PRICE OF EACH PRODUCT IN CART
    public List<WebElement> getTotalOfCart() {
        return getListOfItems(CARTTOTALPRICE);
    }
    //  GET THE PRICE WITHOUT RS.
    public String getValueOfPriceWithout_RS(String fullPrice) {
        String correctPrice = fullPrice.substring(4,fullPrice.length());
        return correctPrice;
    }
}
