import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class connectWallet {

    static WebDriver driver;
    static WebDriverWait wait;

    public static void main(String[] args) throws InterruptedException {

        // 1. Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\techy\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        // 2. Load MetaMask extension
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--remote-allow-origins=*");
        options.addExtensions(new java.io.File("C:\\Users\\techy\\Downloads\\metamask-chrome-12.17.3\\metamask.crx"));

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 3. Wait for MetaMask to load
        Thread.sleep(8000);

        // 4. Switch to MetaMask tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));

        // 5. Start MetaMask import flow
        waitAndClick(By.xpath("//button[text()='Get Started']"));
        waitAndClick(By.xpath("//button[text()='Import wallet']"));
        waitAndClick(By.xpath("//button[text()='No Thanks']"));

        // 6. Fill recovery phrase
        List<WebElement> inputs = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[placeholder='Secret Recovery Phrase']")));
        String[] secretPhrase = {
                "candy", "maple", "cake", "sugar", "pudding", "cream",
                "honey", "rich", "smooth", "crumble", "sweet", "treat"
        };
        for (int i = 0; i < secretPhrase.length; i++) {
            inputs.get(i).sendKeys(secretPhrase[i]);
        }

        // 7. Password setup
        driver.findElement(By.id("password")).sendKeys("YourStrongPassword1");
        driver.findElement(By.id("confirm-password")).sendKeys("YourStrongPassword1");
        driver.findElement(By.cssSelector(".first-time-flow__terms")).click();
        waitAndClick(By.xpath("//button[text()='Import']"));

        // 8. Finish MetaMask setup
        waitAndClick(By.xpath("//button[text()='All Done']"));

        // 9. Open dApp in new tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://assetify-landing-test-7777.web.app");

        // 10. Click Connect Wallet
        waitAndClick(By.xpath("//button[contains(text(),'Connect Wallet')]"));

        // 11. Wait and switch to MetaMask popup
        Thread.sleep(4000);
        switchToMetaMaskPopup();

        // 12. Confirm wallet connection
        waitAndClick(By.xpath("//button[text()='Next']"));
        waitAndClick(By.xpath("//button[text()='Connect']"));

        System.out.println("✅ MetaMask wallet connected successfully!");

        Thread.sleep(4000);
        driver.quit();
    }

    private static void waitAndClick(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    private static void switchToMetaMaskPopup() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getTitle().toLowerCase().contains("metamask")) {
                break;
            }
        }
    }
}
