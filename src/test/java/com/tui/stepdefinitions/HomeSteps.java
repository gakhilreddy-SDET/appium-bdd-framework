package com.tui.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import com.tui.pages.HomePage;
import org.testng.Assert;

public class HomeSteps {
    private HomePage homePage = new HomePage();

    @Given("I am on home screen")
    public void onHomeScreen() {
        // Assume already on home screen after login
    }

    @When("I click on Hotels tab")
    public void clickHotelsTab() {
        homePage.clickHotelsTab();
    }

    @Then("I should see hotel cards")
    public void verifyHotelCards() {
        Assert.assertTrue(homePage.areProductCardsDisplayed(), "Hotel cards should be displayed");
    }

    @When("I click on Holidays tab")
    public void clickHolidaysTab() {
        homePage.clickHolidaysTab();
    }

    @Then("I should see holiday cards")
    public void verifyHolidayCards() {
        Assert.assertTrue(homePage.areProductCardsDisplayed(), "Holiday cards should be displayed");
    }

    @When("I click on All tab")
    public void clickAllTab() {
        homePage.clickAllTab();
    }

    @Then("I should see all products")
    public void verifyAllProducts() {
        Assert.assertTrue(homePage.areProductCardsDisplayed(), "All product cards should be displayed");
    }

    @When("I scroll through cards")
    public void scrollCards() {
        homePage.scrollThroughCards();
    }

    @Then("I should see additional cards")
    public void verifyAdditionalCards() {
        Assert.assertTrue(homePage.areProductCardsDisplayed(), "Additional cards should be displayed after scrolling");
    }

    @Then("I should see product title")
    public void verifyTitle() {
        String title = homePage.getProductTitle();
        Assert.assertNotNull(title, "Product title should not be null");
        Assert.assertFalse(title.isEmpty(), "Product title should not be empty");
    }

    @Then("I should see location")
    public void verifyLocation() {
        String location = homePage.getLocation();
        Assert.assertNotNull(location, "Location should not be null");
        Assert.assertFalse(location.isEmpty(), "Location should not be empty");
    }

    @Then("I should see rating")
    public void verifyRating() {
        String rating = homePage.getRating();
        Assert.assertNotNull(rating, "Rating should not be null");
        Assert.assertFalse(rating.isEmpty(), "Rating should not be empty");
    }

    @Then("I should see price")
    public void verifyPrice() {
        String price = homePage.getPrice();
        Assert.assertNotNull(price, "Price should not be null");
        Assert.assertFalse(price.isEmpty(), "Price should not be empty");
    }

    @Then("I should see board type")
    public void verifyBoardType() {
        String boardType = homePage.getBoardType();
        Assert.assertNotNull(boardType, "Board type should not be null");
        Assert.assertFalse(boardType.isEmpty(), "Board type should not be empty");
    }

    @Then("product images should be displayed")
    public void verifyProductImages() {
        Assert.assertTrue(homePage.isProductImageDisplayed(), "Product images should be displayed");
    }

    @Then("rating images should be displayed")
    public void verifyRatingImages() {
        Assert.assertTrue(homePage.isRatingImageDisplayed(), "Rating images should be displayed");
    }

    @Then("I should see {int} or more product cards")
    public void verifyProductCardCount(int expectedCount) {
        int actualCount = homePage.getProductCardCount();
        Assert.assertTrue(actualCount >= expectedCount, "Expected at least " + expectedCount + " cards, but got " + actualCount);
    }

    @When("I click on the first product card")
    public void clickFirstProductCard() {
        homePage.clickFirstProductCard();
    }

    @Then("the first product card should be displayed")
    public void verifyFirstProductCardDisplayed() {
        Assert.assertNotNull(homePage.getFirstProductCard(), "First product card should be displayed");
    }
}