package com.phantasment.seleniumtests;

import com.phantasment.seleniumtests.matching.matchers.MatchPlanetByLargestDistance;
import com.phantasment.seleniumtests.matching.matchers.MatchPlanetByName;
import com.phantasment.seleniumtests.ui.HomePage;
import com.phantasment.seleniumtests.ui.LogInDialog;
import com.phantasment.seleniumtests.ui.PlanetPage;
import com.phantasment.seleniumtests.util.PlanetChecker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

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
        try
        {
            planetPage = new PlanetPage(webDriver);
        }
        catch (Throwable t)
        {
            Assertions.fail(t);
        }

        planetPage.gotoPage();
        List<Planet> planets = planetPage.getPlanets();

        for (Planet planet : planets)
        {
            Assertions.assertTrue(PlanetChecker.isValidPlanet(planet));
        }
    }

    @Test
    public void planetPageNeptuneTest()
    {
        planetPage = new PlanetPage(webDriver);
        planetPage.gotoPage();

        try
        {
            planetPage.loadPlanets();
        }
        catch (Throwable t)
        {
            Assertions.fail(t);
        }

        Planet planet = planetPage.getPlanetBy(new MatchPlanetByLargestDistance());
        Assertions.assertNotNull(planet);
        Assertions.assertEquals(planet.getName(), "Neptune");
    }

    @AfterEach
    public void afterExampleTest()
    {
        webDriver.quit();
    }
}
