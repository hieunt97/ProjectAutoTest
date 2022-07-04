package hieu.common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String takeScreenshot(WebDriver driver, String fileName) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/Screenshots/" + fileName + "_" + date + ".png";
        FileUtils.copyFile(srcFile, new File(path));
        return path;
    }

}
