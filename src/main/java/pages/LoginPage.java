package pages;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BrowserActions;
import utils.ExtentReport;
import utils.PropertiesReader;
import utils.LoggerClass;


import static utils.ElementActions.click;
import static utils.ElementActions.type;

public class LoginPage {
    private final WebDriver driver;

    private final String landingPageUrl = PropertiesReader.getProperty("websitename.baseurl");

    private final By userName = By.id( "user-name");

    private final By passwordField = By.id( "password");

    private final By loginButton = By.id( "login-button");

    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public LoginPage navigateToLandingPage() {
        BrowserActions browserActions = new BrowserActions(driver);
        browserActions.navigateToUrl(landingPageUrl);
        return this;

    }

    @Step("Login with Valid credentials ")
    public void login(String username, String password) {

        ExtentReport.info(MarkupHelper.createLabel("User Login", ExtentColor.BLUE));

        LoggerClass.logMessage("Login with username: " + username + " and password: " + password);
        type(driver,userName, username);
        type(driver,passwordField, password);
        click(driver,loginButton);
    }


    public String getLoginErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }


}
