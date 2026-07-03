package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private final WebDriver driver;

    private final By logoutButton =
            By.xpath(".//button[text()='Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Выйти из аккаунта")
    public void logout() {
        driver.findElement(logoutButton).click();
    }
}