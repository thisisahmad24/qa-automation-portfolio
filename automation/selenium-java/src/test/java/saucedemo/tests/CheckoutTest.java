package saucedemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import saucedemo.pages.LoginPage;
import saucedemo.pages.InventoryPage;
import saucedemo.pages.CheckoutPage;

import java.time.Duration;

public class CheckoutTest {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Flow setup
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemToCart();
        inventoryPage.goToCart();
    }

    // ✅ TC_01 - Successful Checkout
    @Test
    public void successfulCheckoutTest() {

        checkoutPage.startCheckout();
        checkoutPage.fillDetails("Ahmad", "Hassan", "54000");
        checkoutPage.clickContinue();

        // Wait handled inside page
        checkoutPage.finishOrder();

        String msg = checkoutPage.getSuccessMessage();
        Assert.assertTrue(msg.contains("Thank you for your order!"));
    }

    // ✅ TC_02 - Empty Fields Validation
    @Test
    public void emptyCheckoutTest() {

        checkoutPage.startCheckout();
        checkoutPage.fillDetails("", "", "");
        checkoutPage.clickContinue();

        String error = checkoutPage.getErrorMessage();
        Assert.assertTrue(error.contains("Error"));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}