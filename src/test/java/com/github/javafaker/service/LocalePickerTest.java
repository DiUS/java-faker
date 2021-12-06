package com.github.javafaker.service;

import com.github.javafaker.AbstractFakerTest;
import com.github.javafaker.repeating.Repeat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList; 
import java.util.Random;
import java.util.Collections;
import java.io.File;

import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.hasItems;


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
     * Test to check LocalePicker's getLocaleString method is using the random number generator
     *   passed as an argument. This is checked with a Random object that has a fixed seed and
     *   should have deterministic results.
     */
    @Test
    public void testGetLocaleStringRandom() {
        // Check that we get the same locale when using pseudorandom number generator with a fixed seed
        final long fixedSeed = 5;

        Random random1 = new Random(fixedSeed);
        String randomLocale1 = localePicker.getLocaleString(random1);

        Random random2 = new Random(fixedSeed);
        String randomLocale2 = localePicker.getLocaleString(random2);

        assertEquals(randomLocale1, randomLocale2);
    }

    /**
     * Test to check LocalePicker's getLocaleString method. It verifies that the randomly selected
     *   locale is within the set of all locales supported by Java Faker.
     */
    @Test
    @Repeat(times = 1000)
    public void testGetLocale() {

        Random random = new Random();
        String randomLocale = localePicker.getLocaleString(random);
        assertThat(allLocales, hasItems(randomLocale));
    }

    /**
     * Test to check LocalePicker's getLocaleStringWithoutReplacement method.
     *   It randomly selects n locales where n is the number of locales supported by Java Faker.
     *   It ensures that all the locales supported by Java Faker are represented once.
     */
    @Test
    public void testGetLocaleStringWithoutReplacement() {
        Random random = new Random();
        List<String> returnedLocales;
        int numSupportedLocales = allLocales.size();

        // loop through all locales supported by Java Faker twice
        for (int i=0; i < 2; i++) {
            returnedLocales = new ArrayList<String>(numSupportedLocales);

            for (int j=0; j < numSupportedLocales; j++) {
                returnedLocales.add(localePicker.getLocaleStringWithoutReplacement(random));
            }

            Collections.sort(returnedLocales);
            Collections.sort(allLocales);
            assertEquals(returnedLocales, allLocales);
        }
    }


}
