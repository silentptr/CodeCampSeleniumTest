package com.phantasment.seleniumtests;

import com.phantasment.seleniumtests.ui.HomePage;
import com.phantasment.seleniumtests.ui.LogInDialog;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlaygroundTestSuite
{
    WebDriver webDriver;
    HomePage homePage;
    LogInDialog logInDialog;

    @BeforeEach
    public void beforeExampleTest()
    {
        webDriver = new ChromeDriver();
        webDriver.get("https://d1iw6mb9di5l9r.cloudfront.net/");
        webDriver.manage().window().maximize();
        homePage = new HomePage(webDriver);
        logInDialog = new LogInDialog(webDriver);
    }

    @Test
    public void exampleTest()
    {
        homePage.sendForenameKeys("Logan").clickForenameSubmitButton();
    }

    @Test
    public void webDriverWaitTest()
    {
        homePage.clickFakeLoginButton().waitForFakeLoginMessage();
    }

    @Test
    public void userLoginTest()
    {
        logInDialog.openDialog();
        logInDialog.sendUsernameKeys("username");
        logInDialog.setPasswordKeys("password");
        logInDialog.clickLoginButton();
        Assertions.assertEquals(logInDialog.getMessageText().get(), "Invalid user and password");
    }

    @Test
    public void formsModernErrorTest()
    {
        homePage.clickFormsButton();
        homePage.sendFormNameKeys("a")
                .sendFormEmailKeys("a@a.aa");

        homePage.clickAgreeCheckbox();
        homePage.selectStateDropdown("QLD");

        homePage.clickFormSubmitButton().waitForFormMessage();
    }

    @AfterEach
    public void afterExampleTest()
    {
        //webDriver.quit();
    }
}
