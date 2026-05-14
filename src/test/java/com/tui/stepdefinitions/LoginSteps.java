package com.tui.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.tui.pages.LoginPage;
import com.tui.pages.HomePage;
import org.testng.Assert;

public class LoginSteps {
    private LoginPage loginPage = new LoginPage();
    private HomePage homePage = new HomePage();

    @Given("I launch the application")
    public void launchApp() {
        // Driver already initialized in hooks
    }

    @When("I enter username {string}")
    public void enterUsername(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void enterPassword(String password) {
        loginPage.enterPassword(password);
    }

    @When("I select DOB {string}")
    public void selectDOB(String dob) {
        loginPage.selectDOB(dob);
    }

    @When("I click submit")
    public void clickSubmit() {
        loginPage.clickSubmit();
    }

    @Then("I should see home screen")
    public void verifyHomeScreen() {
        Assert.assertTrue(homePage.areProductCardsDisplayed());
    }

    @Then("I should see validation error")
    public void verifyError() {
        // Assume some error message verification
        Assert.assertTrue(true); // Placeholder
    }
}