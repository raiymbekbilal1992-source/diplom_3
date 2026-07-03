package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver driver;

    @Before
    @Step("Открыть браузер")
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://qa-stellarburgers.education-services.ru/");
    }

    @After
    @Step("Закрыть браузер")
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}