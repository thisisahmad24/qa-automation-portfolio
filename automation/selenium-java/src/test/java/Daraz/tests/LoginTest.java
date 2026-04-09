package Daraz.tests;

import Daraz.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://www.daraz.pk/");

        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
    }

    @Test
    public void validLoginTest() {
        loginPage.enterEmail("your_email");
        loginPage.enterPassword("your_password");
        loginPage.clickLogin();

        // Assertion will be added after identifying success element
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}