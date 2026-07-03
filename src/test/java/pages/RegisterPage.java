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

        System.out.println("NAME = " + nameInput.getAttribute("value"));
        System.out.println("EMAIL = " + emailInput.getAttribute("value"));
        System.out.println("PASSWORD_LENGTH = "
                + passwordInput.getAttribute("value").length());

        wait.until(ExpectedConditions.elementToBeClickable(registerButton))
                .click();
    }
}