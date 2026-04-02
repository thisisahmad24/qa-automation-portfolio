package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

        loginPage = new LoginPage(driver);
    }

    //TC_01 - Valid Login
    @Test
    public void validLoginTest() {

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();

        Assert.assertTrue(msg.contains("You logged into a secure area!"));
    }

    //TC_03 - Invalid Username
    @Test
    public void invalidLoginTest() {

        loginPage.enterUsername("wrong");
        loginPage.enterPassword("wrong");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();

        Assert.assertTrue(msg.contains("Your username is invalid!"));
    }

    //TC_02 - Invalid Password
    @Test
    public void InvalidPasswordTest() {
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("Your password is invalid!"));
    }

    //TC_04 - Empty Fields
    @Test
    public void emptyLoginTest() {
        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();

        Assert.assertTrue(msg.contains("Your username is invalid!"));
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}