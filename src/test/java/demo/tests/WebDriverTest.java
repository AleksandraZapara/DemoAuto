package demo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTest {

    private static WebDriver driver;

    @BeforeSuite
    public static void openChrome(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
    }

    @Test(description = "Verify Title", priority = 1)
    public static void testGetTitleByUrl() {
        String expectedTitle = "DEMOQA";
        String originalTitle = driver.getTitle();
        Assert.assertEquals(originalTitle, expectedTitle, "Title is wrong: " + originalTitle);
    }

    @Test(description = "Verify navigate to the page", priority = 2)
    public static void testGetPageByUrl() {
        WebElement trainingHref = driver.findElement(By.xpath("//div[@class='home-banner']//*[contains(@href,'https://www.toolsqa.com/selenium-training/')]"));
        trainingHref.click();
        driver.switchTo().window(driver.getWindowHandle());
    }

    @Test(description = "Verify Select Elements Tab", priority = 3)
    public static void testGetElementsTab() {
        String expectedName = "Elements";
        WebElement elementsTab = driver.findElement(By.xpath("//div[@class='card-body']//h5[text()='"+expectedName+"']"));
        Assert.assertTrue(elementsTab.isDisplayed());
        elementsTab.click();
    }

    @Test(description = "Verify fill name", priority = 4)
    public static void testFillName() {
        WebElement textBoxTab = driver.findElement(By.xpath("//span[@class='text']"));
        textBoxTab.click();
        WebElement inputName = driver.findElement(By.xpath("//input[@id='userName']"));
        inputName.sendKeys("Popov Sasha");
        Assert.assertTrue(inputName.isDisplayed());
    }

    @Test(description = "Verify fill Email",  priority = 5)
    public static void testFillEmail() {
        WebElement inputEmail = driver.findElement(By.xpath("//input[@id='userEmail']"));
        inputEmail.sendKeys("Popov@gmail.com");
        Assert.assertTrue(inputEmail.isDisplayed());
    }

    @AfterTest
    public static void closeChrome(){
        driver.quit();
    }
}
