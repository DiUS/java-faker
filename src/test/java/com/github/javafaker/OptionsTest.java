package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

public class OptionsTest extends AbstractFakerTest {

    private String[] options;

    @Before
    public void setupOptions() {
        options = new String[]{"A", "B", "C"};
    }

    @Test
    public void testOptionWithArray() {
        assertThat(faker.options().option(options), isOneOf(options));
    }

    @Test
    public void testOptionWithVarargsString() {
        assertThat(faker.options().option("A", "B", "C"), isOneOf(options));
    }

    @Test
    public void testOptionWithVarargsInteger() {
        Integer[] integerOptions = new Integer[] { 1, 3, 4, 5};
        assertThat(faker.options().option(1, 3, 4, 5), isOneOf(integerOptions));
    }

    @Test
    public void testOptionWithEnum() {
        assertThat(faker.options().option(Day.class), isOneOf(Day.values()));
    }

    @Test
    public void testNextArrayElement() {
        Integer[] array = new Integer[] { 1, 2, 3, 5, 8, 13, 21 };

        for (int i = 1; i < 10; i++) {
            assertThat(faker.options().nextElement(array), isIn(array));
        }
    }

    @Test
    public void testNextListElement() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 8, 13, 21);
        for (int i = 1; i < 10; i++) {
            assertThat(faker.options().nextElement(list), isIn(list));
        }
    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
