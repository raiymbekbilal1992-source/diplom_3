package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver;

    private final By loginButton =
            By.cssSelector("#root > div > main > section.BurgerConstructor_basket__29Cd7.mt-25 > div > button");

    private final By personalAccountButton =
            By.linkText("Личный Кабинет");

    private final By constructorButton =
            By.xpath("//p[text()='Конструктор']");

    private final By logo =
            By.cssSelector("div.AppHeader_header__logo__2D0X2");

    private final By bunsTab =
            By.xpath("//span[text()='Булки']");

    private final By saucesTab =
            By.xpath("//span[text()='Соусы']");

    private final By fillingsTab =
            By.xpath("//span[text()='Начинки']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажать кнопку Войти в аккаунт")
    public void clickLoginButton() {

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));

        driver.findElement(loginButton).click();
    }

    @Step("Открыть личный кабинет")
    public void clickPersonalAccount() {

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.invisibilityOfElementLocated(
                        By.cssSelector(".Modal_modal_overlay__x2ZCr")));

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(personalAccountButton));

        driver.findElement(personalAccountButton).click();
    }

    @Step("Перейти в конструктор")
    public void clickConstructor() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        driver.findElement(logo).click();
    }

    @Step("Открыть раздел Булки")
    public void clickBuns() {
        driver.findElement(bunsTab).click();
    }

    @Step("Открыть раздел Соусы")
    public void clickSauces() {
        driver.findElement(saucesTab).click();
    }

    @Step("Открыть раздел Начинки")
    public void clickFillings() {
        driver.findElement(fillingsTab).click();
    }
}