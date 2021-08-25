package com.phantasment.seleniumtests.matching.matchers;

import com.phantasment.seleniumtests.Planet;
import com.phantasment.seleniumtests.matching.Matchable;

public class MatchPlanetByName implements Matchable<Planet>
{
    private String planetName;

    public MatchPlanetByName(String planetName)
    {
        this.planetName = planetName;
    }

    @Override
    public boolean match(Planet p)
    {
        return p.getName().equals(planetName);
    }
}
