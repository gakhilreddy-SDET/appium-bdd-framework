package com.tui.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.tui.stepdefinitions"},
    plugin = {"pretty", "html:target/cucumber-reports", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
    monochrome = true,
    tags = "@smoke",
    name = "Valid Login"
)
public class TestRunner extends AbstractTestNGCucumberTests {
}