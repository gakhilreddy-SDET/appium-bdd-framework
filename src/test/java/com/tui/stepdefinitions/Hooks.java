package com.tui.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import com.tui.driver.DriverFactory;
import com.tui.utils.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger logger = LogManager.getLogger(Hooks.class);

    @Before
    public void setUp() {
        logger.info("Initializing driver");
        DriverFactory.initializeDriver();
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            ScreenshotUtils.captureScreenshot(DriverFactory.getDriver(), scenario.getName());
        }
        logger.info("Quitting driver");
        DriverFactory.quitDriver();
    }
}