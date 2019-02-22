import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests {
    WebDriver driver;
    LandingPage landingPage;

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        landingPage = new LandingPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validData() {
        return new Object[][]{
                { "linkedin.tst.yanina@gmail.com", "Test123!" },
               // { "linkedin.TST.yanina@gmail.com", "Test123!" },
               // { " linkedin.tst.yanina@gmail.com ", "Test123!" }
        };
    }

    //@Test(dataProvider = "validData")
    public void successfulLoginTest(String userEmail, String userPassword) {
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        HomePage homePage = landingPage.loginToHomePage(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page did not load after Login.");
    }

    @Test
    public void negativeLoginReturnedToLandingTest() {
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        landingPage.loginToLandingPage("a@b.c", "");
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");
    }

    @DataProvider
    public Object[][] invalidData() {
        return new Object[][]{
                { "linkedin.tst.yanina@gmail.com", "12345", "",  "Hmm, that's not the right password. Please try again or request a new one."},
        };
    }

    @Test(dataProvider = "invalidData")
    public void negativeLoginReturnedToLoginSubmitTest(String userEmail,
                                                       String userPassword,
                                                       String emailValidationMessage,
                                                       String passwordValidationMessage) {
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        LoginSubmitPage loginSubmitPage = landingPage.loginToLoginSubmitPage(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(),
                "loginSubmitPage page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getEmailValidationMessageText(),
                emailValidationMessage,
                "Wrong validation message for email field.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessageText(),
                passwordValidationMessage,
                "Wrong validation message for password field.");
    }

}
