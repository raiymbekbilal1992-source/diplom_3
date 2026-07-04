package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    private final WebDriver driver;

    private final By logoutButton =
            By.cssSelector("#root > div > main > div > nav > ul > li:nth-child(3) > button");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку Выход")
    public void clickLogout() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(logoutButton));

        WebElement element = driver.findElement(logoutButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}