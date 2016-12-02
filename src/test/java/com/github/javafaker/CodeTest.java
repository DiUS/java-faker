package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.apache.commons.validator.routines.ISBNValidator;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.junit.Test;

import java.util.Locale;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class CodeTest extends AbstractFakerTest {

    private static final ISBNValidator ISBN_VALIDATOR = ISBNValidator.getInstance(false);

    @Test
    @Repeat(times = 1000)
    public void isbn10DefaultIsNoSeparator() {
        String isbn10 = faker.code().isbn10();

        assertIsValidISBN10(isbn10);
        assertThat(isbn10, not(containsString("-")));
    }

    @Test
    @Repeat(times = 1000)
    public void isbn13DefaultIsNoSeparator() {
        String isbn13 = faker.code().isbn13();

        assertIsValidISBN13(isbn13);
        assertThat(isbn13, not(containsString("-")));
    }

    @Test
    @Repeat(times = 1000)
    public void testIsbn10() {
        final String isbn10NoSep = faker.code().isbn10(false);
        final String isbn10Sep = faker.code().isbn10(true);

        assertThat(isbn10NoSep + " is not null", isbn10NoSep, is(not(nullValue())));
        assertThat(isbn10NoSep + " has length of 10", isbn10NoSep.length(), is(10));
        assertIsValidISBN10(isbn10NoSep);

        assertThat(isbn10Sep + " is not null", isbn10Sep, is(not(nullValue())));
        assertThat(isbn10Sep + " has length of 13", isbn10Sep.length(), is(13));
        assertIsValidISBN10(isbn10Sep);
    }

    @Test
    @Repeat(times = 1000)
    public void testIsbn13() {
        final String isbn13NoSep = faker.code().isbn13(false);
        final String isbn13Sep = faker.code().isbn13(true);

        assertThat(isbn13NoSep + " is not null", isbn13NoSep, is(not(nullValue())));
        assertThat(isbn13NoSep + " has length of 13", isbn13NoSep.length(), is(13));
        assertIsValidISBN13(isbn13NoSep);

        assertThat(isbn13Sep + " is not null", isbn13Sep, is(not(nullValue())));
        assertThat(isbn13Sep + " has length of 17", isbn13Sep.length(), is(17));
        assertIsValidISBN13(isbn13Sep);
    }

    private void assertIsValidISBN10(String isbn10) {
        assertThat(isbn10 + " is valid", ISBN_VALIDATOR.isValidISBN10(isbn10), is(true));
    }

    private void assertIsValidISBN13(String isbn13) {
        assertThat(isbn13 + " is valid", ISBN_VALIDATOR.isValidISBN13(isbn13), is(true));
    }

    @Test
    @Repeat(times = 100)
    public void testOverrides() {
        Faker faker = new Faker(new Locale("test"));

        final String isbn10Sep = faker.code().isbn10(true);
        final String isbn13Sep = faker.code().isbn13(true);

        assertThat("Uses overridden expressions from test.yml", 
                isbn10Sep, 
                matchesRegularExpression("9971-\\d-\\d{4}-(\\d|X)"));
        
        assertThat("Uses overridden expressions from test.yml",
                isbn13Sep, 
                matchesRegularExpression("(333|444)-9971-\\d-\\d{4}-\\d"));
    }

    @Test
    public void asin() {
        assertThat(faker.code().asin(), matchesRegularExpression("B000([A-Z]|\\d){6}"));
    }

    @Test
    public void imei() {
        String imei = faker.code().imei();

        assertThat(imei, matchesRegularExpression("\\A[\\d\\.\\:\\-\\s]+\\z"));
        assertThat(LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(imei), is(true));
    }

    @Test
    public void ean8() {
        assertThat(faker.code().ean8(), matchesRegularExpression("\\d{8}"));
    }

    @Test
    public void gtin8() {
        assertThat(faker.code().gtin8(), matchesRegularExpression("\\d{8}"));
    }

    @Test
    public void ean13() {
        String ean13 = faker.code().ean13();
        assertThat(ean13, matchesRegularExpression("\\d{13}"));
        assertThat(EAN13CheckDigit.EAN13_CHECK_DIGIT.isValid(ean13), is(true));
    }

    @Test
    public void gtin13() {
        String gtin13 = faker.code().gtin13();
        assertThat(gtin13, matchesRegularExpression("\\d{13}"));
        assertThat(EAN13CheckDigit.EAN13_CHECK_DIGIT.isValid(gtin13), is(true));
    }
}
