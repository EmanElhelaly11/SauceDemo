package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.LoggerClass;


import static utils.ElementActions.*;

public class CartPage {

    private WebDriver driver;

    private By checkout = By.id("checkout");
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public CartPage checkout(String firstName, String lastName, String postalCode) {
        LoggerClass.logStep("🛒 Starting checkout process");

        // Wait and click checkout safely
        LoggerClass.logStep("🔍 Waiting and clicking on [checkout] button");
        click(driver, checkout);

        LoggerClass.logStep("✍️ Filling in checkout information");
        type(driver, this.firstName, firstName);
        type(driver, this.lastName, lastName);
        type(driver, this.postalCode, postalCode);

        LoggerClass.logStep("👉 Clicking on [continue] button");
        click(driver, continueButton);

        LoggerClass.logStep("✅ Clicking on [finish] button to complete checkout");
        click(driver, finishButton);

        LoggerClass.logStep("🎉 Checkout completed successfully");

        return this;
    }
}
