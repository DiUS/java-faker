package com.github.javafaker.service;

import com.github.javafaker.AbstractFakerTest;
import com.github.javafaker.repeating.Repeat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List; 
import java.util.Random;
import java.io.File;

import com.github.javafaker.service.LocalePicker;

import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class LocalePickerTest extends AbstractFakerTest {

    private LocalePicker localePicker;
    private List<String> allLocales;

    /**
     * Initialize tests by instantiating a LocalePicker object and list of all locales supported in Java Faker
     */
    @Before
    public void init() {
        localePicker = new LocalePicker();
        allLocales = localePicker.getAllSupportedLocales();
    }

    /**
     * Test to check that list of all locales support in Java Faker is loaded
     */
    @Test
    public void testGetAllSuppportedLocales() {
        // Check that directory of locale resources exists
        File resourceDirectory = new File("./src/main/resources"); 
        assertTrue(resourceDirectory.exists());
        
        // Check that list of locales is not empty
        assertThat(allLocales, not(IsEmptyCollection.empty()));
    }

    /**
     * Test to check LocalePicker's getRandomLocale method is using the random number generator
     *   passed as an argument. This is checked with a Random object that has a fixed seed and
     *   should have deterministic results.
     */
    @Test
    public void testGetRandomLocale() {
        // Check that we get the same locale when using pseudorandom number generator with a fixed seed
        final long SEED_FIXED = 5;

        Random random1 = new Random(SEED_FIXED);
        String randomLocale1 = localePicker.getRandomLocale(random1);

        Random random2 = new Random(SEED_FIXED);
        String randomLocale2 = localePicker.getRandomLocale(random2);

        assertEquals(randomLocale1, randomLocale2);
    }

    /**
     * Test to check LocalePicker's getLocale method. It verifies that the randomly selected
     *   locale is within the set of all locales supported by Java Faker.
     */
    @Test
    @Repeat(times = 1000)
    public void testGetLocale() {

        String randomLocale = localePicker.getLocale();
        assertThat(allLocales, hasItems(randomLocale));
    }
}
