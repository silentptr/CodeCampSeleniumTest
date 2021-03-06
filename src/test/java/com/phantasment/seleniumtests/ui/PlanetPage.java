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
import java.util.function.Predicate;

public class PlanetPage
{
    private WebDriver webDriver;
    private ArrayList<Planet> planets;

    public PlanetPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
        planets = new ArrayList<>();
    }

    public void gotoPage()
    {
        webDriver.findElement(By.cssSelector("a[href='#/planets']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfElementLocated(By.className("v-card__text")));
    }

    public void loadPlanets() throws ParseException
    {
        planets.clear();
        NumberFormat nf = NumberFormat.getNumberInstance();

        for (WebElement e : webDriver.findElements(By.className("planet")))
        {
            String distance = e.findElement(By.className("distance")).getText();
            String radius = e.findElement(By.className("radius")).getText();
            planets.add(new Planet(
                    e.findElement(By.tagName("h2")).getText(),
                    nf.parse(distance.substring(0, distance.length() - 3)).longValue(),
                    nf.parse(radius.substring(0, radius.length() - 3)).doubleValue())
            );
        }
    }

    public List<Planet> getPlanets()
    {
        return Collections.unmodifiableList(planets);
    }

    public Planet getPlanetBy(Matchable<Planet> matcher)
    {
        Planet result = null;

        for (Planet p : planets)
        {
            if (matcher.match(p))
            {
                result = p;
            }
        }

        return result;
    }

    public ArrayList<Planet> getPlanetBy(Predicate<Planet> predicate)
    {
        ArrayList<Planet> result = new ArrayList<>();

        for (Planet p : planets)
        {
            if (predicate.test(p))
            {
                result.add(p);
            }
        }

        return result;
    }
}
