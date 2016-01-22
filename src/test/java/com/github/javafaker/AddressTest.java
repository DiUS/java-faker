package com.github.javafaker;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static com.github.javafaker.matchers.IsANumber.isANumber;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(value = Parameterized.class)
import java.text.DecimalFormatSymbols;

public class AddressTest {

    private static final Locale FINNISH_LOCALE = new Locale("fi", "FI");
    private static final Logger logger = LoggerFactory.getLogger(AddressTest.class);
    private static final char decimalSeparator = new DecimalFormatSymbols().getDecimalSeparator();
    private Faker faker;

    @Parameterized.Parameters(name = "testing locale {0}")
    public static Collection<Object[]> data() {
        return Lists.transform(Arrays.asList(Locale.ENGLISH, FINNISH_LOCALE, Locale.FRENCH, Locale.GERMAN, Locale.CHINA, Locale.JAPANESE, Locale.TAIWAN), new Function<Locale, Object[]>() {
            @Override
            public Object[] apply(@Nullable Locale input) {
                return new Object[]{input};
            }
        });
    }

    @Rule
    public DefaultLocaleRule defaultLocaleRule = DefaultLocaleRule.root();

    public AddressTest(Locale locale) {
        defaultLocaleRule.setDefault(locale);
    }

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testStreetAddressIsANumber() {
        final String streetAddressNumber = faker.address().streetAddressNumber();
        logger.info("Street Address Number: " + streetAddressNumber);
        assertThat(streetAddressNumber, isANumber());
    }

    @Test
    public void testLatitude() throws ParseException {
        String latStr;
        Double lat;
        for (int i = 0; i < 100; i++) {
            latStr = faker.address().latitude().replace(decimalSeparator, '.');
            assertThat(latStr, isANumber());
            lat = NumberFormat.getInstance(Locale.getDefault()).parse(latStr).doubleValue();
            assertThat("Latitude is less then -90", lat >= -90);
            assertThat("Latitude is greater than 90", lat <= 90);
        }
    }

    @Test
    public void testLongitude() throws ParseException {
        String longStr;
        Double lon;
        for (int i = 0; i < 100; i++) {
            longStr = faker.address().longitude().replace(decimalSeparator, '.');
            assertThat(longStr, isANumber());
            lon = NumberFormat.getInstance(Locale.getDefault()).parse(longStr).doubleValue();
            assertThat("Longitude is less then -180", lon >= -180);
            assertThat("Longitude is greater than 180", lon <= 180);
        }
    }


    @Test
    public void testTimeZone() {
        assertThat(faker.address().timeZone(), matchesRegularExpression("[A-Za-z_]+/[A-Za-z_]+[/A-Za-z_]*"));
    }

    @Test
    public void testState() {
        assertThat(faker.address().state(), matchesRegularExpression("[A-Za-z' ]+"));
    }

    @Test
    public void testCity() {
        assertThat(faker.address().city(), matchesRegularExpression("[A-Za-z' ]+"));
    }
}
