package com.phantasment.seleniumtests.matching;

import com.phantasment.seleniumtests.Planet;

import java.util.function.Predicate;

public final class PlanetPredicates
{
    private PlanetPredicates() { }

    public static Predicate<Planet> byName(String name)
    {
        return planet -> planet.getName().equals(name);
    }

    public static Predicate<Planet> byDistanceFromSun(long distanceFromSun)
    {
        return planet -> planet.getDistanceFromSun() == distanceFromSun;
    }

    public static Predicate<Planet> byRadius(double radius)
    {
        return planet -> planet.getRadius() == radius;
    }

    public static Predicate<Planet> ifDistanceFromSunLessThan(long distanceFromSun)
    {
        return planet -> planet.getDistanceFromSun() < distanceFromSun;
    }

    public static Predicate<Planet> ifDistanceFromSunGreaterThan(long distanceFromSun)
    {
        return planet -> planet.getDistanceFromSun() > distanceFromSun;
    }

    public static Predicate<Planet> ifRadiusLessThan(double radius)
    {
        return planet -> planet.getRadius() < radius;
    }

    public static Predicate<Planet> ifRadiusGreaterThan(double radius)
    {
        return planet -> planet.getRadius() > radius;
    }
}
