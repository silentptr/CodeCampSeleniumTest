package com.phantasment.seleniumtests.matching;

public interface Matchable<T>
{
    boolean match(T t);
}
