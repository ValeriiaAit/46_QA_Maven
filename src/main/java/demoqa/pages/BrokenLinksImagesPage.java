package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BrokenLinksImagesPage extends BasePage {
    public BrokenLinksImagesPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "img")
    List<WebElement> allImages;

    public BrokenLinksImagesPage checkBrokenLinksImages() {
        System.out.println(allImages.size());
        for (WebElement image : allImages){
           String imageUrl = image.getAttribute("src");
            if (imageUrl != null) {
                verifyLink(imageUrl);
            }
            boolean isDisplayed = (Boolean) js.executeScript("return arguments[0].naturalWidth > 0", image);
            System.out.println("Image: [" + imageUrl + (isDisplayed ? " is displayed! " : " is NOT displayed! "));
        }
        return this;
    }

}
