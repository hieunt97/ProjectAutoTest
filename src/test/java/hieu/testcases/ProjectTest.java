package hieu.testcases;

import hieu.common.BaseSetup;
import hieu.common.CommonService;
import hieu.page.AddProductDialog;
import hieu.page.DashboardPage;
import hieu.page.ProjectPage;
import hieu.page.SignInPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProjectTest extends BaseSetup {

    private WebDriver driver;
    private SignInPage signInPage;
    private DashboardPage dashboardPage;
    private ProjectPage projectPage;
    private AddProductDialog addProductDialog;
    private CommonService commonService;

    @BeforeClass
    public void setupBrowser(){
        driver = getDriver();
    }

    @Test(priority = 1)
    public void signIn() throws InterruptedException {
        log.info("login test Start");
        commonService = new CommonService(driver);
        signInPage = new SignInPage(driver);
        dashboardPage = signInPage.signIn("admin@mailinator.com", "123456");
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void openProjectPage() throws InterruptedException {
        commonService.waitForPageLoad();
        projectPage = dashboardPage.openProjectsPage();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void openAddProjectPage() throws InterruptedException {

        addProductDialog = projectPage.addProjects();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void addProject() throws InterruptedException {
        addProductDialog.saveProduct();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void searchProject() throws InterruptedException {
        projectPage.enterSearchValue("4,000");
        Thread.sleep(1000);
        projectPage.checkSearchTableByColumn(4, "4,000");
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }

}
