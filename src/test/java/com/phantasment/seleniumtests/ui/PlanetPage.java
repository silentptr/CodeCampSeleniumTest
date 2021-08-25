package com.phantasment.seleniumtests.ui;

import com.phantasment.seleniumtests.Planet;
import com.phantasment.seleniumtests.matching.Matchable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlanetPage
{
    private WebDriver webDriver;
    private WebElement planetsElement;
    private ArrayList<Planet> planets;

    public PlanetPage(WebDriver webDriver) throws ParseException
    {
        this.webDriver = webDriver;
        planetsElement = webDriver.findElement(By.className("planets"));
        loadPlanets();
    }

    public void gotoPage()
    {
        webDriver.findElement(By.cssSelector("a[href='#/planets']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfElementLocated(By.className("v-card__text")));
    }

    private ArrayList<Planet> loadPlanets() throws ParseException
    {
        ArrayList<Planet> planets = new ArrayList<>();
        NumberFormat nf = NumberFormat.getNumberInstance();

        for (WebElement e : planetsElement.findElements(By.className("planet")))
        {
            String distance = e.findElement(By.className("distance")).getText();
            String radius = e.findElement(By.className("radius")).getText();
            planets.add(new Planet(
                    e.findElement(By.tagName("h2")).getText(),
                    nf.parse(distance.substring(0, distance.length() - 3)).longValue(),
                    nf.parse(radius.substring(0, radius.length() - 3)).doubleValue())
            );
        }

        return planets;
    }

    public List<Planet> getPlanets()
    {
        return Collections.unmodifiableList(planets);
    }

    public Planet getPlanetBy(Matchable<Planet> matcher)
    {
        for (Planet p : planets)
        {
            if (matcher.match(p))
            {
                return p;
            }
        }

        return null;
    }
}
