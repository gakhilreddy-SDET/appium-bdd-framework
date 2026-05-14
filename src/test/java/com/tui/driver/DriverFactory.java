package com.tui.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.tui.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.net.URL;
import java.net.MalformedURLException;

public class DriverFactory {
    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private static final Logger logger = LogManager.getLogger(DriverFactory.class);

    public static AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver driverInstance) {
        driver.set(driverInstance);
    }

    public static void initializeDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        String execution = ConfigReader.getProperty("execution");
        String platform = ConfigReader.getProperty("platform").toLowerCase();

        if ("local".equals(execution)) {
            String deviceName;
            String platformVersion;
            String appPath;
            String automationName;
            String platformName;

            if ("android".equals(platform)) {
                deviceName = ConfigReader.getProperty("deviceName");
                platformVersion = ConfigReader.getProperty("platformVersion");
                appPath = ConfigReader.getProperty("appPath");
                String appPackage = ConfigReader.getProperty("appPackage");
                String appActivity = ConfigReader.getProperty("appActivity");
                automationName = "UiAutomator2";
                platformName = "Android";

                caps.setCapability("appPackage", appPackage);
                caps.setCapability("appActivity", appActivity);
                caps.setCapability("autoGrantPermissions", true);
            } else if ("ios".equals(platform)) {
                deviceName = ConfigReader.getProperty("ios.deviceName");
                platformVersion = ConfigReader.getProperty("ios.platformVersion");
                appPath = ConfigReader.getProperty("ios.appPath");
                String bundleId = ConfigReader.getProperty("ios.bundleId");
                automationName = "XCUITest";
                platformName = "iOS";

                caps.setCapability("bundleId", bundleId);
                caps.setCapability("autoAcceptAlerts", true);
            } else {
                throw new RuntimeException("Unsupported platform: " + platform);
            }

            logger.info("Initializing {} Driver with local execution", platformName);
            logger.info("Device Name: " + deviceName);
            logger.info("Platform Version: " + platformVersion);
            logger.info("App Path: " + appPath);

            caps.setCapability("platformName", platformName);
            caps.setCapability("deviceName", deviceName);
            caps.setCapability("platformVersion", platformVersion);
            caps.setCapability("app", appPath);
            caps.setCapability("automationName", automationName);

            try {
                logger.info("Attempting to connect to Appium server at http://127.0.0.1:4723");

                AppiumDriver appiumDriver;
                if ("android".equals(platform)) {
                    appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
                } else {
                    appiumDriver = new IOSDriver(new URL("http://127.0.0.1:4723"), caps);
                }

                setDriver(appiumDriver);

                logger.info("{} Driver initialized successfully", platformName);

            } catch (MalformedURLException e) {

                logger.error("Malformed URL for Appium server", e);

                throw new RuntimeException("Failed to initialize driver: Invalid URL", e);

            } catch (Exception e) {

                logger.error("Error initializing Appium driver. Ensure Appium server is running on http://127.0.0.1:4723", e);

                throw new RuntimeException("Failed to initialize driver: " + e.getMessage(), e);
            }
        } else if ("browserstack".equals(execution)) {
            logger.info("Initializing Android Driver with BrowserStack execution");
            caps.setCapability("browserstack.user", ConfigReader.getProperty("browserstack.user"));
            caps.setCapability("browserstack.key", ConfigReader.getProperty("browserstack.key"));
            caps.setCapability("app", ConfigReader.getProperty("appUrl"));
            caps.setCapability("device", "Google Pixel 3");
            caps.setCapability("os_version", "9.0");
            caps.setCapability("project", "TUI Mobile Test");
            caps.setCapability("build", "TUI Build");
            caps.setCapability("name", "TUI Test");

            try {
                logger.info("Attempting to connect to BrowserStack at https://hub-cloud.browserstack.com/wd/hub");
                AppiumDriver appiumDriver = new AndroidDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), caps);
                setDriver(appiumDriver);
                logger.info("Android Driver initialized successfully on BrowserStack");
            } catch (MalformedURLException e) {
                logger.error("Malformed URL for BrowserStack server", e);
                throw new RuntimeException("Failed to initialize driver: Invalid URL", e);
            } catch (Exception e) {
                logger.error("Error initializing driver on BrowserStack", e);
                throw new RuntimeException("Failed to initialize driver: " + e.getMessage(), e);
            }
        }
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            try {
                getDriver().quit();
                logger.info("Driver quit successfully");
            } catch (Exception e) {
                logger.error("Error quitting driver", e);
            } finally {
                driver.remove();
            }
        }
    }
}