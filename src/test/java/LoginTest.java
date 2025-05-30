import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
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

    @Test
    @Step
    public void login(){

    //email with invalid
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input")); // Update selector if needed
        emailField.sendKeys("invalid-email@");
        String enteredEmail = emailField.getAttribute("value");
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        Assert.assertFalse(enteredEmail.matches(emailPattern), "Invalid email format should not be accepted!");

        WebElement login=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
        login.click();
        WebElement emailField0 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input"));
        emailField0.clear();// Update selector if needed

    //Email with Valid
        WebElement emailField1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input")); // Update selector if needed
        emailField1.sendKeys("testinghimanshu6@gmail.com");
        String enteredEmail1 = emailField1.getAttribute("value");
        String emailPattern1 = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        Assert.assertTrue(enteredEmail1.matches(emailPattern1), "Email format is invalid!");
        WebElement login1=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
        login1.click();


    //password with invalid
        WebElement passwordField1 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input"));
        passwordField1.sendKeys("short");
        String enteredPassword1 = passwordField1.getAttribute("value");
        String passwordPattern1 = "^.{8,}$";
        Assert.assertFalse(enteredPassword1.matches(passwordPattern1), "Short password should be rejected!");


        WebElement login3=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
        login3.click();
        WebElement passwordField01 = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input"));
        passwordField01.clear();

    //password with valid
        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input")); // Adjust if needed
        passwordField.sendKeys("123456789");
        String enteredPassword = passwordField.getAttribute("value");
        String passwordPattern = "^.{8,}$";
        Assert.assertTrue(enteredPassword.matches(passwordPattern), "Password should be at least 8 characters long!");

        WebElement login2=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
        login2.click();


//  //login with valid id password
//
//        WebElement emailEl=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input"));
//        emailEl.sendKeys("testinghimanshu6@gmail.com");
//        WebElement passwordEl=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input"));
//        passwordEl.sendKeys("123456789");
//        WebElement showPassword=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/div/img"));
//        showPassword.click();
//        WebElement login4=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
//        login4.click();

//    // Forget password
//        WebElement forgetpassword=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/h4[1]"));
//        forgetpassword.click();
//        WebElement enterEmail=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div/div/div"));
//        enterEmail.sendKeys("testinghimanshu6@gmail.com");
//        WebElement sendEmail=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button/span/span"));
//        sendEmail.click();
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
