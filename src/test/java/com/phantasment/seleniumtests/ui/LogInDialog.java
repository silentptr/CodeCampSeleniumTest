package com.phantasment.seleniumtests.ui;

import com.phantasment.seleniumtests.WebDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.Optional;
import java.util.TimeZone;

public class LogInDialog extends WebDialog
{
    private boolean isOpen = false;
    private String date;

    public LogInDialog(WebDriver webDriver)
    {
        super(webDriver);
        Calendar c = Calendar.getInstance(TimeZone.getDefault());
        date = String.format("%d%02d%02d", c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public boolean IsOpen()
    {
        return isOpen;
    }

    @Override
    public void openDialog()
    {
        webDriver.findElement(By.cssSelector("a[aria-label='users']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input[name='gen-" + date + "-username']")));
        isOpen = true;
    }

    @Override
    public void closeDialog()
    {
        webDriver.findElement(By.cssSelector("a[aria-label='cancel']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='gen-" + date + "-username']")));
        isOpen = false;
    }

    public void clickLoginButton()
    {
        webDriver.findElement(By.className("v-card__actions")).findElement(By.cssSelector("button[id='loginButton']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfElementLocated(By.className("v-messages__message")));
    }

    public void sendUsernameKeys(CharSequence keys)
    {
        webDriver.findElement(By.cssSelector("input[name='gen-" + date + "-username']")).sendKeys(keys);
    }

    public void setPasswordKeys(CharSequence keys)
    {
        webDriver.findElement(By.cssSelector("input[name='gen-" + date + "-password']")).sendKeys(keys);
    }

    public Optional<String> getMessageText()
    {
        try
        {
            return Optional.of(webDriver.findElement(By.className("v-messages__message")).getText());
        }
        catch (Throwable t)
        {
            return Optional.empty();
        }
    }
}
