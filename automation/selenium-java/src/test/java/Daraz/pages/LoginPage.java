package Daraz.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased to 20s
    }

    // === Improved Locators (more specific) ===
    private final By loginLink = By.xpath("//a[contains(text(),'Login') or contains(@class,'login')]");

    // Email/Phone field - Daraz often uses phone or email in one field
    private final By emailOrPhoneField = By.cssSelector("input[type='text'], input[placeholder*='email'], input[placeholder*='phone'], input[name*='email']");

    // Password field
    private final By passwordField = By.cssSelector("input[type='password']");

    // Submit/Login button - Try multiple strategies
    private final By loginButton = By.xpath(
            "//button[contains(text(),'Login') or contains(text(),'LOG IN') or contains(@class,'login') " +
                    "or @type='submit' and not(contains(@class,'disabled'))]"
    );

    // Alternative: by visible text + parent form
    // private final By loginButton = By.xpath("//form//button[contains(translate(text(),'LOGIN','login'),'login')]");

    // Error message (make it broader)
    private final By errorMsg = By.xpath(
            "//*[contains(text(),'incorrect') or contains(text(),'Invalid') or contains(text(),'wrong') " +
                    "or contains(text(),'error') or contains(@class,'error')]"
    );

    // Profile icon after successful login
    private final By profileIcon = By.xpath("//span[contains(@class,'account') or contains(text(),'Account') or contains(@class,'user')]");

    // === Actions ===
    public void openLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(emailOrPhoneField));
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        field.clear();
        field.sendKeys(password);
    }

    public void clickLogin() {
        // Scroll into view + wait for clickable (helps with dynamic forms)
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));

        // Optional: JavaScript click as fallback if normal click fails
        // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        btn.click();
    }

    public String getErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
            return error.getText().trim();
        } catch (Exception e) {
            return "NO ERROR MESSAGE FOUND";
        }
    }

    public boolean isProfileVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterEmailOrPhone(String mail) {
    }
}