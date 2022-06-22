package hieu.testcases;

import hieu.common.BaseSetup;
import hieu.common.CommonService;
import hieu.page.DashboardPage;
import hieu.page.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class SignInTest extends BaseSetup {

    private WebDriver driver;
    private CommonService commonService;
    private SignInPage signInPage;
    private DashboardPage dashboardPage;

    @BeforeClass
    public void setupBrowser(){
        driver = getDriver();
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

    @Test(priority = 3)
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

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }

//    @AfterTest
//    public void takeScreenshot(ITestResult result) {
//        if (ITestResult.FAILURE == result.getStatus()) {
//            TakesScreenshot camera = (TakesScreenshot) driver;
//            File screenshot = camera.getScreenshotAs(OutputType.FILE);
//            try {
//                Files.move(screenshot, new File("resources/screenshots/"+ result.getName() + ".png"));
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//
//    }


}