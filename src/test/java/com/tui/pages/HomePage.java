package com.tui.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;

public class HomePage extends BasePage {

    // Tab elements
    @AndroidFindBy(id = "top_app_bar_all_tab")
    private WebElement allTab;

    @AndroidFindBy(id = "top_app_bar_hotels_tab")
    private WebElement hotelsTab;

    @AndroidFindBy(id = "top_app_bar_holidays_tab")
    private WebElement holidaysTab;

    // Product/Content card elements
    @AndroidFindBy(id = "content_card_root_0")
    private WebElement firstProductCard;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='content_card_root_0'] | //android.view.View[@resource-id='content_card_root_1']")
    private List<WebElement> productCards;

    @AndroidFindBy(id = "content_card_hotel_name_0")
    private WebElement productTitle;

    @AndroidFindBy(id = "content_card_destination_0")
    private WebElement location;

    @AndroidFindBy(id = "content_card_rating_0")
    private WebElement rating;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='content_card_price_button_0']//android.widget.TextView")
    private WebElement price;

    // Additional elements for comprehensive testing
    @AndroidFindBy(id = "content_card_hotel_image_0")
    private WebElement productImage;

    @AndroidFindBy(id = "content_card_rating_image_0")
    private WebElement ratingImage;

    @AndroidFindBy(id = "content_card_board_type_0")
    private WebElement boardType;

    public void clickAllTab() {
        click(allTab);
    }

    public void clickHotelsTab() {
        click(hotelsTab);
    }

    public void clickHolidaysTab() {
        click(holidaysTab);
    }

    public boolean areProductCardsDisplayed() {
        return !productCards.isEmpty();
    }

    public String getProductTitle() {
        return getText(productTitle);
    }

    public String getLocation() {
        return getText(location);
    }

    public String getRating() {
        return getText(rating);
    }

    public String getPrice() {
        return getText(price);
    }

    public String getBoardType() {
        return getText(boardType);
    }

    public void scrollThroughCards() {
        swipeUp();
    }

    public boolean isProductImageDisplayed() {
        try {
            waitForVisibility(productImage);
            return productImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isRatingImageDisplayed() {
        try {
            waitForVisibility(ratingImage);
            return ratingImage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getProductCardCount() {
        return productCards.size();
    }

    public WebElement getFirstProductCard() {
        return firstProductCard;
    }

    public void clickFirstProductCard() {
        click(firstProductCard);
    }
}