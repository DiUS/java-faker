package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.junit.Test;

public class CodeTest extends AbstractFakerTest{

    @Test
    public void testIsbn10() {
        String isbn10;
        long sum;
        int i, n;
        for (int j = 0; j < 1000; j++) {
            isbn10 = faker.code().isbn10();
            assertNotNull(isbn10);
            assertEquals(11, isbn10.length());
            sum = 0;
            i = 10;
            for (char c : isbn10.toCharArray()) {
                if (c != '-') {
                    n = c != 'X' ? Integer.valueOf("" + c) : 10;
                    sum += i * n;
                    i = i - 1;
                }
            }
            assertThat(sum % 11, is(0L));
        }
    }

    @Test
    public void testIsbn13() {
        String isbn13;
        long sum;
        for (int j = 0; j < 1000; j++) {
            isbn13 = faker.code().isbn13();
            assertNotNull(isbn13);
            assertEquals(17, isbn13.length());
            sum = 0;
            int multiplier = 1;
            for (char c : isbn13.toCharArray()) {
                if (c != '-') {
                    sum += multiplier * Integer.valueOf("" + c);
                    multiplier = multiplier == 1 ? 3 : 1;
                }
            }
            assertThat(sum % 10, is(0L));
        }
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