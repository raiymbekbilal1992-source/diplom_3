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

        homePage.clickPersonalAccount();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("profile"));

        assertTrue(driver.getCurrentUrl().contains("profile"));
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

        homePage.clickPersonalAccount();

        homePage.clickConstructor();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/"));

        assertTrue(driver.getCurrentUrl().contains("/"));
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

        homePage.clickPersonalAccount();

        personalAccountPage.clickLogout();

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("/login"));

        assertTrue(driver.getCurrentUrl().contains("/login"));
    }
}