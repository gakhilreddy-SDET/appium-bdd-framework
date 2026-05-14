package com.tui.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoginPage extends BasePage {

    @AndroidFindBy(id = "username_input_field")
    private WebElement usernameField;

    @AndroidFindBy(id = "password_input_field")
    private WebElement passwordField;

    @AndroidFindBy(id = "date_of_birth_field")
    private WebElement dobField;

    @AndroidFindBy(id = "date_of_birth_field_calendar_icon")
    private WebElement calendarIcon;

    @AndroidFindBy(id = "login_form_submit_button")
    private WebElement submitButton;

    // Date picker elements
    @AndroidFindBy(id = "date_of_birth_dialog")
    private WebElement datePickerDialog;

    @AndroidFindBy(accessibility = "Switch to selecting a year")
    private WebElement yearSelector;

    @AndroidFindBy(id = "date_of_birth_dialog_confirm_button")
    private WebElement confirmButton;

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void selectDOB(String dob) {
        // Parse the DOB string, e.g., "1998-06-01"
        LocalDate date = LocalDate.parse(dob, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int year = date.getYear();
        String month = date.getMonth().name().substring(0, 1).toUpperCase() + date.getMonth().name().substring(1).toLowerCase();
        int day = date.getDayOfMonth();

        // Click the calendar icon to open date picker
        click(calendarIcon);

        // Wait for dialog to appear
        waitForVisibility(datePickerDialog);

        // Click to switch to year selection
        click(yearSelector);

        // Scroll to and select the year
        selectYear(year);

        // Select the month
        selectMonth(month, year);

        // Select the day
        selectDay(day, month, year);

        // Click confirm
        click(confirmButton);
    }

    private void selectYear(int year) {
        By yearLocator = By.xpath("//android.widget.TextView[contains(@content-desc, 'Navigate to year " + year + "')]");
        WebElement yearElement = null;
        int maxScrolls = 10;
        for (int i = 0; i < maxScrolls; i++) {
            try {
                yearElement = driver.findElement(yearLocator);
                if (yearElement.isDisplayed()) {
                    break;
                }
            } catch (Exception e) {
                // not found
            }
            swipeUp();
        }
        if (yearElement != null) {
            click(yearElement);
        } else {
            throw new RuntimeException("Year " + year + " not found in date picker");
        }
    }

    private void selectMonth(String month, int year) {
        By monthLocator = By.xpath("//android.widget.TextView[@content-desc='" + month + " " + year + "']");
        WebElement monthElement = waitForElementToBeClickable(monthLocator);
        click(monthElement);
    }

    private void selectDay(int day, String month, int year) {
        By dayLocator = By.xpath("//android.widget.TextView[contains(@text, '" + month + " " + day + ", " + year + "')]");
        WebElement dayElement = waitForElementToBeClickable(dayLocator);
        click(dayElement);
    }

    public void clickSubmit() {
        click(submitButton);
    }
}