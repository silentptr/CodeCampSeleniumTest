package com.phantasment.seleniumtests.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class HomePage
{
    WebDriver _webDriver;

    public HomePage(WebDriver webDriver)
    {
        _webDriver = webDriver;
    }

    // GET FUNCTIONS

    public ShoppingCart getShoppingCart() throws ParseException
    {
        ArrayList<CartItem> items = new ArrayList<>();

        WebElement table = _webDriver.findElement(By.className("cart"));
        WebElement tableBody = table.findElement(By.tagName("tbody"));

        for (WebElement tableRow : tableBody.findElements(By.tagName("tr")))
        {
            List<WebElement> rowData = tableRow.findElements(By.tagName("td"));
            int quantity = Integer.parseInt(rowData.get(0).findElement(By.cssSelector("input[type=number]")).getAttribute("value"));
            String name = rowData.get(1).getText();
            BigDecimal price = new BigDecimal(rowData.get(2).getText().replace("$", ""));
            items.add(new CartItem(name, price, quantity));
        }

        return new ShoppingCart(items);
    }

    public WebElement getCartTotalElement()
    {
        return _webDriver.findElement(By.className("cart")).findElement(By.className("cart-total"));
    }

    // CLICK FUNCTIONS

    public HomePage clickFakeLoginButton()
    {
        _webDriver.findElement(By.cssSelector("button[aria-label='login']")).click();
        return this;
    }

    public HomePage clickForenameSubmitButton()
    {
        _webDriver.findElement(By.id("submit")).click();
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

    public void clickAgreeCheckbox()
    {
        _webDriver.findElement(By.className("v-input--selection-controls__ripple")).click();
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
}
