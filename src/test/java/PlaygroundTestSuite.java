import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;


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
    }

    @Test
    public void userLoginErrorTest()
    {
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        String date = String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        Logger.getLogger(getClass().getName()).info(date);
        webDriver.findElement(By.cssSelector("a[aria-label='users']")).click();
        new WebDriverWait(webDriver, 2000L).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='gen-" + date + "-username']")));
        webDriver.findElement(By.cssSelector("input[name='gen-" + date + "-username']")).sendKeys("test_username");
        webDriver.findElement(By.cssSelector("input[name='gen-" + date + "-password']")).sendKeys("test_password");
        webDriver.findElement(By.cssSelector("button[id='loginButton']:not([data-v-c76d68e2=''])")).click();
        new WebDriverWait(webDriver, 2000L).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("v-messages__message")));
        Assertions.assertEquals(webDriver.findElement(By.className("v-messages__message")).getText(), "Invalid user and password");
    }

    @AfterEach
    public void afterExampleTest()
    {
        //webDriver.quit();
    }
}
