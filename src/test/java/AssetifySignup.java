import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AssetifySignup {

    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\techy\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://assetify-dashboard-dev-testing.web.app/signup");
    }

    @Test(priority = 1)
    @Step("Sign up page")
    public void Signup() {

        WebElement name = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input"));
        name.sendKeys("Himanshu");

        WebElement Email = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input"));
        Email.sendKeys("testinghimanshu6@gmail.com");

        WebElement conteryFlag = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[3]/div/div[2]/div/div/div"));
        conteryFlag.click();

        WebElement conteryname = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[3]/div/div[2]/ul/li[1]/input"));
        conteryname.sendKeys("in");

        WebElement selectContery = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[3]/div/div[2]/ul/li[2]"));
        selectContery.click();

        WebElement mobileNumber = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[3]/div/input"));
        mobileNumber.sendKeys("8005959821");

        WebElement passwordEl = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[4]/div/div/div/input"));
        passwordEl.sendKeys("123456789");

        WebElement cnfpasswordEl = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[5]/div/div/div/input"));
        cnfpasswordEl.sendKeys("123456789");

        WebElement checkbtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[6]/div/input"));
        checkbtn.click();

        WebElement signupBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
        signupBtn.click();

        // Validation part
        String enteredName = name.getAttribute("value");
        String namePattern = "^[A-Za-z ]{2,30}$";

        String enteredEmail = Email.getAttribute("value");
        String emailPattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";

        String enteredPhone = mobileNumber.getAttribute("value");
        String cleanedPhone = enteredPhone.replaceAll("[^0-9]", "");
        if (cleanedPhone.length() > 10) {
            cleanedPhone = cleanedPhone.substring(cleanedPhone.length() - 10); // Keep last 10 digits
        }
        String phonePattern = "^[6-9]\\d{9}$"; // Valid Indian 10-digit mobile number

        String enteredPassword = passwordEl.getAttribute("value");
        String confirmPassword = cnfpasswordEl.getAttribute("value");
        String passwordPattern = "^.{8,}$"; // Minimum 8 characters

        if (enteredName.matches(namePattern)
                && enteredEmail.matches(emailPattern)
                && cleanedPhone.matches(phonePattern)
                && enteredPassword.matches(passwordPattern)
                && enteredPassword.equals(confirmPassword)) {

            System.out.println("All fields are valid. Signup test passed.");
        } else {
            System.out.println("Validation failed! Please check input fields.");
            System.out.println("Name valid? " + enteredName.matches(namePattern));
            System.out.println("Email valid? " + enteredEmail.matches(emailPattern));
            System.out.println("Phone valid? " + cleanedPhone.matches(phonePattern) + " | Extracted: " + cleanedPhone);
            System.out.println("Password valid? " + enteredPassword.matches(passwordPattern));
            System.out.println("Password match? " + enteredPassword.equals(confirmPassword));
        }
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
