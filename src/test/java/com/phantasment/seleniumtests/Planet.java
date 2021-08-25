package com.phantasment.seleniumtests;

public class Planet
{
    private String name;
    private long distanceFromSun;
    private double radius;

    public Planet(String name, long distanceFromSun, double radius)
    {
        this.name = name;
        this.distanceFromSun = distanceFromSun;
        this.radius = radius;
    }

    public String getName()
    {
        return name;
    }

    public long getDistanceFromSun()
    {
        return distanceFromSun;
    }

    public double getRadius()
    {
        return radius;
    }
}
