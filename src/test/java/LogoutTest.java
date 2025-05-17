import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import pages.HomePage;
import pages.LoginPage;
import utils.*;

@Listeners({io.qameta.allure.testng.AllureTestNg.class, utils.TestngListener.class})
public class LogoutTest {

    private WebDriver driver;
    private JsonFileManager testData;
    private LoginPage loginPage;
    private HomePage homePage;

    //////////////////////////////////////////////////////
    /////////////////// Test Case ////////////////////////

    @Test
    @Description("Login with valid user and perform logout")
    @Story("Logout Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC_Logout_001")
    @Issue("LOGOUT-BUG")
    @Epic("User Management")
    @Feature("Logout")
    public void userShouldBeAbleToLogoutSuccessfully() {

        // Step 1: Login
        loginPage.navigateToLandingPage()
                .login(testData.getTestData("username"), testData.getTestData("password"));

        // Step 2: Logout
        homePage.logout();

        // Step 3: Assertion
        String currentUrl = driver.getCurrentUrl();
        Validations.assertThat()
                .object(currentUrl)
                .contains("saucedemo.com")
                .withCustomReportMessage("Verify user is redirected to login page after logout")
                .perform();
    }

    //////////////////////////////////////////////////////
    ///////////////// Configurations /////////////////////

    @BeforeClass
    public void classSetup() {
        testData = new JsonFileManager("src/test/resources/TestData/SauceDemoTestData.json");
    }

    @BeforeMethod
    @Parameters("browser")
    public void methodSetup(@Optional("chrome") String browser) {
        BrowserFactory.BrowserType browserType = BrowserFactory.BrowserType.fromString(browser);
        BrowserFactory.ExecutionType executionType = BrowserFactory.ExecutionType.LOCAL;
        driver = BrowserFactory.getBrowser(browserType, executionType);

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            BrowserActions browserActions = new BrowserActions(driver);
            browserActions.closeAllOpenedBrowserWindows();
        }
    }
}
