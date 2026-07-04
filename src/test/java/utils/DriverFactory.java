package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome");

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if ("yandex".equalsIgnoreCase(browser)) {
            String yandexBinaryPath = System.getProperty(
                    "yandexBinaryPath",
                    "C:/Users/bilalulr/AppData/Local/Yandex/YandexBrowser/Application/browser.exe"
            );
            options.setBinary(yandexBinaryPath);
        }

        return new ChromeDriver(options);
    }
}