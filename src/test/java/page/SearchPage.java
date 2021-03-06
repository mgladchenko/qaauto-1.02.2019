package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {

    @FindBy(xpath = "//h3[contains(@class, 'search-results__total')]")
    private WebElement searchResultTotal;

    @FindBy(xpath = "//li[contains(@class, 'search-result ')]")
    private List<WebElement> searchResultElements;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementIsClickable(searchResultTotal);
    }

    public boolean isPageLoaded() {
        return searchResultTotal.isDisplayed()
                && driver.getCurrentUrl().contains("/search/results")
                && driver.getTitle().contains("| Search | LinkedIn");
    }

    public int getSearchResultCount() {
        return searchResultElements.size();
    }

    public List<String> getSearchResultsList() {
        List<String> searchResultStringsList = new ArrayList<String>();
        for (WebElement searchResultElement : searchResultElements) {
            ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView(true);", searchResultElement);
            String searchResultString = searchResultElement.getText();
            searchResultStringsList.add(searchResultString);
        }
        return searchResultStringsList;
    }
}
