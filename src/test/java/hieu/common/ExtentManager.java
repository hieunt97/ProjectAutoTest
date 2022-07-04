package hieu.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ExtentManager {

    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public static synchronized ExtentReports initReport() {
        extentReports = new ExtentReports();
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") +
                "/ExtentReports/" + "Report" + date + ".html");
        spark.config().setDocumentTitle("Project Auto Test");
        spark.config().setReportName("All regression test");

        extentReports.attachReporter(spark);
        spark.config().setTheme(Theme.STANDARD);
        extentReports.setSystemInfo("Framework Name", "Selenium Java Framework");
        extentReports.setSystemInfo("Author", "N.T.Hieu");
        return extentReports;
    }

    public static void setResult(WebDriver driver, ITestResult result) throws IOException {
        ExtentTest test = extentReports.createTest(result.getName());
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "FAILED TEST CASE IS:" + "" + result.getName());

            String base64Screenshot =
                    "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
            test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "SKIPPED TEST CASE IS:" + "" + result.getName());

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "PASSED TEST CASE IS:" + "" + result.getName());
        }
        extentReports.flush();
    }

}
