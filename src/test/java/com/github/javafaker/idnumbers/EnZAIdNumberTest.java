package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EnZAIdNumberTest {


    @Test
    public void valid() {
        EnZAIdNumber idNumber = new EnZAIdNumber();
        final Faker f = new Faker(new Locale("en-ZA"));

        assertThat(idNumber.validSsn(f.idNumber().valid()), is(true));
        assertThat(idNumber.validSsn(f.idNumber().valid()), is(true));

        assertThat(idNumber.validSsn("9202204720083"), is(true));
        assertThat(idNumber.validSsn("8801235111088"), is(true));
    }

    @Test
    public void invalid() {
        EnZAIdNumber idNumber = new EnZAIdNumber();
        final Faker f = new Faker(new Locale("en-ZA"));

        assertThat(idNumber.validSsn(f.idNumber().invalid()), is(false));
        assertThat(idNumber.validSsn(f.idNumber().invalid()), is(false));

        assertThat(idNumber.validSsn("9202204720085"), is(false));
        assertThat(idNumber.validSsn("foo2204720082"), is(false));
        assertThat(idNumber.validSsn("9232454720082"), is(false));

    }

    @Test
    public void testValidSsn() {
        final Faker f = new Faker(new Locale("en-ZA"));
        for (int i = 0; i < 100; i++) {
            Assert.assertThat(f.idNumber().valid(), matchesRegularExpression("\\d{10}[01][8]\\d{1}"));
        }
    }

    @Test
    public void testInvalidSsn() {
        final Faker f = new Faker(new Locale("en-ZA"));
        for (int i = 0; i < 100; i++) {
            Assert.assertThat(f.idNumber().invalid(), matchesRegularExpression("\\d{10}[01][8]\\d{1}"));
        }
    }
}