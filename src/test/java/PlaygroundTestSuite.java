import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlaygroundTestSuite
{
    @Test
    public void exampleTest()
    {
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://d1iw6mb9di5l9r.cloudfront.net/");
        webDriver.findElement(By.id("forename")).sendKeys("Logan");
        webDriver.findElement(By.id("submit")).click();
        webDriver.findElement(By.cssSelector("a[role='button']")).click();
        webDriver.findElement(By.cssSelector("button[aria-label='home']")).click();
    }
}
