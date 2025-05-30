import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AssetifyDashboardSettings {
    public WebDriver driver;
    public WebDriverWait wait;  // Declare wait here

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\techy\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://assetify-dashboard-dev-testing.web.app/login");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Initialize wait here
    }

    @Test
    public void profiledetails() {
        Actions actions = new Actions(driver);

        WebElement emailEl = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input"));
        emailEl.sendKeys("testinghimanshu6@gmail.com");

        WebElement passwordEl = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input"));
        passwordEl.sendKeys("123456789");

        WebElement loginSubmitBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
        loginSubmitBtn.click();

        // Wait for the button to be clickable using the wait object declared in this class
        WebElement myButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#root > div > div:nth-child(1) > div > nav > div.flex.items-center.space-x-4.gap-5 > div.hidden.md\\:flex.items-center.gap-5 > button:nth-child(2)")));
        myButton.click();

        WebElement editName = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div/div[2]/div[1]/div/div/div/div/input"));
        editName.clear();
        editName.sendKeys("Himanshu Pratihar");

        WebElement editMobileno = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[1]/div/div[2]/div[3]/div/div/input"));
        editMobileno.clear();
        editMobileno.sendKeys("9636535359");

        WebElement oldPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/div[2]/div[1]/div/div/div/div/input"));
        oldPassword.sendKeys("123456789");

        WebElement newPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/div[2]/div[2]/div/div/div/div/input"));
        newPassword.sendKeys("987654321");

        WebElement cnfPassword = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/div[2]/div[3]/div/div/div/div/input"));
        cnfPassword.sendKeys("987654321");

        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[3]/div[1]/div/div[3]/button")));
        actions.moveToElement(saveBtn).click().perform();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}