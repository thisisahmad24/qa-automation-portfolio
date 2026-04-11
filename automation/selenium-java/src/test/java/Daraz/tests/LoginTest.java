package Daraz.tests;

import Daraz.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        // options.addArguments("--disable-blink-features=AutomationControlled"); // optional

        driver = new ChromeDriver(options);
        driver.get("https://www.daraz.pk/");   // or your base URL

        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1, description = "Valid Login Test")
    public void validLoginTest() {
        loginPage.openLoginPage();
        loginPage.enterEmailOrPhone("your_valid_email_or_phone@gmail.com");  // Replace with real creds
        loginPage.enterPassword("your_valid_password");
        loginPage.clickLogin();

        boolean isLoggedIn = loginPage.isProfileVisible();
        Assert.assertTrue(isLoggedIn, "Login failed - Profile icon not visible");
        System.out.println("Valid Login Test Passed");
    }

    @Test(priority = 2, description = "Invalid Password Test")
    public void invalidPasswordTest() {
        loginPage.openLoginPage();
        loginPage.enterEmailOrPhone("valid_email@gmail.com");
        loginPage.enterPassword("wrongpassword123");
        loginPage.clickLogin();

        String error = loginPage.getErrorMessage();
        Assert.assertTrue(error.contains("incorrect") || error.contains("invalid") || error.contains("wrong"),
                "Expected error message not found. Actual: " + error);
        System.out.println("Invalid Password Test Passed. Error: " + error);
    }

    @Test(priority = 3, description = "Invalid Email Test")
    public void invalidEmailTest() {
        loginPage.openLoginPage();
        loginPage.enterEmailOrPhone("invalid_email@xyz.com");
        loginPage.enterPassword("anyPassword123");
        loginPage.clickLogin();

        String error = loginPage.getErrorMessage();
        Assert.assertFalse(error.equals("NO ERROR MESSAGE FOUND"), "No error shown for invalid email");
        System.out.println("Invalid Email Test Passed. Error: " + error);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}