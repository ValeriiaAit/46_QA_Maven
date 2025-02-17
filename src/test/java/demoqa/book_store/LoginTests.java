package demoqa.book_store;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.LoginPage;
import demoqa.pages.SidePage;
import demoqa.utils.RetryAnalyzer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    //lera2024@gm
    //Valeriia24@

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver).getBookStore().hideAds();
        new SidePage(app.driver).selectLogin().hideAds();
    }

//    @Test(invocationCount = 10)
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginPositiveTest() {
        new LoginPage(app.driver)
                .enterPersonalData("lera2024@gm","Valeriia24@")
                .clickOnLoginButton()
                .verifyUserName("lera2024@gm");

    }
}
