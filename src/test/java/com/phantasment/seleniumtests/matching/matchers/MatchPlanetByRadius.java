package com.phantasment.seleniumtests.matching.matchers;

import com.phantasment.seleniumtests.Planet;
import com.phantasment.seleniumtests.matching.Matchable;

public class MatchPlanetByRadius implements Matchable<Planet>
{
    private double planetRadius;

    public MatchPlanetByRadius(double planetRadius)
    {
        this.planetRadius = planetRadius;
    }

    @Override
    public boolean match(Planet planet)
    {
        return planet.getRadius() == planetRadius;
    }
}
