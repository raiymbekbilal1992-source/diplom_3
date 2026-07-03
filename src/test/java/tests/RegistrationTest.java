package tests;

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
}