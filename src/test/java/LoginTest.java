
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import pages.LoginPage;
import utils.*;

@Listeners({io.qameta.allure.testng.AllureTestNg.class, utils.TestngListener.class})
public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @Test
    @Description("Verify user cannot login with invalid credentials")
    @Story("Negative Login")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC_Login_002")
    @Issue("BUG-LOGIN-INVALID")
    @Epic("User Authentication")
    @Feature("Login")
    public void verifyInvalidLogin() {
        loginPage.navigateToLandingPage()
                .login("wrong_user", "wrong_pass");

        String errorMsg = loginPage.getLoginErrorMessage();

        Validations.assertThat()
                .object(errorMsg)
                .contains("Username and password do not match")
                .withCustomReportMessage("Verify error message on invalid login")
                .perform();
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        driver = BrowserFactory.getBrowser(
                BrowserFactory.BrowserType.fromString(browser),
                BrowserFactory.ExecutionType.LOCAL
        );
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            new BrowserActions(driver).closeAllOpenedBrowserWindows();
        }
    }

}
