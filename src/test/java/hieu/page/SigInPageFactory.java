package hieu.page;

import hieu.common.CommonService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SigInPageFactory {

    private WebDriver driver;
    private CommonService commonService;

    @FindBy(xpath = "//a[normalize-space()='Forgot Username/Password?']")
    private WebElement headerPageText;

    @FindBy(id = "EmailInputEmail")
    private WebElement emailInput;

    @FindBy(id = "PasswordInputPassword")
    private WebElement passwordInput;

    @FindBy(id = "SignInButton")
    private WebElement signinBtn;

    @FindBy(id = "signInError")
    private WebElement errorMsgText;

    @FindBy(id = "Pin")
    private WebElement pinInput;

    @FindBy(id = "RequestPinForm_SubmitButton")
    private WebElement submitBtn;

    @FindBy(id = "RequestPinForm_Back")
    private WebElement backBtn;

    @FindBy(id = "RequestPinForm_ResetPin")
    private WebElement resetPintBtn;

    //Create initElemnt from PageFactory
    public SigInPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonService = new CommonService(driver);
    }

    public DashboardPage sigin(String username, String password, String pin) throws Exception {
        commonService.setTextValue(emailInput, username);
        commonService.setTextValue(passwordInput, password);
        commonService.clickSubmit(signinBtn);
        commonService.setTextValue(pinInput, pin);
        commonService.waitForPageLoad();
        return new DashboardPage(driver);
    }

    public boolean verifySignInPageTitle() {
        String expectedTitle = "Sign In";
        return driver.getTitle().equals(expectedTitle);
    }

    public boolean verifySignInPageText() {
        String pageText = headerPageText.getText();
        String expectedPageText = "Forgot Username/Password?";
        return pageText.contains(expectedPageText);
    }



}
