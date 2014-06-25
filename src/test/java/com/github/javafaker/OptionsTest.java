package com.github.javafaker;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OptionsTest {

    private Faker faker;
    private String[] options;

    @Before
    public void before() {
        faker = new Faker();
        options = new String[]{"A", "B", "C"};
    }

    @Test
    public void testOption() {
        List<String> helper = Arrays.asList(options);
        assertTrue(helper.contains(faker.options().option(options)));
    }
}
