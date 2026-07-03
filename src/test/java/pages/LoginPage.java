package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    // Поле Email
    private final By emailField =
            By.xpath("//input[@name='name']");

    // Поле Пароль
    private final By passwordField =
            By.xpath("//input[@type='password']");

    // Кнопка Войти
    private final By loginButton =
            By.xpath("//button[contains(text(),'Войти')]");

    // Ссылка Зарегистрироваться
    private final By registerLink =
            By.xpath("//a[contains(text(),'Зарегистрироваться')]");

    // Ссылка Восстановить пароль
    private final By forgotPasswordLink =
            By.xpath("//a[contains(text(),'Восстановить пароль')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email")
    public void setEmail(String email) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));

        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordField));

        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку Войти")
    public void clickLoginButton() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(loginButton));

        driver.findElement(loginButton).click();
    }

    @Step("Авторизация пользователя")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

    @Step("Перейти на регистрацию")
    public void clickRegisterLink() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(registerLink));

        driver.findElement(registerLink).click();
    }

    @Step("Перейти на восстановление пароля")
    public void clickForgotPasswordLink() {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(forgotPasswordLink));

        driver.findElement(forgotPasswordLink).click();
    }
}