package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import org.junit.Test;

public class PhoneNumberTest extends AbstractFakerTest {

    @Test
    public void testCellPhone_enUS() {
        final Faker f = new Faker(Locale.US);
        assertThat(f.phoneNumber().cellPhone(), matchesRegularExpression("\\(?\\d+\\)?([- .]\\d+){1,3}"));
    }

    @Test
    public void testPhone_esMx() {
        final Faker f = new Faker(new Locale("es_MX"));
        for (int i=0;i<100;i++) {
            assertThat(f.phoneNumber().cellPhone(), matchesRegularExpression("(044 )?\\(?\\d+\\)?([- .]\\d+){1,3}"));
            assertThat(f.phoneNumber().phoneNumber(), matchesRegularExpression("\\(?\\d+\\)?([- .]\\d+){1,3}"));
        }
    }

    @Test
    public void testPhone_enZA() {
        final Faker f = new Faker(new Locale("en_ZA"));
        for (int i=0;i<100;i++) {
            assertThat(f.phoneNumber().phoneNumber(), matchesRegularExpression("\\(?\\d+\\)?([- .]\\d+){1,3}"));
        }
    }

    @Test
    public void testCellPhone() {
        assertThat(faker.phoneNumber().cellPhone(), matchesRegularExpression("\\(?\\d+\\)?([- .]\\d+){1,3}"));
    }

    @Test
    public void testPhoneNumber() {
        assertThat(faker.phoneNumber().phoneNumber(), matchesRegularExpression("\\(?\\d+\\)?([- .]x?\\d+){1,5}"));
    }
}
