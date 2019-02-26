package com.github.javafaker;

import org.junit.Test;

import java.util.Locale;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class IdNumberTest extends AbstractFakerTest {

    @Test
    public void testValid() {
        assertThat(faker.idNumber().valid(), matchesRegularExpression("[0-8]\\d{2}-\\d{2}-\\d{4}"));
    }

    @Test
    public void testInvalid() {
        assertThat(faker.idNumber().invalid(), matchesRegularExpression("[0-9]\\d{2}-\\d{2}-\\d{4}"));
    }

    @Test
    public void testSsnValid() {
        assertThat(faker.idNumber().valid(), matchesRegularExpression("[0-8]\\d{2}-\\d{2}-\\d{4}"));
    }

    @Test
    public void testValidSwedishSsn() {
        final Faker f = new Faker(new Locale("sv_SE"));
        for (int i = 0; i < 100; i++) {
            assertThat(f.idNumber().valid(), matchesRegularExpression("\\d{6}[-+]\\d{4}"));
        }
    }

    @Test
    public void testInvalidSwedishSsn() {
        final Faker f = new Faker(new Locale("sv_SE"));
        for (int i = 0; i < 100; i++) {
            assertThat(f.idNumber().invalid(), matchesRegularExpression("\\d{6}[-+]\\d{4}"));
        }
    }

    @Test
    public void testDni() {
        final Faker f = new Faker();
        assertThat(f.idNumber().dni(), matchesRegularExpression("^[0-9]{8,8}-[A-Za-z]$"));
    }

    @Test
    public void testDniBasedInLocale() {
        final Faker f = new Faker(new Locale("es"));
        for (int i = 0; i < 100; i++) {
            assertThat(f.idNumber().valid(), matchesRegularExpression("^[0-9]{8,8}-[A-Za-z]$"));
        }
    }

    @Test
    public void testInvalidDniBasedInLocale() {
        final Faker f = new Faker(new Locale("es"));
        for (int i = 0; i < 100; i++) {
            assertThat(f.idNumber().invalid(), matchesRegularExpression("^[0-9]{8,8}-[A-Za-z]$"));
        }
    }
}
