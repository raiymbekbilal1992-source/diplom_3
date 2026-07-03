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
import utils.BaseTest;
import utils.UserGenerator;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

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

        assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @Test
    public void loginFromPersonalAccount() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickPersonalAccount();

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @Test
    public void loginFromRegisterPage() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();

        loginPage.clickRegisterLink();

        driver.navigate().back();

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        assertTrue(driver.getCurrentUrl().contains("/"));
    }

    @Test
    public void loginFromForgotPasswordPage() {

        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();

        loginPage.clickForgotPasswordLink();

        driver.navigate().back();

        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );

        assertTrue(driver.getCurrentUrl().contains("/"));
    }
}