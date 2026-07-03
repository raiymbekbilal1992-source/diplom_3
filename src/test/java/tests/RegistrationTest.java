package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import models.User;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.BaseTest;
import utils.UserGenerator;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверка успешной регистрации нового пользователя")
    public void userCanRegisterSuccessfully() {

        User user = UserGenerator.getRandomUser();

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        homePage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.registerUser(
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        assertTrue(driver.getCurrentUrl().contains("/login"));
    }

    @Test
    @DisplayName("Ошибка для короткого пароля")
    @Description("Проверка сообщения об ошибке при пароле менее 6 символов")
    public void shouldShowErrorForShortPassword() {

        User user = UserGenerator.getRandomUser();

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        homePage.clickLoginButton();
        loginPage.clickRegisterLink();

        registerPage.registerUser(
                user.getName(),
                user.getEmail(),
                "12345"
        );

        assertTrue(registerPage.isPasswordErrorVisible());
    }
}