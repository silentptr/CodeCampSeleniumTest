package com.phantasment.seleniumtests;

import com.phantasment.seleniumtests.matching.matchers.MatchPlanetByLargestDistance;
import com.phantasment.seleniumtests.ui.*;
import com.phantasment.seleniumtests.util.PlanetChecker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.math.BigDecimal;
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
        webDriver.get("https://d21vtxezke9qd9.cloudfront.net/");
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

    @Test
    public void tableTest()
    {
        ShoppingCart cart = null;

        try
        {
            cart = homePage.getShoppingCart();
        }
        catch (Throwable t)
        {
            Assertions.fail(t);
        }

        List<CartItem> items = cart.getItems();
        Assertions.assertEquals(2, items.size());

        for (int i = 0; i < 2; ++i)
        {
            int quantity = i == 0 ? 1 : 2;
            String name = i == 0 ? "Levi 501s classic denim" : "Plain crewneck T-shirt (white)";
            BigDecimal price = i == 0 ? new BigDecimal("69.99") : new BigDecimal("19.99");
            BigDecimal subTotal = i == 0 ? new BigDecimal("69.99") : new BigDecimal("39.98");
            CartItem item = items.get(i);
            Assertions.assertEquals(name, item.getName());
            Assertions.assertEquals(price, item.getPrice());
            Assertions.assertEquals(subTotal, item.getSubtotal());
            Assertions.assertEquals(quantity, item.getQuantity());
        }

        BigDecimal total = new BigDecimal("109.97");
        Assertions.assertEquals(total, cart.getTotal());
        WebElement totalElement = homePage.getCartTotalElement();
        BigDecimal tableTotal = new BigDecimal(totalElement.getText().replace("$", ""));
        Assertions.assertEquals(total, tableTotal);
    }

    @AfterEach
    public void afterExampleTest()
    {
        webDriver.quit();
    }
}
