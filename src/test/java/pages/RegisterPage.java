package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;

    private final By nameField =
            By.cssSelector("#root > div > main > div > form > fieldset:nth-child(1) > div > div > input");

    private final By emailField =
            By.cssSelector("#root > div > main > div > form > fieldset:nth-child(2) > div > div > input");

    private final By passwordField =
            By.cssSelector("#root > div > main > div > form > fieldset:nth-child(3) > div > div > input");

    private final By registerButton =
            By.cssSelector("#root > div > main > div > form > button");

    private final By errorText =
            By.xpath("//*[contains(text(),'Некорректный пароль')]");

    private final By loginLink =
            By.xpath("//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Зарегистрировать пользователя")
    public void registerUser(String name,
                             String email,
                             String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement nameInput =
                wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));

        WebElement emailInput =
                wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));

        WebElement passwordInput =
                wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));

        nameInput.clear();
        nameInput.sendKeys(name);

        emailInput.clear();
        emailInput.sendKeys(email);

        passwordInput.clear();
        passwordInput.sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(registerButton))
                .click();
    }

    @Step("Перейти на страницу авторизации из формы регистрации")
    public void clickLoginLink() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginLink))
                .click();
    }

    public boolean isPasswordErrorVisible() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(errorText));

            return driver.findElement(errorText).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}