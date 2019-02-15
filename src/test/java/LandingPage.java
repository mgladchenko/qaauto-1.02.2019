import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

    WebDriver driver;

    WebElement signInButton;
    WebElement userEmailField;
    WebElement userPasswordField;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        signInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
        userEmailField = driver.findElement(By.xpath("//input[@id='login-email']"));
        userPasswordField = driver.findElement(By.xpath("//input[@id='login-password']"));
    }

    public void login(String userEmail, String userPassword) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPassword);
        signInButton.click();
    }

}
