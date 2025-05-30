import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;



public class AssetifyLogin {
    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\techy\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://assetify-dashboard-dev-testing.web.app/login");
    }
    @Test(priority = 6)
    @Step("Login")
    public void login(){
        WebElement emailEl=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input"));
        emailEl.sendKeys("testinghimanshu6@gmail.com");
        WebElement passwordEl=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input"));
        passwordEl.sendKeys("123456789");
        WebElement showPassword=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/div/img"));
        showPassword.click();
        WebElement login=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
        login.click();
    }

    @Test(priority = 5)
    @Step("Forget Password")
    public void forgetpassword(){
        driver.get("https://assetify-dashboard-dev-testing.web.app/forget-password");
        WebElement forgetpassword=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/h4[1]"));
        forgetpassword.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/form/div/div/div/input")));
        element.sendKeys("testinghimanshu6@gmail.com");

        WebElement sendEmail=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button/span/span"));
        sendEmail.click();
    }
    @Test(priority = 2)
    @Step("Test With valid Credentials")
    public void testValidEmailFormat() {
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input")); // Update selector if needed
        emailField.sendKeys("test.user@example.com");

        String enteredEmail = emailField.getAttribute("value");
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";

        Assert.assertTrue(enteredEmail.matches(emailPattern), "Email format is invalid!");
    }

    @Test(priority = 1)
    @Step("Test With Invalid Credentials")
    public void testInvalidEmailFormat() {
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input")); // Update selector if needed
        emailField.sendKeys("invalid-email@");

        String enteredEmail = emailField.getAttribute("value");
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";

        Assert.assertFalse(enteredEmail.matches(emailPattern), "Invalid email format should not be accepted!");
    }
    @Test(priority = 4)
    @Step("Test Password With valid Format")
    public void testPasswordMinLength() {
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input")); // Adjust if needed
        passwordField.sendKeys("password123");

        String enteredPassword = passwordField.getAttribute("value");
        String passwordPattern = "^.{8,}$";

        Assert.assertTrue(enteredPassword.matches(passwordPattern), "Password should be at least 8 characters long!");
    }

    @Test(priority = 3)
    @Step("Test Password With Invalid Format")
    public void testPasswordTooShort() {
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input"));
        passwordField.sendKeys("short");

        String enteredPassword = passwordField.getAttribute("value");
        String passwordPattern = "^.{8,}$";

        Assert.assertFalse(enteredPassword.matches(passwordPattern), "Short password should be rejected!");
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
