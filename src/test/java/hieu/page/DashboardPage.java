package hieu.page;

import hieu.common.CommonService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DashboardPage {
    private WebDriver driver;
    private CommonService commonService;

//    private By emailInPut = By.xpath("//input[@placeholder='Email']");
//    private By passInPut = By.xpath("//input[@placeholder='Password']");
//    private By loginBtn = By.id("login");
//    @FindBy(xpath = "//input[@placeholder='Email']")
//    private By emailInPut;
//    @FindBy(xpath = "//input[@placeholder='Password']")
//    private By passInPut;
//    @FindBy(id = "login")
//    private By loginBtn;
//    private By projectsMenu = By.xpath("//ul[@id='sidebar-menu']//span[contains(text(),'Projects')]");

    @FindBy(xpath = "//ul[@id='sidebar-menu']//span[contains(text(),'Projects')]")
    private WebElement projectsMenu;

    private String url = "/dashboard";

    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        commonService = new CommonService(driver);
    }

    public boolean verifyUrl(){
        return driver.getCurrentUrl().contains(url);
    }

    public ProjectPage openProjectsPage(){
        Assert.assertTrue(verifyUrl(), "Not page is Dashboard");
        commonService.clickSubmit(projectsMenu);
        return new ProjectPage(driver);
    }


}
