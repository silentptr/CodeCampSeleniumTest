package com.phantasment.seleniumtests.ui;

import com.phantasment.seleniumtests.Planet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class PlanetPage
{
    private WebDriver webDriver;

    public PlanetPage(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    public void gotoPage()
    {
        webDriver.findElement(By.cssSelector("a[href='#/planets']")).click();
        new WebDriverWait(webDriver, 2L).until(ExpectedConditions.visibilityOfElementLocated(By.className("v-card__text")));
    }

    public ArrayList<Planet> getPlanets() throws ParseException
    {
        ArrayList<Planet> planets = new ArrayList<>();
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

        return planets;
    }
}
