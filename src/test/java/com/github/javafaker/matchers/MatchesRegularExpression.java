package com.github.javafaker.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class MatchesRegularExpression extends TypeSafeMatcher<String> {

    private final String regularExpression;

    public MatchesRegularExpression(String regularExpression) {
        this.regularExpression = regularExpression;
    }

    @Override
    protected boolean matchesSafely(String item) {
        return item.matches(regularExpression);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("to match the regular expression " + regularExpression);
    }

    @Factory
    public static <T> Matcher<String> matchesRegularExpression(String regularExpression) {
        return new MatchesRegularExpression(regularExpression);
    }
}
