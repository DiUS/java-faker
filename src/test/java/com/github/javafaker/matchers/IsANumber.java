package com.github.javafaker.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Scanner;

public class IsANumber extends TypeSafeMatcher<String> {

    @Override
    protected boolean matchesSafely(String item) {
        Scanner scanner = new Scanner(item);
        boolean firstTokenIsNumberic = scanner.hasNextInt() || scanner.hasNextDouble();
        if (firstTokenIsNumberic) {
            scanner.next();
            return !scanner.hasNext();
        } else {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is a number");
    }

    @Factory
    public static <T> Matcher<String> isANumber() {
        return new IsANumber();
    }
}
