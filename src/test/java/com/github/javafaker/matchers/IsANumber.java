package com.github.javafaker.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class IsANumber extends TypeSafeMatcher<String> {

    @Override
    protected boolean matchesSafely(String item) {
        try {
            Double.parseDouble(item);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void describeTo(Description description) {
        description.appendText("is a number");
    }

    @Factory
    public static <T> Matcher<String> isANumber() {
        return new IsANumber();
    }
}
