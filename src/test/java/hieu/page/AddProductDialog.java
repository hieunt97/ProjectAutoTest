package hieu.page;

import hieu.common.CommonService;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AddProductDialog {

    private WebDriver driver;
    private Actions actions;
    private CommonService commonService;

    public AddProductDialog(WebDriver driver) {
        this.driver = driver;
        actions = new Actions(driver);
        commonService = new CommonService(driver);
    }

    private String pageText = "Add project";

    private String url = "/projects/all_projects";

    private By headerPageText = By.id("ajaxModalTitle");
    private By titleInput = By.id("title");
    private By clientDropdown = By.xpath("//label[@for='client_id']/following-sibling::div");
    private By searchClientInput = By.xpath("//div[@id='select2-drop']/div/input");
    private By desInput = By.id("description");
    private By startDateInput = By.id("start_date");
    private By deadLineInput = By.id("deadline");
    private By priceInput = By.id("price");
    private By labelInput = By.xpath("//div[@id='s2id_project_labels']//input");
    private By saveBtn = By.xpath("//button[@type='submit']");

    public void saveProduct() throws InterruptedException {
        Assert.assertTrue(verifyElementText(), "Not page is Add Project");
        commonService.setText(titleInput, "P01");
        Thread.sleep(1000);

        commonService.clickElemnt(clientDropdown);
        Thread.sleep(1000);

        commonService.setText(searchClientInput, "Công ty cổ phần BlueCo");
        Thread.sleep(1000);
        actions.sendKeys(Keys.ENTER).build().perform();

        commonService.setText(desInput, "description abc");
        Thread.sleep(1000);

        commonService.setText(startDateInput, "2022-08-25");
        Thread.sleep(1000);

        commonService.setText(deadLineInput, "2022-08-30");
        Thread.sleep(1000);

        commonService.setText(priceInput, "priceInput");
        Thread.sleep(1000);

        commonService.setText(labelInput, "Public");
        Thread.sleep(1000);
        actions.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(1000);

        driver.findElement(saveBtn).click();

//        driver.findElement(titleInput).sendKeys("P01");
//        Thread.sleep(1000);
//
//        driver.findElement(clientDropdown).click();
//        Thread.sleep(1000);
//        driver.findElement(searchClientInput).sendKeys("Công ty cổ phần BlueCo");
//        Thread.sleep(1000);
//        actions.sendKeys(Keys.ENTER).build().perform();
//        Thread.sleep(1000);
//
//        driver.findElement(desInput).sendKeys("description abc");
//        driver.findElement(startDateInput).sendKeys("2022-08-25");
//        driver.findElement(deadLineInput).sendKeys("2022-08-30");
//        driver.findElement(priceInput).sendKeys("4000");
//        Thread.sleep(1000);
//
//        driver.findElement(labelInput).sendKeys("Public");
//        Thread.sleep(1000);
//        actions.sendKeys(Keys.ENTER).build().perform();
//        Thread.sleep(1000);
//        driver.findElement(saveBtn).click();
    }

    public boolean verifyUrl() {
        return driver.getCurrentUrl().contains(url);
    }

    public boolean verifyElementText() {
        return driver.findElement(headerPageText).getText().equals(pageText);
    }


}
