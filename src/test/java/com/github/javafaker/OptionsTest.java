package com.github.javafaker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.oneOf;

public class OptionsTest extends AbstractFakerTest {

    private String[] options;

    @BeforeEach
    public void setupOptions() {
        options = new String[]{"A", "B", "C"};
    }

    @Test
    public void testOptionWithArray() {
        assertThat(faker.options().option(options), is(oneOf((options))));
    }

    @Test
    public void testOptionWithVarargsString() {
        assertThat(faker.options().option("A", "B", "C"), is(oneOf(options)));
    }

    @Test
    public void testOptionWithVarargsInteger() {
        Integer[] integerOptions = new Integer[] { 1, 3, 4, 5};
        assertThat(faker.options().option(1, 3, 4, 5), is(oneOf(integerOptions)));
    }

    @Test
    public void testOptionWithEnum() {
        assertThat(faker.options().option(Day.class), is(oneOf(Day.values())));
    }

    @Test
    public void testNextArrayElement() {
        Integer[] array = new Integer[] { 1, 2, 3, 5, 8, 13, 21 };

        for (int i = 1; i < 10; i++) {
            assertThat(faker.options().nextElement(array), is(in(array)));
        }
    }

    @Test
    public void testNextListElement() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 8, 13, 21);
        for (int i = 1; i < 10; i++) {
            assertThat(faker.options().nextElement(list), is(in(list)));
        }
    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
