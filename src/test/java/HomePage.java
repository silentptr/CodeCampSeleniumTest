import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.TimeZone;

public class HomePage
{
    private WebDriver _webDriver;
    private String date;

    public HomePage(WebDriver webDriver)
    {
        _webDriver = webDriver;
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        date = String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
    }

    // CLICK FUNCTIONS

    public HomePage clickLoginButton()
    {
        _webDriver.findElement(By.className("v-card__actions")).findElement(By.cssSelector("button[id='loginButton']")).click();
        return this;
    }

    public HomePage clickFakeLoginButton()
    {
        _webDriver.findElement(By.cssSelector("button[aria-label='login']")).click();
        return this;
    }

    public HomePage clickUserButton()
    {
        _webDriver.findElement(By.cssSelector("a[aria-label='users']")).click();
        return this;
    }

    public HomePage clickForenameSubmitButton()
    {
        _webDriver.findElement(By.id("submit")).click();
        return this;
    }

    public HomePage clickAgreeCheckbox()
    {
        _webDriver.findElement(By.className("v-input--selection-controls__ripple")).click();
        return this;
    }

    public HomePage clickFormSubmitButton()
    {
        _webDriver.findElement(By.className("v-card__text")).findElement(By.tagName("button")).click();
        return this;
    }

    public HomePage clickFormsButton()
    {
        WebElement formsLink = _webDriver.findElement(By.cssSelector("a[href='#/forms']"));
        new WebDriverWait(_webDriver, 2L).until(ExpectedConditions.elementToBeClickable(formsLink));
        formsLink.click();
        new WebDriverWait(_webDriver, 2L).until(ExpectedConditions.visibilityOf(_webDriver.findElement(By.cssSelector("input[name='name']"))));
        return this;
    }

    // SELECT FUNCTIONS

    public boolean selectStateDropdown(String selection)
    {
        boolean validSelection = false;
        _webDriver.findElement(By.className("v-select__selections")).click();
        WebElement listBox = _webDriver.findElement(By.cssSelector("div[role='listbox']"));
        new WebDriverWait(_webDriver, 2L).until(ExpectedConditions.visibilityOf(listBox));

        for (WebElement option : listBox.findElements(By.cssSelector("div[role='option']")))
        {
            if (option.findElement(By.className("v-list-item__content")).findElement(By.className("v-list-item__title")).getText().equals(selection))
            {
                option.click();
                validSelection = true;
            }
        }

        return validSelection;
    }

    // WAIT FUNCTIONS

    public HomePage waitForLoginMessage()
    {
        new WebDriverWait(_webDriver, 2L).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("v-messages__message")));
        return this;
    }

    public HomePage waitForLoginDialog()
    {
        new WebDriverWait(_webDriver, 2L).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='gen-" + date + "-username']")));
        return this;
    }

    public HomePage waitForFakeLoginMessage()
    {
        new WebDriverWait(_webDriver, 2L).until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-message")));
        return this;
    }

    public HomePage waitForFormMessage()
    {
        new WebDriverWait(_webDriver, 20L).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
        return this;
    }

    // SEND KEY FUNCTIONS

    public HomePage sendLoginUserKeys(CharSequence keys)
    {
        _webDriver.findElement(By.cssSelector("input[name='gen-" + date + "-username']")).sendKeys(keys);
        return this;
    }

    public HomePage sendLoginPasswordKeys(CharSequence keys)
    {
        _webDriver.findElement(By.cssSelector("input[name='gen-" + date + "-password']")).sendKeys(keys);
        return this;
    }

    public HomePage sendForenameKeys(CharSequence keys)
    {
        _webDriver.findElement(By.id("forename")).sendKeys(keys);
        return this;
    }

    public HomePage sendFormNameKeys(CharSequence keys)
    {
        _webDriver.findElement(By.cssSelector("input[name='name']")).sendKeys(keys);
        return this;
    }

    public HomePage sendFormEmailKeys(CharSequence keys)
    {
        _webDriver.findElement(By.cssSelector("input[name='email']")).sendKeys(keys);
        return this;
    }

    // GETTERS

    public String getLoginMessageText()
    {
        return _webDriver.findElement(By.className("v-messages__message")).getText();
    }
}
