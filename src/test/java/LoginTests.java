import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests {
    WebDriver driver;

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validData() {
        return new Object[][]{
                { "linkedin.tst.yanina@gmail.com", "Test123!" },
                { "linkedin.TST.yanina@gmail.com", "Test123!" },
                { " linkedin.tst.yanina@gmail.com ", "Test123!" }
        };
    }

    @Test(dataProvider = "validData")
    public void successfulLoginTest(String userEmail, String userPassword) {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        landingPage.login(userEmail, userPassword);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page did not load after Login.");
    }

    @Test
    public void negativeLoginReturnedToLandingTest() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        landingPage.login("a@b.c", "");
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");
    }

    @Test
    public void negativeLoginReturnedToLoginSubmitTest() {
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        landingPage.login("linkedin.tst.yanina@gmail.com", "12345");
        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(driver);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(),
                "loginSubmitPage page is not loaded.");
        Assert.assertEquals(loginSubmitPage.getPasswordValidationMessageText(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "Wrong validation message for password field.");
    }

}
