package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
            By.cssSelector(".AppHeader_header__logo__2D0X2 a[href='/']");

    private final By bunsTab =
            By.xpath("//span[text()='Булки']");

    private final By saucesTab =
            By.xpath("//span[text()='Соусы']");

    private final By fillingsTab =
            By.xpath("//span[text()='Начинки']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Клик через JS — обходит перехват клика прозрачным/перемонтируемым оверлеем модалки
    private void jsClick(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(locator));

        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // Активная вкладка получает CSS-класс с подстрокой "current" на родительском div
    private boolean isTabActive(By tabLocator) {
        WebElement tabText = driver.findElement(tabLocator);
        WebElement tabContainer = tabText.findElement(By.xpath("./.."));
        String classAttribute = tabContainer.getAttribute("class");
        return classAttribute != null && classAttribute.contains("current");
    }

    @Step("Нажать кнопку Войти в аккаунт")
    public void clickLoginButton() {

        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));

        jsClick(loginButton);
    }

    @Step("Открыть личный кабинет")
    public void clickPersonalAccount() {
        jsClick(personalAccountButton);
    }

    @Step("Перейти в конструктор")
    public void clickConstructor() {
        jsClick(constructorButton);
    }

    @Step("Нажать на логотип")
    public void clickLogo() {
        jsClick(logo);
    }

    @Step("Открыть раздел Булки")
    public void clickBuns() {
        jsClick(bunsTab);
    }

    @Step("Открыть раздел Соусы")
    public void clickSauces() {
        jsClick(saucesTab);
    }

    @Step("Открыть раздел Начинки")
    public void clickFillings() {
        jsClick(fillingsTab);
    }

    @Step("Проверить, что активна вкладка Булки")
    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить, что активна вкладка Соусы")
    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить, что активна вкладка Начинки")
    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }
}