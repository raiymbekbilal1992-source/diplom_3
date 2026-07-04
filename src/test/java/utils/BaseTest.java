package utils;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;

    @Before
    @Step("Открыть браузер")
    public void setUp() {

        driver = DriverFactory.getDriver();

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