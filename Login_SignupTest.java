package Tests;


import Pages.Login_SignupPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Login_SignupTest {
    WebDriver driver;
    Login_SignupPage loginSignupObject;
    String URL = "http://automationexercise.com";




    @BeforeMethod
    public void setup()
    {
//        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        loginSignupObject = new Login_SignupPage(driver);
    }
    /////////////////////////////// SIGN_UP TESTE ////////////////////////////////////
    @Test
    public void verifyHomepageVisible()
    {
        Assert.assertEquals(driver.getCurrentUrl().toString(),"https://automationexercise.com/");
    }
    @Test
    public void verifyNewUserSignupVisible()
    {
        loginSignupObject.navigateToSignInUp();
        Assert.assertEquals(loginSignupObject.newuserSignup(),"New User Signup!");
    }
    @Test
    public void validateNavigationToRegistrationOrLogin()
    {
        loginSignupObject.navigateToSignInUp();
        Assert.assertEquals(driver.getCurrentUrl().toString(),"https://automationexercise.com/login");
    }
    @Test
    public void VerifyENTERACCOUNTINFORMATIONVisible()
    {
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.entername("bla");
        loginSignupObject.enterEmail("bla@bla.com");
        loginSignupObject.clickOnSignUpBtn();
        Assert.assertEquals(loginSignupObject.getTxt_ENTERACCOUNTINFORMATION(),"ENTER ACCOUNT INFORMATION");

    }
    @Test
    public void validateRegistrationNewUserValidDataCreated()
    {
        SoftAssert softAssert = new SoftAssert();
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.entername("Esraa");
        loginSignupObject.enterEmail("escomramn@n.com");
        loginSignupObject.clickOnSignUpBtn();
        driver.getCurrentUrl();
        loginSignupObject.enterNewUserTitle();
        loginSignupObject.enterPassword("123456");
        loginSignupObject.enterNewUserDateOfBirth("1","March","1995");
        loginSignupObject.enterNewUserCheckNewsLetterOrSpecialOffer();
        loginSignupObject.enterNewUserNameAndCompany("Esraa","Khalaf","Vodafone");
        loginSignupObject.enterNewUserAddress("canada","canada","Canada");
        loginSignupObject.enterNewUserAddressAndMobileNumber("state","city","11885","015669855");
        loginSignupObject.clickOnCreateUserBtn();
        softAssert.assertEquals(loginSignupObject.getTextAccountCreated(),"ACCOUNT CREATED!");
        loginSignupObject.clickOnContinue();
        loginSignupObject.delete_account();
        softAssert.assertAll();
    }

    @Test
    public void verifyLoggedInAsUserNameVisible()
    {
        SoftAssert softAssert;
        softAssert = new SoftAssert();
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.entername("Esraa");
        loginSignupObject.enterEmail("nn@n.com");
        loginSignupObject.clickOnSignUpBtn();
        driver.getCurrentUrl();
        loginSignupObject.enterNewUserTitle();
        loginSignupObject.enterPassword("123456");
        loginSignupObject.enterNewUserDateOfBirth("1","March","1995");
        loginSignupObject.enterNewUserCheckNewsLetterOrSpecialOffer();
        loginSignupObject.enterNewUserNameAndCompany("Esraa","Khalaf","Vodafone");
        loginSignupObject.enterNewUserAddress("canada","canada","Canada");
        loginSignupObject.enterNewUserAddressAndMobileNumber("state","city","11885","015669855");
        loginSignupObject.clickOnCreateUserBtn();
        loginSignupObject.clickOnContinue();
        softAssert.assertTrue(loginSignupObject.getTextLoggedInAsUserName().contains(" Logged in as "+"Esraa"));
        loginSignupObject.delete_account();
    }

    @Test
    public void verifyAccountDeletedSuccessfully()
    {
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.entername("Esraa");
        loginSignupObject.enterEmail("esramn@n.com");
        loginSignupObject.clickOnSignUpBtn();
        driver.getCurrentUrl();
        loginSignupObject.enterNewUserTitle();
        loginSignupObject.enterPassword("123456");
        loginSignupObject.enterNewUserDateOfBirth("1","March","1995");
        loginSignupObject.enterNewUserCheckNewsLetterOrSpecialOffer();
        loginSignupObject.enterNewUserNameAndCompany("Esraa","Khalaf","Vodafone");
        loginSignupObject.enterNewUserAddress("canada","canada","Canada");
        loginSignupObject.enterNewUserAddressAndMobileNumber("state","city","11885","015669855");
        loginSignupObject.clickOnCreateUserBtn();
        loginSignupObject.clickOnContinue();
        loginSignupObject.delete_account();
        Assert.assertEquals(loginSignupObject.getACCOUNTDELETEDText(),"ACCOUNT DELETED!");
    }
    ////////////////////////// LOG_IN TESTS ////////////////////////////////
    @Test
    public void VerifyLoginToYourAccountVisible()
    {
        loginSignupObject.navigateToSignInUp();
        Assert.assertEquals(loginSignupObject.getTextLoginToYourAccount(),"Login to your account");
    }
    @Test
    public void verifyTheFunctionalityOfLoginBtnByLoggedInAsUserVisible()
    {
        SoftAssert softAssert;
        softAssert = new SoftAssert();
// get a registered user from the function acting as a saved database
        loginSignupObject.getRegisteredUser();

        /////////////////////// SIGN_IN /////////////////////////////////
        driver.get(URL);
        //verify that user in the home page
        softAssert.assertEquals(driver.getCurrentUrl().toString(),"https://automationexercise.com/");
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.enterRegisteredEmail("ayhaga@n.com");
        loginSignupObject.enterRegisteredPassword("123456");
        loginSignupObject.clickLogInBtn();
        // ASSERT THAT AFTER LOGGING IN "Logged IN as username" IS VISIBLE
        softAssert.assertEquals(loginSignupObject.getTextLoggedInAsUserName(),"Logged in as "+"Esraa");
        softAssert.assertAll();
        loginSignupObject.delete_account();
        loginSignupObject.clickOnContinue();
    }
    @Test
    public void verifyAccountDeletedVisibleAfterDeleting()
    {
// get a registered user from the function acting as a saved database
        loginSignupObject.getRegisteredUser();
        /////////////////////// SIGN_IN /////////////////////////////////
        driver.get(URL);
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.enterRegisteredEmail("ayhaga@n.com");
        loginSignupObject.enterRegisteredPassword("123456");
        loginSignupObject.clickLogInBtn();
        loginSignupObject.delete_account();
        Assert.assertEquals(loginSignupObject.getACCOUNTDELETEDText(),"ACCOUNT DELETED!");
    }
    @Test
    public void validateWrongPasswordUsername()
    {
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.enterRegisteredEmail("j@l.com");
        loginSignupObject.enterRegisteredPassword("*****");
        loginSignupObject.clickLogInBtn();
        Assert.assertEquals(loginSignupObject.getTestErrorMessage(),"Your email or password is incorrect!");
    }
    @Test
    public void verifyNavigationToSignInAfterLoggingOut()
    {
        // get a registered user from the function acting as a saved database
        loginSignupObject.getRegisteredUser();
        /////////// LOG OUT TEST //////////
        driver.get(URL);
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.enterRegisteredEmail("ayhaga@n.com");
        loginSignupObject.enterRegisteredPassword("123456");
        loginSignupObject.clickLogInBtn();
        loginSignupObject.clickOnLogOut();
        Assert.assertEquals(driver.getCurrentUrl().toString(),"https://automationexercise.com/login");
        /////////////////////////////// delete account /////////////////////////////
        loginSignupObject.deleteAccountAfterLogIn();
    }
    @Test
    public void validateExistingUsername()
    {
        loginSignupObject.navigateToSignInUp();
        loginSignupObject.entername("Radwa");
        loginSignupObject.enterEmail("radwa@r.com");
        loginSignupObject.clickOnSignUpBtn();
        Assert.assertEquals(loginSignupObject.getTestErrorMessage(),"Email Address already exist!");
    }
    @AfterMethod
    public void closeTab()
    {
        driver.close();
    }
}
