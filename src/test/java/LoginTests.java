import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void successfulLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        landingPage.login("linkedin.tst.yanina@gmail.com", "Test123!");

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page did not load after Login.");
    }

    @Test
    public void negativeLoginReturnedToLandingTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        landingPage.login("a@b.c", "");
        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");
    }

    @Test
    public void negativeLoginReturnedToLoginSubmitTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

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
