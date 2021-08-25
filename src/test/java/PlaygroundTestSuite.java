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
import java.util.TimeZone;

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
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-message")));
    }

    @Test
    public void userLoginErrorTest()
    {
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        String date = String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        webDriver.findElement(By.cssSelector("a[aria-label='users']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[name='gen-" + date + "-username']")));
        webDriver.findElement(By.cssSelector("input[name='gen-" + date + "-username']")).sendKeys("test_username");
        webDriver.findElement(By.cssSelector("input[name='gen-" + date + "-password']")).sendKeys("test_password");
        webDriver.findElement(By.className("v-card__actions")).findElement(By.cssSelector("button[id='loginButton']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("v-messages__message")));
        Assertions.assertEquals(webDriver.findElement(By.className("v-messages__message")).getText(), "Invalid user and password");
    }

    @Test
    public void formsModernErrorTest()
    {
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='#/forms']")));
        webDriver.findElement(By.cssSelector("a[href='#/forms']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='name']")));
        webDriver.findElement(By.cssSelector("input[name='name']")).sendKeys("a");
        webDriver.findElement(By.cssSelector("input[name='email']")).sendKeys("a@a.aa");
        webDriver.findElement(By.className("v-select__selections")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='listbox']")));
        webDriver.findElement(By.cssSelector("div[role='listbox']")).findElements(By.cssSelector("div[role='option']")).forEach(option ->
        {
            if (option.findElement(By.className("v-list-item__content")).findElement(By.className("v-list-item__title")).getText().equals("QLD"))
            {
                option.click();
            }
        });
        webDriver.findElement(By.className("v-input--selection-controls__ripple")).click();
        webDriver.findElement(By.className("v-card__text")).findElement(By.tagName("button")).click();
        new WebDriverWait(webDriver, 20L).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
    }

    @AfterEach
    public void afterExampleTest()
    {
        //webDriver.quit();
    }
}
