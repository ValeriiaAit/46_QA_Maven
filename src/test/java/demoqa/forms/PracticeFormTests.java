package demoqa.forms;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.PracticeFormPage;
import demoqa.pages.SidePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PracticeFormTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver).getForms().hideAds();
        new SidePage(app.driver).selectPracticeForm().hideAds();
    }

    @Test
    public void practiceFormTest() {
        new PracticeFormPage(app.driver)
                .enterPersonalData(
                        "Vale", "Havrylenko", "lera24@gmail.com", "1234567890")
                .selectGender("Female")
                .chooseDateAsString("04 May 1965")
//                .chooseDate("1", "January", "2001")
                .enterSubject(new String[]{"Maths", "English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:/Users/Valeriia/Pictures/photo_2023-09-11_15-31-32 (1).jpg")
                .enterCurrentAddress("Portishead, Bristol, UK")
                .enterState("NCR")
                .enterCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }

    @Test
    @Parameters({"firstName", "lastName", "email", "phone"})
    public void practiceFormParametersTest(String firstName, String lastName, String email, String phone) {
        new PracticeFormPage(app.driver)
                .enterPersonalData(
                        firstName, lastName, System.currentTimeMillis()+email, phone)
                .selectGender("Female")
                .chooseDateAsString("04 May 1965")
//                .chooseDate("1", "January", "2001")
                .enterSubject(new String[]{"Maths", "English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:/Users/Valeriia/Pictures/photo_2023-09-11_15-31-32 (1).jpg")
                .enterCurrentAddress("Portishead, Bristol, UK")
                .enterState("NCR")
                .enterCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }


}
