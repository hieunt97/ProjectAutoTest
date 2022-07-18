package hieu.page;

import hieu.common.CommonService;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ProjectPage {

    private WebDriver driver;
    private CommonService commonService;


    public ProjectPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        commonService = new CommonService(driver);
    }

    private String pageText = "Projects";

    private String url = "/projects/all_projects";

    private By headerPageText = By.tagName("h1");

    //    private By addProjectBtn = By.xpath("//a[@title='Add project']");
    @FindBy(xpath = "//a[@title='Add project']")
    private WebElement addProjectBtn;

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchInput;

    public boolean verifyUrl() {
        return driver.getCurrentUrl().contains(url);
    }

    public boolean verifyElementText() {
        return driver.findElement(headerPageText).getText().equals(pageText);
    }

    public AddProductDialog addProjects() {
        commonService.waitForPageLoad();
        commonService.clickToElemnt(addProjectBtn);
        return new AddProductDialog(driver);
    }

    public void enterSearchValue(String value) {
        commonService.setTextValue(searchInput, value);
    }

    public void checkSearchTableByColumn(int colum, String value) {
        List<WebElement> row = driver.findElements(By.xpath("//table//tbody/tr"));
        int rowTotal = row.size(); //Lay so dong
        System.out.println("Search Result: " + rowTotal);

        //Duyệt từng row
        for (int i = 1; i <= rowTotal; i++) {
            WebElement elementCheck = driver.findElement(By.xpath("//table//tbody/tr[" + i + "]/td[" + colum + "]"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", elementCheck);

            System.out.println("Row" + i + ":" + elementCheck.getText().toUpperCase().contains(value.toUpperCase()) + " - " + elementCheck.getText());
            Assert.assertTrue(elementCheck.getText().toUpperCase().contains(value.toUpperCase()), "Row " + i + " Not contains search value");
        }
    }

}
