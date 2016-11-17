package com.github.javafaker.integration;

import com.github.javafaker.service.FakeValuesService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * The purpose of these tests is to ensure that the Locales have been properly configured
 * and that methods return values. The unit tests should ensure what the values returned
 * are correct. These tests just ensure that the methods can be invoked.
 */
public class MostSpecificLocaleIT {

    private FakeValuesService en;
    private FakeValuesService en_US;

    @Before
    public void setupFakers() {
        en = new FakeValuesService(new Locale("en"), null);
        en_US = new FakeValuesService(new Locale("en","US"), null);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void resolvesTheMostSpecificLocale() {
        final List<String> enDefaultCountries = (List<String>) en.fetchObject("address.default_country");
        final List<String> enUsDefaultCountries = (List<String>) en_US.fetchObject("address.default_country");
        
        assertThat(enDefaultCountries, hasSize(1));
        assertThat(enUsDefaultCountries, hasSize(3));
        
        assertThat("the default country for en is not en_US", enDefaultCountries, is(not(enUsDefaultCountries)));
    }
}
