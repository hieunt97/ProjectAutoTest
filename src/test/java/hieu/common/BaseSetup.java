package hieu.common;

import hieu.com.uitls.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BaseSetup {
    private WebDriver driver;
    public Logger log = Logger.getLogger("ProjectAuto");

    public WebDriver getDriver() {
        return driver;
    }

    //Option browser
    private void setDriver(String browserType, String webURL) {
        if ("chrome".equals(browserType)) {
            driver = initChromeDriver(webURL);
        } else if ("firefox".equals(browserType)) {
            driver = initFirefoxDriver(webURL);
        } else {
            System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
            driver = initChromeDriver(webURL);
        }
    }

    //set driver for browser
    private WebDriver initChromeDriver(String webURL) {
        System.out.println("Launching Chrome browser...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(webURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    private WebDriver initFirefoxDriver(String webURL) {
        System.out.println("Launching Firefox browser...");
//        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.navigate().to(webURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    // Create driver & browser
    @Parameters({"browserType", "webURL"})
    @BeforeClass
    public void initializeTestBaseSetup(String browserType, String webURL) {
        try {
            setDriver(browserType, webURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }


}
