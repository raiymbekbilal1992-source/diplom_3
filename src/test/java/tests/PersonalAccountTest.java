package tests;

import api.UserClient;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.PersonalAccountPage;
import utils.BaseTest;
import utils.UserGenerator;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PersonalAccountTest extends BaseTest {

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
    public void userCanOpenPersonalAccount() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        // ждём, что после логина мы точно ушли со страницы /login,
        // прежде чем кликать по хедеру
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        homePage.clickPersonalAccount();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/account"));

        assertTrue(driver.getCurrentUrl().contains("/account"));
    }

    @Test
    public void userCanGoToConstructorFromPersonalAccount() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        homePage.clickPersonalAccount();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/account"));

        homePage.clickConstructor();

        // проверяем, что вернулись именно на главную (конструктор),
        // а не просто "URL содержит /" — это условие было бы true всегда
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/account")));

        assertEquals(
                "https://qa-stellarburgers.education-services.ru/",
                driver.getCurrentUrl()
        );
    }

    @Test
    public void userCanGoToConstructorViaLogoFromPersonalAccount() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        homePage.clickPersonalAccount();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/account"));

        homePage.clickLogo();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/account")));

        assertEquals(
                "https://qa-stellarburgers.education-services.ru/",
                driver.getCurrentUrl()
        );
    }

    @Test
    public void userCanLogout() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage =
                new PersonalAccountPage(driver);

        homePage.clickLoginButton();

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.not(ExpectedConditions.urlContains("/login")));

        homePage.clickPersonalAccount();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/account"));

        personalAccountPage.clickLogout();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}