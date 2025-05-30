import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MetaMaskAutomation {
    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\techy\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://assetify-landing-test-7777.web.app/");
    }
    @Test
    @Step
    public void loginPage(){
        WebElement loginBtnEl=driver.findElement(By.xpath("//*[@id=\"scroll-container\"]/nav/div/div[1]/div/a[9]"));
        loginBtnEl.click();
        String mainWindow = driver.getWindowHandle();

        Set<String> allWindows = driver.getWindowHandles();


        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        WebElement emailInput=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[1]/div/div/input"));
        emailInput.sendKeys("testinghimanshu6@gmail.com");
        WebElement passwordEl=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/div[2]/div/div/input"));
        passwordEl.sendKeys("123456789");
        WebElement loginSubmitBtn=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button"));
        loginSubmitBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlToBe("https://assetify-dashboard-dev-testing.web.app/dashboard"));

        WebElement chooseCurrencyEl=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div/div/div[2]/div[1]/div/div/div[1]/div[2]"));
        chooseCurrencyEl.click();
        WebElement chooseCurrencyEl1=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div/div/div[2]/div[1]/div/div/div[2]/div"));
        chooseCurrencyEl1.click();
        WebElement chooseCurrencyEl2=driver.findElement(By.xpath("//*[@id=\"react-select-2-input\"]"));
        chooseCurrencyEl2.sendKeys("eth");


        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement desiredOption = wait2.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(), 'ETH')]")  // or whatever your dropdown option looks like
        ));
        desiredOption.click();
        WebElement amounSection=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/div/input"));
        amounSection.sendKeys("50");
        WebElement amountInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/div/input"));

        String amountValue = amountInput.getAttribute("value");

// Trim spaces
        amountValue = amountValue.trim();

// Check if it's empty
        if (amountValue.isEmpty()) {
            System.out.println("Amount input is empty");
//            throw new RuntimeException("Amount input field is empty. Please enter a value.");
        }

// Clean value (optional, in case there are extra chars)
        amountValue = amountValue.replaceAll("[^0-9.]", "");

        double amount = Double.parseDouble(amountValue);

        if (amount < 50) {
            System.out.println("Error: Amount is less than 50");
//            throw new RuntimeException("Amount must be at least 50");
        } else {
            System.out.println("Valid amount: " + amount);
        }


        WebElement amountInput1 = driver.findElement(By.xpath(
                "//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div/div/div[2]/div[3]/div/div/div/input"
        ));

        String amountValue1 = amountInput1.getAttribute("value").trim().replaceAll("[^0-9.]", "");

        if (!amountValue1.isEmpty()) {
            double amount1 = Double.parseDouble(amountValue1);
            if (amount1 > 0) {
                System.out.println("Amount is more than 0: " + amount1);
            } else {
                System.out.println("Amount is 0 or less");
            }
        } else {
            System.out.println("Amount input is empty");
        }

        WebElement checkBtn=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div/div/div[3]/input"));
        checkBtn.click();
        WebElement buyNowbtn=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div/div/div[4]/button"));
        buyNowbtn.click();

        // Wait for the modal to be visible
        WebDriverWait waitt = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement modal = waitt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div[1]/div[1]/div[2]/div")));

// Close the modal (assuming it has a close button)
        WebElement closeButton = driver.findElement(By.cssSelector("#root > div > div.main-dashboard-content.mt-8.text-white > div > div:nth-child(1) > div.col-span-1.lg\\:col-span-1 > div.modal-overlay > div > button > svg > path"));
        closeButton.click();
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
