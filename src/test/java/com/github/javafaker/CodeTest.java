package com.github.javafaker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.apache.commons.validator.routines.ISBNValidator;

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
            assertThat(new ISBNValidator().isValidISBN10(isbn10), is(true));
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
            assertThat(new ISBNValidator().isValidISBN13(isbn13), is(true));
        }
    }
}
