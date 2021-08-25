package com.phantasment.seleniumtests.matching.matchers;

import com.phantasment.seleniumtests.Planet;
import com.phantasment.seleniumtests.matching.Matchable;

public class MatchPlanetByDistance implements Matchable<Planet>
{
    private long planetDistance;

    public MatchPlanetByDistance(long planetDistance)
    {
        this.planetDistance = planetDistance;
    }

    @Override
    public boolean match(Planet planet)
    {
        return planet.getDistanceFromSun() == planetDistance;
    }
}
