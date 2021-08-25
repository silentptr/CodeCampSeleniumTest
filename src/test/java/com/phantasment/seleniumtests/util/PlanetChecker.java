package com.phantasment.seleniumtests.util;

import com.phantasment.seleniumtests.Planet;

public final class PlanetChecker
{
    private PlanetChecker() { }

    public static boolean isValidPlanet(Planet planet)
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
            default:
                return false;
        }

        return planet.getDistanceFromSun() == distance && planet.getRadius() == radius;
    }
}
