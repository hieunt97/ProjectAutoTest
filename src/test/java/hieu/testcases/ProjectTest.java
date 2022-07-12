package hieu.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.google.common.io.Files;
import hieu.common.BaseSetup;
import hieu.common.CommonService;
import hieu.common.ExtentManager;
import hieu.common.Utils;
import hieu.data.DataTest;
import hieu.page.AddProductDialog;
import hieu.page.DashboardPage;
import hieu.page.ProjectPage;
import hieu.page.SignInPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectTest extends BaseSetup {

    private WebDriver driver;
    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private ProjectPage projectPage;
    private AddProductDialog addProductDialog;
    private CommonService commonService;
    private Utils utils;
    ExtentTest extentTest;
    ExtentReports extentReports;

    @BeforeClass
    public void setupBrowser() {
        driver = getDriver();
        commonService = new CommonService(driver);
        ExtentManager.initReport();
    }

    @Test(priority = 1, dataProvider = "data-login", dataProviderClass = DataTest.class)
    public void signIn(String username, String password) throws InterruptedException, IOException {
        log.info("login test Start");
        signInPage = new SignInPage(driver);
//        dashboardPage = signInPage.signIn("admin@mailinator.com", "123456");
        dashboardPage = signInPage.signIn(username, password);
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void openProjectPage() throws InterruptedException {
        commonService.waitForPageLoad();
        projectPage = dashboardPage.openProjectsPage();
        Thread.sleep(1000);
    }

    @Test(priority = 3)
    public void openAddProjectPage() throws InterruptedException {

        addProductDialog = projectPage.addProjects();
        Thread.sleep(1000);
    }

    @Test(priority = 4)
    public void addProject() throws InterruptedException {
        addProductDialog.saveProduct();
        Thread.sleep(1000);
    }

    @Test(priority = 5)
    public void searchProject() throws InterruptedException {
        projectPage.enterSearchValue("P01");
        Thread.sleep(1000);
        projectPage.checkSearchTableByColumn(2, "P01");
    }

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
        Thread.sleep(1000);
        driver.quit();
    }


}
