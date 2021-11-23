package com.github.javafaker;
import com.github.javafaker.repeating.Repeat;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.List; 
import java.io.File;

import com.github.javafaker.service.LocalePicker;

import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class LocalePickerTest extends AbstractFakerTest {

    private LocalePicker localePicker;
    private List<String> allLocales;

    @Before
    public void init() {
        localePicker = new LocalePicker();
        allLocales = localePicker.getAllSupportedLocales();
    }

    @Test
    public void testGetAllSuppportedLocales() {
        // Check that directory of locale resources exists
        File resourceDirectory = new File("./src/main/resources"); 
        assertTrue(resourceDirectory.exists());
        
        // Check that list of locales is not empty
        assertThat(allLocales, not(IsEmptyCollection.empty()));
    }

    @Test
    @Repeat(times = 1000)
    public void testGetRandomLocale() {

        String randomLocale = localePicker.getRandomLocale();
        assertThat(allLocales, hasItems(randomLocale));
    }
}
