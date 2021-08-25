package com.phantasment.seleniumtests;

import com.phantasment.seleniumtests.ui.HomePage;
import com.phantasment.seleniumtests.ui.LogInDialog;
import com.phantasment.seleniumtests.ui.PlanetPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class PlaygroundTestSuite
{
    WebDriver webDriver;
    HomePage homePage;
    PlanetPage planetPage;
    LogInDialog logInDialog;

    @BeforeEach
    public void beforeExampleTest()
    {
        webDriver = new ChromeDriver();
        webDriver.get("https://d1iw6mb9di5l9r.cloudfront.net/");
        webDriver.manage().window().maximize();
        homePage = new HomePage(webDriver);
        planetPage = new PlanetPage(webDriver);
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

    @Test
    public void planetPageTest()
    {
        planetPage.gotoPage();
        ArrayList<Planet> planets = null;

        try
        {
            planets = planetPage.getPlanets();
        }
        catch (Throwable t)
        {
            Assertions.fail();
        }

        for (Planet planet : planets)
        {
            long distance = 0L;
            double radius = 0.0d;

            switch (planet.getName())
            {
                case "Mercury":
                    distance = 57910000L;
                    radius = 2439.7d;
                    break;
                case "Venus":
                    distance = 108200000L;
                    radius = 6051.8d;
                    break;
                case "Earth":
                    distance = 149600000L;
                    radius = 6371.0d;
                    break;
                case "Mars":
                    distance = 227900000L;
                    radius = 3389.5d;
                    break;
                case "Jupiter":
                    distance = 778500000L;
                    radius = 69911.0d;
                    break;
                case "Saturn":
                    distance = 1434000000L;
                    radius = 58232.0d;
                    break;
                case "Uranus":
                    distance = 2871000000L;
                    radius = 25362.0d;
                    break;
                case "Neptune":
                    distance = 4495000L;
                    radius = 24622.0d;
                    break;
            }

            Assertions.assertFalse(distance == 0L && radius == 0.0d);
            Assertions.assertTrue(planet.getDistanceFromSun() == distance && planet.getRadius() == radius);
        }
    }

    @AfterEach
    public void afterExampleTest()
    {
        //webDriver.quit();
    }
}
