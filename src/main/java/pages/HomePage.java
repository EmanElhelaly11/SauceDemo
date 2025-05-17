package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utils.ElementActions.click;

public class HomePage {
    private final WebDriver driver;

    private final By firstProduct = By.id("add-to-cart-sauce-labs-backpack");
    private final By secondProduct = By.id("add-to-cart-sauce-labs-bike-light");
    private final By shoppingCart = By.id("shopping_cart_container");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /// ///////////////////////////////////////////////////////////////////////
    /// ///////////////////////////// Actions ////////////////////////////////




    @Step("Add Items to Cart ")
    public void addProductsToCart() {

        click(driver, firstProduct);
        click(driver, secondProduct);
        click(driver, shoppingCart);
    }

    public void logout() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link"))).click();
    }



}