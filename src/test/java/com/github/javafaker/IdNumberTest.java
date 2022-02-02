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

    /**
     * Test whether generated valid Mexican ssn is valid.
     */
    @Test
    public void testValidMexicanSsn() {
        final Faker f = new Faker(new Locale("es_MX"));
        for (int i = 0; i < 100; i++) {
            assertThat(f.idNumber().valid(), matchesRegularExpression("[A-Z][A-Z][A-Z][A-Z]\\d{2}[0-1]\\d[0-3]\\d[HM]" +
                                        "[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z,0-9]\\d{1}"));
        }
    }

    /**
     * Test whether generated invalid Mexican ssn is invalid.
     */
    @Test
    public void testInvalidMexicanSsn() {
        final Faker f = new Faker(new Locale("es_MX"));
        for (int i = 0; i < 100; i++) {
            assertThat(f.idNumber().valid(), matchesRegularExpression("\\w[AEIOUaeiou]\\w{2}\\d{2}[0-1]\\d[0-3]\\d[HMhm]\\w{7}"));
        }
    }
}
