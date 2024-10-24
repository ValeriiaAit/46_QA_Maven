package demoqa.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;

    protected void init() {
        String browser = System.getProperty("browser", "chrome");
        // Проверяет значение переменной browser и в зависимости от результата инициализирует нужный драйвер
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default: // Это резервный сценарий на случай, если значение browser не совпадает ни с одним из указанных случаев
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
        //driver = new ChromeDriver();
//        driver.manage().window().setPosition(new Point(2500, 0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // неявное
        driver.get("https://demoqa.com/");
    }

    protected void stop() {
        if (driver != null) {
            driver.quit(); // Остановка драйвера после всех тестов
        }
    }
}