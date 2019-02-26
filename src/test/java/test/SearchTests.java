package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.HomePage;
import page.SearchPage;

public class SearchTests extends BaseTest {

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


    }

}