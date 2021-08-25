package com.phantasment.seleniumtests.matching;

public interface Matchable<T>
{
    public abstract boolean match(T t);
}
