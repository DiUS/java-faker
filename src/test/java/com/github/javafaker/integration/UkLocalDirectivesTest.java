package com.github.javafaker.integration;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Locale;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * The purpose of these tests is to ensure that the Locales have been properly configured
 * and that methods return values. The unit tests should ensure what the values returned
 * are correct. These tests just ensure that the methods can be invoked.
 */
public class UkLocalDirectivesTest {

    /**
     * uk is interesting in that it has feminine and masculine prefixes for street names.  the feminine
     * and masculine prefixes are NOT methods on Address though as they only make sense for this locale (and possibly
     * others).  This test shows we can resolve within the yml file without reaching out to any of the {@link Faker}
     * child objects.
     */
    @Test
    public void resolvesDirectivesOnlyInYmlFile() {
        final Locale uk = new Locale("uk");

        final String streetName = new Faker(uk).address().streetName();

        final ArrayList<String> masc = Lists.newArrayList("пр.", "проспект", "пров.", "провулок");
        final ArrayList<String> fem = Lists.newArrayList("вул.", "вулиця", "пл.", "площа");
        
        boolean startsWithMascPrefix = false,
                startsWithFemPrefix = false;
        
        for (String mascPrefix : masc) {
            startsWithMascPrefix |= streetName.startsWith(mascPrefix);
        }
        for (String femPrefix : fem) {
            startsWithFemPrefix |= streetName.startsWith(femPrefix);
        }
        
        assertThat("the streetname starts with a fem or masc prefix", 
                startsWithFemPrefix || startsWithMascPrefix, 
                is(true));
    }
    
    
}
