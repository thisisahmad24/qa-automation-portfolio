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
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");

        loginPage = new LoginPage(driver);
    }

    // TC_01 - Valid Login
    @Test
    public void validLoginTest() {

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("You logged into a secure area!"));
    }

    // TC_02 - Invalid Username
    @Test
    public void invalidLoginTest() {

        loginPage.enterUsername("wrong");
        loginPage.enterPassword("wrong");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!"));
    }

    // TC_03 - Invalid Password
    @Test
    public void invalidPasswordTest() {

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("Your password is invalid!"));
    }

    // TC_04 - Empty Fields
    @Test
    public void emptyLoginTest() {

        loginPage.enterUsername("");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!"));
    }

    // TC_05 - Empty Password
    @Test
    public void emptyPasswordTest() {

        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("Your password is invalid!"));
    }

    // TC_06 - Special Characters (treated as invalid username)
    @Test
    public void specialCharactersTest() {

        loginPage.enterUsername("tom@smith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!"));
    }

    //TC_07 - Long Input
    @Test
    public void longUsernameTest() {
        loginPage.enterUsername("tommmmmmmmmmmmmmmmsmmmmmmmmmmmmmmmmmmmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("System should handle input properly"));
    }

    //TC_08 - SQL Injection Attempt
    @Test
    public void SqlInjectionTest() {
        loginPage.enterUsername("' OR '1'='1");
        loginPage.enterPassword("anything");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("Your username format is invalid!"));

    }

    //TC_09 - Case Sensitivity
    @Test
    public void CaseInsensitiveTest() {
        loginPage.enterUsername("TOMSMITH");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String msg = loginPage.getMessage();
        Assert.assertTrue(msg.contains("Your username is invalid!"));
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}