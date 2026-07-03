package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private final WebDriver driver;

    private final By loginLink =
            By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Перейти на страницу авторизации")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}