package com.github.javafaker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertEquals;
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
    public void testRandomSubset(){
        for (String element : faker.options().randomSubset("A", "B", "C")){
            assertThat(element, isIn(options));
        }
    }

    @Test
    public void testRandomSubsetSingleElement(){
        String[] expectedResult = new String[] { "A" };
        String[] emptyArray = new String[] { };
        assertThat(faker.options().randomSubset("A"), anyOf(is(expectedResult),is(emptyArray)));
    }

    @Test
    public void testRandomSubsetNoElement(){
        String[] expectedResult = new String[] { };
        assertThat(faker.options().randomSubset(), Matchers.<Object[]>is(expectedResult));
    }

    @Test
    public void testOptionWithEnum() {
        assertThat(faker.options().option(Day.class), isOneOf(Day.values()));
    }

    @Test
    public void testNextArrayElement() {
        Integer[] array = new Integer[] {1, 2, 3, 5, 8, 13, 21};
        for (int i = 1; i < 10; i++) {
            assertThat(faker.options().nextElement(array), isIn(array));
        }
    }

    @Test
    public void testRandomSubArray() {
        Integer[] array = new Integer[] { 1, 2, 3, 5, 8, 13, 21 };
        for (int element : faker.options().randomSubArray(array)){
            assertThat(element, isIn(array));
        }
    }

    @Test
    public void testRandomSubArraySingleElement() {
        Integer[] array = new Integer[] { 1 };
        Integer[] emptyArray = new Integer[] { };
        assertThat(faker.options().randomSubArray(array), anyOf(is(array),is(emptyArray)));
    }

    @Test
    public void testRandomSubArrayNoElement() {
        Integer[] array = new Integer[] { };
        Integer[] expectedResult = new Integer[] { };
        assertThat(faker.options().randomSubArray(array), is(expectedResult));
    }

    @Test
    public void testNextListElement() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 8, 13, 21);
        for (int i = 1; i < 10; i++) {
            assertThat(faker.options().nextElement(list), isIn(list));
        }
    }

    @Test
    public void testRandomSubList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 5, 8, 13, 21);
        for (int element : faker.options().randomSubList(list)){
            assertThat(element, isIn(list));
        }
    }

    @Test
    public void testRandomSubListSingleElement() {
        List<Integer> list = Collections.singletonList(1);
        List<Integer> emptyList = Collections.emptyList();
        assertThat(faker.options().randomSubList(list), anyOf(is(Collections.singletonList(1)), is(emptyList)));
    }

    @Test
    public void testRandomSubListNoElement() {
        List<Integer> list = Collections.emptyList();
        assertEquals(faker.options().randomSubList(list), Collections.emptyList());
    }

    public enum Day {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }
}
