package demoqa.elements;

import demoqa.core.TestBase;
import demoqa.pages.ButtonsPage;
import demoqa.pages.HomePage;
import demoqa.pages.SidePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ButtonsTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver).getElements().hideAds();
        new SidePage(app.driver).selectButtons().hideAds();
    }

    @Test
    public void doubleClickButtonsTest(){
        new ButtonsPage(app.driver)
                .doubleClick()
                .verifydoubleClickMessage("You have done a double click");
    }

    @Test
    public void rightClickButtonsTest() throws InterruptedException {
        new ButtonsPage(app.driver)
                .rightClickButton()
                .verifyrightClickMessage("You have done a right click");
    }

    @Test(invocationCount = 2)
    public void dynamicClickButtonsTest() {
        new ButtonsPage(app.driver)
                .dynamicClickButton()
                .verifyDynamicClickMessage("You have done a dynamic click");
    }
}
