package hieu.testcases;

import hieu.common.BaseSetup;
import hieu.common.CommonService;
import hieu.common.ExtentManager;
import hieu.common.Utils;
import hieu.page.DashboardPage;
import hieu.page.SignInPage;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class SignInTest extends BaseSetup {

    private WebDriver driver;
    private CommonService commonService;
    private SignInPage signInPage;
    private DashboardPage dashboardPage;

    @BeforeClass
    public void setupBrowser() {
        driver = getDriver();
    }

    @BeforeMethod
    public void startTest(){
        ExtentManager.initReport();
    }

    @Test(priority = 1)
    public void signInEmptyEmailPassword() throws InterruptedException {
        commonService = new CommonService(driver);
        signInPage = new SignInPage(driver);
        dashboardPage = signInPage.signIn("", "");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void signInInvalidEmail() throws InterruptedException {
        commonService = new CommonService(driver);
        signInPage = new SignInPage(driver);
        dashboardPage = signInPage.signIn("admin02@mai", "123456");
        Thread.sleep(2000);
    }

    @Test(priority = 3, dataProvider = "")
    public void signInInvalidPassword() throws InterruptedException {
        commonService = new CommonService(driver);
        signInPage = new SignInPage(driver);
        dashboardPage = signInPage.signIn("admin02@mailinator.com", "1");
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void signIn() throws InterruptedException {
        commonService = new CommonService(driver);
        signInPage = new SignInPage(driver);
        dashboardPage = signInPage.signIn("admin@mailinator.com", "123456");
        Thread.sleep(2000);
    }

//----------- Testcase signIn continute.. ------------

    //    @AfterMethod
//    public void takeScreenshot(ITestResult result) throws InterruptedException, IOException {
//        Thread.sleep(1000);
//        if(result.getStatus() == ITestResult.FAILURE){
//            Utils.takeScreenshot(driver, result.getName());
//        }
//    }

    @AfterMethod
    public void setResult(ITestResult result) throws IOException {
        ExtentManager.setResult(driver, result);
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}