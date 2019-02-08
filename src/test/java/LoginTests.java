import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests {

    @Test
    public void negativeLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.linkedin.com/");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign UpX",
                "Login page title is wrong.");
    }

}
