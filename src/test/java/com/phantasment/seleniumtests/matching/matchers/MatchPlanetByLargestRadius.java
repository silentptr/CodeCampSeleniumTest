package com.phantasment.seleniumtests.matching.matchers;

import com.phantasment.seleniumtests.Planet;
import com.phantasment.seleniumtests.matching.Matchable;

public class MatchPlanetByLargestRadius implements Matchable<Planet>
{
    private Planet p;

    @Override
    public boolean match(Planet planet)
    {
        if (p == null || planet.getRadius() > p.getRadius())
        {
            p = planet;
            return true;
        }
        else
        {
            return false;
        }
    }
}
