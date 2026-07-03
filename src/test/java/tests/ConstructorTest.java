package tests;

import org.junit.Test;
import pages.HomePage;
import utils.BaseTest;

public class ConstructorTest extends BaseTest {

    @Test
    public void userCanOpenBunsSection() {

        HomePage homePage = new HomePage(driver);

        homePage.clickSauces();
        homePage.clickBuns();
    }

    @Test
    public void userCanOpenSaucesSection() {

        HomePage homePage = new HomePage(driver);

        homePage.clickSauces();
    }

    @Test
    public void userCanOpenFillingsSection() {

        HomePage homePage = new HomePage(driver);

        homePage.clickFillings();
    }
}