package demoqa.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void hideAds() {
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    protected void click(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    protected void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    protected void typeScrollWithJS(WebElement element, String text, int y) {
        if (text != null) {
//            clickWitJS(element, 0, y);
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    protected void clickWitJS(WebElement element, int x, int y) {
        //window.scrollBy(0,100)
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        //js.executeScript("window.scrollBy({},{})",x,y);
        click(element);
    }

    //можно использовать перед click
    protected void scrollTo(int y) {
        js.executeScript("window.scrollBy(" + 0 + "," + y + ")");
    }

    protected void scrollWithPageDown(int times, int delay) {
        try {
            Robot robot = new Robot();

            for (int i = 0; i < times; i++) {
                robot.keyPress(KeyEvent.VK_PAGE_DOWN);
                robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
                robot.delay(delay);
            }
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void shouldHaveText(WebElement element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        try {
            // Переменная будет хранить результат ожидания элемента
            boolean isTextPresent = wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            Assert.assertTrue(isTextPresent,
                    "Expected text: [" + text + "], actual text: [" + element.getText() + "] in element: [" + element + "]");
        } catch (TimeoutException e) {
            //throw new TimeoutException(e);
        }
    }

    public void verifyLink(String urlToCheck) {
        try {
            URL url = new URL(urlToCheck);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.connect();

            // Получение кода ответа
            int responseCode = connection.getResponseCode();
            // Получение заголовка ответа
            String responseMessage = connection.getResponseMessage();

            if (responseCode >= 400) {
                // broken link
                System.err.println("URL to check: [" + urlToCheck + "], " + "response code: [" + responseCode + "], " + "response message: [" + responseMessage + "] is broken link");
            } else {
                // correct link
                System.out.println("URL to check: [" + urlToCheck + "], " + "response code: [" + responseCode + "], " + "response message: [" + responseMessage + "] is valid link");
            }

        } catch (MalformedURLException error) {
            System.err.println("Error: Malformed URL: ["+urlToCheck+"], error message: [" + error.getMessage()+"]");
            //throw new RuntimeException(e);
        } catch (IOException error) {
            System.err.println("Error occurred: ["+error.getMessage()+"] for URL: [" + urlToCheck +"]");
            //throw new RuntimeException(e);
        }
    }
}
