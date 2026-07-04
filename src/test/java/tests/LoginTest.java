package tests;

import api.UserClient;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ForgotPasswordPage;
import pages.HomePage;
import pages.LoginPage;
import pages.RegisterPage;
import utils.BaseTest;
import utils.UserGenerator;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LoginTest extends BaseTest {

    private static final String MAIN_PAGE_URL =
            "https://qa-stellarburgers.education-services.ru/";

    private User user;
    private UserClient userClient;
    private String accessToken;

    @Before
    public void createUser() {

        userClient = new UserClient();

        user = UserGenerator.getRandomUser();

        userClient.createUser(user);

        accessToken = userClient.login(user)
                .jsonPath()
                .getString("accessToken");
    }

    @After
    public void deleteUser() {

        if (accessToken != null) {
            userClient.deleteUser(accessToken);
        }
    }

    @Test
    public void loginFromHomePage() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));

        assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    public void loginFromPersonalAccount() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        // неавторизованный пользователь при клике на "Личный Кабинет"
        // должен быть переадресован на /login
        homePage.clickPersonalAccount();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        assertFalse(driver.getCurrentUrl().contains("/login"));
    }

    @Test
    public void loginFromRegisterPage() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        homePage.clickLoginButton();

        loginPage.clickRegisterLink();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/register"));

        // реальный клик по ссылке "Войти" внутри формы регистрации
        registerPage.clickLoginLink();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));

        assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }

    @Test
    public void loginFromForgotPasswordPage() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        homePage.clickLoginButton();

        loginPage.clickForgotPasswordLink();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/forgot-password"));

        // реальный клик по ссылке "Войти" внутри формы восстановления пароля
        forgotPasswordPage.clickLoginLink();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlToBe(MAIN_PAGE_URL));

        assertEquals(MAIN_PAGE_URL, driver.getCurrentUrl());
    }
}