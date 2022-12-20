package Elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementAction {
    static WebDriver driver;
    static WebDriverWait webDriverWait;
    static Select wantedChoice;
    static Actions action;

    public ElementAction(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        action = new Actions(this.driver);
    }

    public static void clickBtn(By locator){
        webDriverWait.until(ExpectedConditions.elementToBeClickable(locator));
        driver.findElement(locator).click();
    }
    public static void checkboxClick(By element){
        WebElement webelement = driver.findElement(element);
        webDriverWait.until(ExpectedConditions.visibilityOf(webelement));
        webelement.click();
    }

    public static void insertwaitToBeVisible(By element){
        WebElement welement = driver.findElement(element);
        webDriverWait.until(ExpectedConditions.visibilityOf(welement));
    }
    public static void clearTxtField(By fieldName){
        WebElement field = driver.findElement(fieldName);
        webDriverWait.until(ExpectedConditions.visibilityOf(field));
        field.clear();
    }

    public static void selectFromDropDownList(By selectionLocator, String selectedItem){
        WebElement field1 = driver.findElement(selectionLocator);
        wantedChoice = new Select(field1);
        webDriverWait.until(ExpectedConditions.visibilityOf(field1));
        wantedChoice.selectByVisibleText(selectedItem);
    }
    public static void mouseOverOnItem(By item, By add){
        WebElement webElement = driver.findElement(item);
        action.moveToElement(webElement).moveToElement(driver.findElement(add)).click().build().perform();
    }
    public static void scrollDown(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
    }
}
