package Pages;

import Elements.ElementAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login_SignupPage {

    WebDriver driver;
    ElementAction action;

    /////////////////////////// LOCATORS ///////////////////////////
    By LOGIN_SIGNUPtab = By.xpath("//a[@href='/login']");
    By SIGNUPNAME = By.xpath("//*[@data-qa='signup-name']");
    By SIGNUPEMAIL = By.xpath("//*[@data-qa='signup-email']");
    By SIGNUPbtn = By.xpath("//*[@data-qa='signup-button']");
    By NEWUSERSIGNUP = By.xpath("//div[@class='signup-form']//h2");
    /////////////////////// GENDER LOCATORS////////////////////////////////
    By MR = By.id("id_gender1");
    By MRS = By.id("id_gender2");
    ///////////////////////////////////////////////////////////////////////
    By SIGNUPASS = By.xpath("//*[@data-qa='password']");
    /////////////////////// Locators For Date Of Birth /////////////////////
    By DAY = By.xpath("//*[@data-qa='days']");
    By MONTH = By.xpath("//*[@data-qa='months']");
    By YEARS = By.xpath("//*[@data-qa='years']");
    ////////////////////////// ACCOUNT INFORMATION  /////////////////////////////////////
    By ENTERACCOUNTINFORMATION = By.xpath("(//div[@class='login-form']//h2)[1]");
    By FIRSTNAME = By.id("first_name");
    By LASTNAME = By.id("last_name");
    By COMPANY = By.id("company");
    By ADDRESS1 = By.id("address1");
    By ADDRESS2 = By.id("address2");
    By COUNTRY = By.id("country");
    By STATE = By.id("state");
    By CITY = By.id("city");
    By ZIPCODE = By.id("zipcode");
    By MOBILENUMBER = By.id("mobile_number");
    By NEWSLETTER = By.id("newsletter");
    By SPECIALOFFER = By.id("optin");
    By CREATEACCOUNT = By.xpath("//*[@data-qa='create-account']");
    By ACCOUNTCREATED = By.xpath("//h2[@class='title text-center']//b");
    By CONTINUEAFTERREGISTERbtn = By.xpath("//*[@data-qa='continue-button']");

    ///////////////////////// LOG IN LOCATORS ////////////////////////////////////
    By REGEMAIL = By.xpath("//*[@data-qa='login-email']");
    By REGPASSWORD = By.xpath("//*[@data-qa='login-password']");
    By LOGINbtn = By.xpath("//*[@data-qa='login-button']");
    By LOGGEDINASUSER = By.xpath("//i[contains(@class,'fa-user')]//parent::a");
    By DELETEACCOUNTtab = By.xpath("//a[@href='/delete_account']");
    By ACCOUNTDELETED = By.xpath("//h2[@class='title text-center']//b");
    By LOGINTOYOURACCOUNT = By.xpath("//div[@class='login-form']//h2");
    By LOGOUTtab = By.xpath("//a[@href='/logout']");
    By ERRORMSG = By.xpath("//form[@method='POST']//p");

    //////////////////////////////  CONSTRUCTOR /////////////////////////////////////////////
    public Login_SignupPage(WebDriver driver){
        this.driver = driver;
        action = new ElementAction(this.driver);
    }

    ///////////////////////////////  SIGN UP FUNCTIONS/////////////////////////////////////////
    public String newuserSignup(){
        return driver.findElement(NEWUSERSIGNUP).getText();
    }
    public void navigateToSignInUp()
    {
        ElementAction.clickBtn(LOGIN_SIGNUPtab);
    }
    public void clickOnSignUpBtn()
    {
        ElementAction.clickBtn(SIGNUPbtn);
    }
    public String getTxt_ENTERACCOUNTINFORMATION()
    {
        return driver.findElement(ENTERACCOUNTINFORMATION).getText();
    }
    //////// ENTER NAME AND EMAIL FOR NEW USER /////////
    public void entername(String name1)
    {
        driver.findElement(SIGNUPNAME).sendKeys(name1);
    }
    public void enterEmail(String email1)
    {
        driver.findElement(SIGNUPEMAIL).sendKeys(email1);
    }

    public void enterNewUserTitle()
    {
        ElementAction.checkboxClick(MRS);
    }
    public void enterPassword(String newpassWord)
    {
        driver.findElement(SIGNUPASS).sendKeys(newpassWord);
    }
    public void enterNewUserDateOfBirth(String day1, String month1, String year1)
    {
        ElementAction.selectFromDropDownList(DAY,day1);
        ElementAction.selectFromDropDownList(MONTH,month1);
        ElementAction.selectFromDropDownList(YEARS,year1);
    }
    public void enterNewUserCheckNewsLetterOrSpecialOffer()
    {
        ElementAction.clickBtn(NEWSLETTER);
        ElementAction.clickBtn(SPECIALOFFER);
    }
    public void enterNewUserNameAndCompany(String fname1, String lname1, String company1)
    {

        driver.findElement(FIRSTNAME).sendKeys(fname1);
        driver.findElement(LASTNAME).sendKeys(lname1);
        driver.findElement(COMPANY).sendKeys(company1);
    }
    public void enterNewUserAddress(String addressone, String addresstwo, String country1)
    {
        driver.findElement(ADDRESS1).sendKeys(addressone);
        driver.findElement(ADDRESS2).sendKeys(addresstwo);
        ElementAction.selectFromDropDownList(COUNTRY,country1);
    }
    public void enterNewUserAddressAndMobileNumber(String state1, String city1, String zipcode1, String mobileno1)
    {
        driver.findElement(STATE).sendKeys(state1);
        driver.findElement(CITY).sendKeys(city1);
        driver.findElement(ZIPCODE).sendKeys(zipcode1);
        driver.findElement(MOBILENUMBER).sendKeys(mobileno1);
    }
    public void clickOnCreateUserBtn(){
        ElementAction.clickBtn(CREATEACCOUNT);
    }
    public String getTextAccountCreated(){
        return driver.findElement(ACCOUNTCREATED).getText();
    }

    public void clickOnContinue(){
        ElementAction.clickBtn(CONTINUEAFTERREGISTERbtn);
    }
    public String getTextLoggedInAsUserName()
    {
        ElementAction.insertwaitToBeVisible(LOGGEDINASUSER);
        return driver.findElement(LOGGEDINASUSER).getText();
    }
    public String getACCOUNTDELETEDText(){
        return driver.findElement(ACCOUNTDELETED).getText();
    }

    ///////////////////////////////// DELETE ACCOUNT FUNCTION ///////////////////
    public void delete_account()
    {
        ElementAction.insertwaitToBeVisible(DELETEACCOUNTtab);
        ElementAction.clickBtn(DELETEACCOUNTtab);
    }

    //////////////////////////////// SIGN_IN FUNCTIONS //////////////////////

    // ENTER EMAIL AND PASSWORD FOR REGISTERED USER //
    public void enterRegisteredEmail(String userName1)
    {
        driver.findElement(REGEMAIL).sendKeys(userName1);
    }
    public void enterRegisteredPassword(String passWord1)
    {
        driver.findElement(REGPASSWORD).sendKeys(passWord1);
    }

    public String getTextLoginToYourAccount()
    {
        return driver.findElement(LOGINTOYOURACCOUNT).getText();
    }
    public void clickLogInBtn()
    {
        ElementAction.insertwaitToBeVisible(LOGINbtn);
        ElementAction.clickBtn(LOGINbtn);
    }
    public void clickOnLogOut()
    {
        ElementAction.insertwaitToBeVisible(LOGOUTtab);
        ElementAction.clickBtn(LOGOUTtab);
    }
    public String getTestErrorMessage()
    {
        return driver.findElement(ERRORMSG).getText();
    }
    public void deleteAccountAfterLogIn()
    {
        navigateToSignInUp();
        enterRegisteredEmail("ayhaga@n.com");
        enterRegisteredPassword("123456");
        clickLogInBtn();
        delete_account();
    }
    //// REGISTER AN ACCOUNT TO USE IT AS DATABASE ATTRIBUTE IN LOG IN TESTS
    public void getRegisteredUser()
    {
        navigateToSignInUp();
        entername("Esraa");
        enterEmail("ayhaga@n.com");
        clickOnSignUpBtn();
        enterNewUserTitle();
        enterPassword("123456");
        enterNewUserDateOfBirth("1","March","1995");
        enterNewUserCheckNewsLetterOrSpecialOffer();
        enterNewUserNameAndCompany("Esraa","Khalaf","Vodafone");
        enterNewUserAddress("canada","canada","Canada");
        enterNewUserAddressAndMobileNumber("state","city","11885","015669855");
        clickOnCreateUserBtn();
        clickOnContinue();
        clickOnLogOut();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
}
