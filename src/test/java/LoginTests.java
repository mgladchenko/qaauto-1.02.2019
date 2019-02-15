import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void negativeLoginTest() {
        String expectedUrl = "https://www.linkedin.com/";

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");

        LandingPage landingPage = new LandingPage(driver);
        landingPage.login("a@b.c", "");

/*

        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl,
                "Login page URL is wrong.");

        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(),
                "SignIn button is not displayed on Login page.");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));

        userEmailField.sendKeys("a@b.c");
        userPasswordField.sendKeys("");
        signInButton.click();

        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl,
                "Login page URL is wrong.");

        Assert.assertTrue(signInButton.isDisplayed(),
                "SignIn button is not displayed on Login page.");*/
    }

    @Test
    public void successfulLoginTest() {
        String expectedUrl = "https://www.linkedin.com/";

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up",
                "Login page title is wrong.");

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl,
                "Login page URL is wrong.");

        WebElement signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));

        Assert.assertTrue(signInButton.isDisplayed(),
                "SignIn button is not displayed on Login page.");

        WebElement userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));

        userEmailField.sendKeys("linkedin.tst.yanina@gmail.com");
        userPasswordField.sendKeys("Test123!");
        signInButton.click();

        WebElement profileNavMenuItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        Assert.assertTrue(profileNavMenuItem.isDisplayed(),
                "Home page did not load after Login.");


    }

}
