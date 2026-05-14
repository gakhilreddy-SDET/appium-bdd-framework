package com.tui.utils;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {
    public static void captureScreenshot(AppiumDriver driver, String fileName) {
        if (driver == null) {
            System.out.println("Driver is null, cannot capture screenshot.");
            return;
        }
        File srcFile = driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File("src/test/resources/reports/screenshots/" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}