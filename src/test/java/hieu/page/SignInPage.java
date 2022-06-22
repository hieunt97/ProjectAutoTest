package hieu.page;

import hieu.common.CommonService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
    private WebDriver driver;
    private CommonService commonService;
    private WebDriverWait wait;

    @FindBy(id = "email" )
    private WebElement emailInPut;

    @FindBy(id = "password" )
    private WebElement passInPut;

    @FindBy(xpath = "//button[@type='submit']" )
    private WebElement siginBtn;


    public SignInPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
        commonService = new CommonService(this.driver);
    }

    public DashboardPage signIn(String email, String password) {
        commonService.setTextValue(emailInPut, email);
        commonService.setTextValue(passInPut, password);
        commonService.clickSubmit(siginBtn);
        return new DashboardPage(driver);
    }

}
