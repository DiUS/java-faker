package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.isIn;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class OptionsTest extends AbstractFakerTest {

    private static final Logger logger = LoggerFactory.getLogger(OptionsTest.class);

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

    @Test
    @Repeat(times=10)
    public void testRandomSubset1() {
        List<Integer> list = new ArrayList<Integer>();
        // Keep the array size to a small size for runtime
        int listSize = (int)(Math.random()*100);
        for (int i=0; i < listSize; i++) {
            // Allow for any positive integer
            list.add((int)(Math.random()*Integer.MAX_VALUE));
        }

        List<Integer> subsetList = faker.options().getRandomSubset(list);
        logger.info("Input list: {}", list);
        logger.info("Random subset: {}", subsetList);

        // Check that every element of the returned subset list is in fact in the original list
        for (int i=0; i < subsetList.size(); i++)
        {
            assertThat(subsetList.get(i),isIn(list));
        }
    }

    @Test
    public void testRandomSubsetEmpty() {
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> subsetList = faker.options().getRandomSubset(list);
        assertEquals(list,subsetList);
    }
}
