package com.github.javafaker;

import org.junit.Test;

import java.util.Locale;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

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
    public void testCellPhone() {
        assertThat(faker.phoneNumber().cellPhone(), matchesRegularExpression("\\(?\\d+\\)?([- .]\\d+){1,3}"));
    }

    @Test
    public void testPhoneNumber() {
        assertThat(faker.phoneNumber().phoneNumber(), matchesRegularExpression("\\(?\\d+\\)?([- .]x?\\d+){1,5}"));
    }

    @Test
    public void testPhoneNumberRegex1() {
        Faker faker = new Faker();
        String number = faker.phoneNumber().phoneNumber("[2-4]#####");
        assertThat(number, matchesRegularExpression("[2-4]\\d\\d\\d\\d\\d"));
    }

    @Test
    public void testPhoneNumberRegex2() {
        Faker faker = new Faker();
        String number = faker.phoneNumber().phoneNumber("[2-4][3-7]#####");
        assertThat(number, matchesRegularExpression("[2-4][3-7]\\d\\d\\d\\d\\d"));
    }

    @Test
    public void testCellPhoneRegex1() {
        Faker faker = new Faker();
        String number = faker.phoneNumber().cellPhone("[5-9]#-##-##");
        assertThat(number, matchesRegularExpression("[5-9]\\d-\\d\\d-\\d\\d"));
    }

    @Test
    public void testCellPhoneRegex2() {
        Faker faker = new Faker();
        String number = faker.phoneNumber().cellPhone("[3-7][7-9]-##-##");
        assertThat(number, matchesRegularExpression("[3-7][7-9]-\\d\\d-\\d\\d"));
    }


    @Test
    public void testExtension() {
        assertThat(faker.phoneNumber().extension(), matchesRegularExpression("\\d{4}"));
    }

    @Test
    public void testSubscriberNumber() {
        assertThat(faker.phoneNumber().subscriberNumber(), matchesRegularExpression("\\d{4}"));
    }

    @Test
    public void testSubscriberNumberWithLength() {
        assertThat(faker.phoneNumber().subscriberNumber(10), matchesRegularExpression("\\d{10}"));
    }
}
