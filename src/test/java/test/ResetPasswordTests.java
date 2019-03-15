package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.RequestPasswordResetPage;

public class ResetPasswordTests extends BaseTest {

    @Test
    public void successfulPasswordResetTest() {
        String userEmail = "linkedin.tst.yanina@gmail.com";

        RequestPasswordResetPage requestPasswordResetPage =
                landingPage.clickOnForgotPasswordLink();
        Assert.assertTrue(requestPasswordResetPage.isLoaded(),
                "RequestPasswordReset page is not loaded.");

        requestPasswordResetPage.findAccount(userEmail);


    }


}
