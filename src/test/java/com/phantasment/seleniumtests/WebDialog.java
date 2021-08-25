package com.phantasment.seleniumtests;

import org.openqa.selenium.WebDriver;

public abstract class WebDialog
{
    protected final WebDriver webDriver;

    public WebDialog(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public abstract boolean IsOpen();
    public abstract void openDialog();
    public abstract void closeDialog();
}
