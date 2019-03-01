package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchPage;

import java.util.List;

public class SearchTests extends BaseTest {

    /**
     * Verify basic search functionality.
     *
     * Scenario:
     * - Open new Browser
     * - Navigate to http://www.linkedin.com
     * - Verify that Landing page is loaded
     * - Login with valid credentials
     * - Verify that Home page is loaded
     * - Search for "HR" search term
     * - Verify that Search page is loaded
     * - Verify that numbers of search results is 10
     * - Verify that each search result contains search term
     */
    @Test
    public void basicSearchTest() {
        String userEmail = "linkedin.tst.yanina@gmail.com";
        String userPassword = "Test123!";
        String searchTerm = "HR";

        Assert.assertTrue(landingPage.isPageLoaded(),
                "Landing page is not loaded.");

        HomePage homePage = landingPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page did not load after Login.");

        SearchPage searchPage = homePage.search(searchTerm);
        Assert.assertTrue(searchPage.isPageLoaded(),
                "Search page did not load.");

        Assert.assertEquals(searchPage.getSearchResultCount(), 10,
                "Search results count is wrong.");

        List<String> searchResultsList = searchPage.getSearchResultsList();

        for (String searchResult: searchResultsList) {
            Assert.assertTrue(searchResult.contains(searchTerm),
                    "SearchTerm "+searchTerm+" not found in:\n"+searchResult);
        }

    }

}
