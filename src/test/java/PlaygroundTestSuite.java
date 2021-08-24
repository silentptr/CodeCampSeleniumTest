import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PlaygroundTestSuite
{
    WebDriver webDriver;

    @BeforeEach
    public void beforeExampleTest()
    {
        webDriver = new ChromeDriver();
        webDriver.get("https://d1iw6mb9di5l9r.cloudfront.net/");
        webDriver.manage().window().maximize();
    }

    @Test
    public void exampleTest()
    {
        webDriver.findElement(By.id("forename")).sendKeys("Logan");
        webDriver.findElement(By.id("submit")).click();
    }

    @Test
    public void webDriverWaitTest()
    {
        webDriver.findElement(By.cssSelector("button[aria-label='login']")).click();
        new WebDriverWait(webDriver, 10000L).until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-message")));
        webDriver.findElement(By.cssSelector("a[aria-label='users']")).click();
    }

    @AfterEach
    public void afterExampleTest()
    {
        webDriver.quit();
    }
}
