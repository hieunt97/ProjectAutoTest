package hieu.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonService {
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;
    private final int timeoutWaitForPageLoaded = 20;

    public CommonService(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 5);
        js = (JavascriptExecutor) driver;
    }

    public boolean veryfiPageTitle(String pageTitle) {
        String title = driver.getTitle();
        return title.contains(pageTitle);
    }

    public void setText(By element, String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
//        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void clickElemnt(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
    }

    public void selectOptionByText(By element, String text) {
        //Convert BY to WebElemnt
        Select select = new Select(driver.findElement(element));

        select.selectByVisibleText(text);
    }

    public void selectOptionByValue(By element, String value) {
        //Convert BY to WebElemnt
        Select select = new Select(driver.findElement(element));

        select.selectByVisibleText(value);
    }

    public void waitForPageLoad() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(expectation);
        } catch (Exception e) {
            Assert.fail("Timeout waiting for Page Load request");
        }
    }

    public void setTextValue(WebElement element, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.clear();
        element.sendKeys(value);
    }

    public void clickSubmit(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void clickToElemnt(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void scrollDown(By element) {
        js.executeScript("arguments[0].scrollIntoView(true);", findWebElement(element));
    }
    public WebElement findWebElement(By by) {
        return driver.findElement(by);
    }
}
