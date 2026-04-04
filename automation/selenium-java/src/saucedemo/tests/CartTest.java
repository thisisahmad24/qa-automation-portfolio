package saucedemo.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import saucedemo.pages.LoginPage;
import saucedemo.pages.InventoryPage;

public class CartTest {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        // Login first
        loginPage.login("standard_user", "secret_sauce");
    }

    // TC_01 - Add to Cart
    @Test
    public void addToCartTest() {
        inventoryPage.addItemToCart();

        String count = inventoryPage.getCartCount();

        Assert.assertEquals(count, "1");
    }

    // TC_02 - Remove from Cart
    @Test
    public void removeFromCartTest() {
        inventoryPage.addItemToCart();
        inventoryPage.removeItem();

        boolean badgePresent = driver.getPageSource().contains("shopping_cart_badge");

        Assert.assertFalse(badgePresent);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}