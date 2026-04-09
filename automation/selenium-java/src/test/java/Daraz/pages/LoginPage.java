package Daraz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    // Locators (may need slight adjustment via inspect)
    By loginBtn = By.xpath("//a[contains(text(),'Login')]");
    By emailField = By.xpath("//input[@type='text']");
    By passwordField = By.xpath("//input[@type='password']");
    By submitBtn = By.xpath("//button[contains(text(),'LOGIN')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.findElement(loginBtn).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(submitBtn).click();
    }
}