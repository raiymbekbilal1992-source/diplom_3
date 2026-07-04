package tests;

import org.junit.Test;
import pages.HomePage;
import utils.BaseTest;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    public void userCanOpenSaucesSection() {

        HomePage homePage = new HomePage(driver);

        homePage.clickSauces();

        assertTrue(homePage.isSaucesTabActive());
    }

    @Test
    public void userCanOpenFillingsSection() {

        HomePage homePage = new HomePage(driver);

        homePage.clickFillings();

        assertTrue(homePage.isFillingsTabActive());
    }

    @Test
    public void userCanOpenBunsSection() {

        HomePage homePage = new HomePage(driver);

        // переключаемся на другую вкладку и обратно на "Булки",
        // чтобы тест был содержательным (по умолчанию она уже активна)
        homePage.clickSauces();
        homePage.clickBuns();

        assertTrue(homePage.isBunsTabActive());
    }
}