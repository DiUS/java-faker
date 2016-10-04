package com.github.javafaker;

import org.apache.commons.validator.routines.EmailValidator;
import org.junit.Before;
import org.junit.Test;

import static com.github.javafaker.matchers.CountOfCharactersMatcher.countOf;
import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static java.lang.Integer.parseInt;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class InternetTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testEmailAddress() {
        String emailAddress = faker.internet().emailAddress();
        assertThat(EmailValidator.getInstance().isValid(emailAddress), is(true));
    }

    @Test
    public void testEmailAddressWithLocalPartParameter() {
        String emailAddress = faker.internet().emailAddress("john");
        assertThat(emailAddress, startsWith("john@"));
        assertThat(EmailValidator.getInstance().isValid(emailAddress), is(true));
    }

    @Test
    public void testSafeEmailAddress() {
        String emailAddress = faker.internet().safeEmailAddress();
        assertThat(EmailValidator.getInstance().isValid(emailAddress), is(true));
    }

    @Test
    public void testSafeEmailAddressWithLocalPartParameter() {
        String emailAddress = faker.internet().emailAddress("john");
        assertThat(emailAddress, startsWith("john@"));
        assertThat(EmailValidator.getInstance().isValid(emailAddress), is(true));
    }

    @Test
    public void testUrl() {
        assertThat(faker.internet().url(), matchesRegularExpression("www\\.(\\w|-)+\\.\\w+"));
    }

    @Test
    public void testAvatar() {
        assertThat(faker.internet().avatar(), matchesRegularExpression("http.*/[^/]+/128.jpg$"));
    }

    @Test
    public void testImage() {
        String imageUrl = faker.internet().image();
        assertThat(imageUrl, matchesRegularExpression("^http:\\/\\/lorempixel\\.com(/g)?/\\d{1,4}/\\d{1,4}/\\w+/$"));
    }

    @Test
    public void testDomainName() {
        assertThat(faker.internet().domainName(), matchesRegularExpression("[a-z]+\\.\\w{2,4}"));
    }

    @Test
    public void testDomainWord() {
        assertThat(faker.internet().domainWord(), matchesRegularExpression("[a-z]+"));
    }

    @Test
    public void testDomainSuffix() {
      assertThat(faker.internet().domainSuffix(), matchesRegularExpression("\\w{2,4}"));
    }

    @Test
    public void testImageWithExplicitParams() {
        String imageUrl = faker.internet().image(800, 600, false, "bugs");
        assertThat(imageUrl, matchesRegularExpression("^http:\\/\\/lorempixel\\.com/800/600/\\w+/bugs$"));
    }

    @Test
    public void testPassword() {
        assertThat(faker.internet().password(), matchesRegularExpression("[a-z\\d]{8,16}"));
    }

    @Test
    public void testPasswordMinLengthMaxLength() {
        assertThat(faker.internet().password(10, 25), matchesRegularExpression("[a-z\\d]{10,25}"));
    }

    @Test
    public void testPasswordMinLengthMaxLengthIncludeUpperCase() {
        assertThat(faker.internet().password(1, 2, false), matchesRegularExpression("[a-z\\d]{1,2}"));
        assertThat(faker.internet().password(10, 25, true), matchesRegularExpression("[a-zA-Z\\d]{10,25}"));
    }

    @Test
    public void testPasswordMinLengthMaxLengthIncludeUpperCaseIncludeSpecial() {
        assertThat(faker.internet().password(10, 25, false, false), matchesRegularExpression("[a-z\\d]{10,25}"));
        assertThat(faker.internet().password(10, 25, false, true), matchesRegularExpression("[a-z\\d!@#$%^&*]{10,25}"));
        assertThat(faker.internet().password(10, 25, true, true), matchesRegularExpression("[a-zA-Z\\d!@#$%^&*]{10,25}"));
    }

    @Test
    public void testMacAddress() {
        assertThat(faker.internet().macAddress(), countOf(':', is(5)));
        assertThat(faker.internet().macAddress(""), countOf(':', is(5)));

        assertThat(faker.internet().macAddress("fa:fa:fa"), startsWith("fa:fa:fa"));
        assertThat(faker.internet().macAddress("fa:fa:fa"), countOf(':', is(5)));

        assertThat(faker.internet().macAddress("01:02"), startsWith("01:02"));
        assertThat(faker.internet().macAddress("01:02"), countOf(':', is(5)));

        // loop through 1000 times just to 'run it through the wringer'
        for (int i=0; i<1000;i++) {
            assertThat(
              "Is valid mac format",
              faker.internet().macAddress(),
              matchesRegularExpression("[0-9a-fA-F]{2}(\\:([0-9a-fA-F]{1,4})){5}"));
        }
    }


    @Test
    public void testIpV4Address() {
        assertThat(faker.internet().ipV4Address(), countOf('.', is(3)));
        for (int i = 0; i < 100; i++) {
            final String[] octets = faker.internet().ipV4Address().split("\\.");
            assertThat("first octet is 1-255", parseInt(octets[0]),
                    both(greaterThan(0)).and(lessThanOrEqualTo(255)));
            assertThat("second octet is 0-255", parseInt(octets[1]),
                    both(greaterThanOrEqualTo(0)).and(lessThanOrEqualTo(255)));
            assertThat("second octet is 0-255", parseInt(octets[2]),
                    both(greaterThanOrEqualTo(0)).and(lessThanOrEqualTo(255)));
            assertThat("second octet is 0-255", parseInt(octets[3]),
                    both(greaterThanOrEqualTo(0)).and(lessThanOrEqualTo(255)));
        }
    }

    @Test
    public void testIpV4Cidr() {
        assertThat(faker.internet().ipV4Cidr(), countOf('.', is(3)));
        assertThat(faker.internet().ipV4Cidr(), countOf('/', is(1)));
        
        for (int i = 0; i < 1000; i++) {
            assertThat(parseInt(faker.internet().ipV4Cidr().split("\\/")[1]),
                    both(greaterThanOrEqualTo(1)).and(lessThan(32)));
        }
    }

    @Test
    public void testPrivateIpV4Address() {
        String ten_dot = "^10\\..+";
        String one_two_seven = "^127\\..+";
        String one_six_nine = "^169\\.254\\..+";
        String one_nine_two = "^192\\.168\\..+";
        String one_seven_two = "^172\\.(16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31)\\..+";
        for (int i = 0; i < 1000; i++) {
            String addr = faker.internet().privateIpV4Address();
            
            assertThat(addr, anyOf(matchesRegularExpression(ten_dot),
                    matchesRegularExpression(one_two_seven),
                    matchesRegularExpression(one_six_nine),
                    matchesRegularExpression(one_nine_two),
                    matchesRegularExpression(one_seven_two)));
        }
    }

    @Test
    public void testPublicIpV4Address() {
        String ten_dot = "^10\\.";
        String one_two_seven = "^127\\.";
        String one_six_nine = "^169\\.254";
        String one_nine_two = "^192\\.168\\.";
        String one_seven_two = "^172\\.(16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31)\\.";
        for (int i = 0; i < 1000; i++) {
            String addr = faker.internet().publicIpV4Address();
            assertThat(addr.matches(ten_dot),is(false));
            assertThat(addr.matches(one_two_seven),is(false));
            assertThat(addr.matches(one_six_nine),is(false));
            assertThat(addr.matches(one_nine_two),is(false));
            assertThat(addr.matches(one_seven_two),is(false));
        }
    }

    @Test
    public void testIpV6() {
        assertThat(faker.internet().ipV6Address(), countOf(':', is(7)));
        
        for (int i = 0; i < 1000; i++) {
            assertThat(
                    "Is valid ipv6 format",
                    faker.internet().ipV6Address(),
                    matchesRegularExpression("[0-9a-fA-F]{1,4}(\\:([0-9a-fA-F]{1,4})){1,7}"));
        }
    }

    @Test
    public void testIpV6Cidr() {
        assertThat(faker.internet().ipV6Cidr(), countOf(':', is(7)));
        assertThat(faker.internet().ipV6Cidr(), countOf('/', is(1)));

        for (int i = 0; i < 1000; i++) {
            assertThat(parseInt(faker.internet().ipV6Cidr().split("\\/")[1]),
                    both(greaterThanOrEqualTo(1)).and(lessThan(128)));
        }
    }
}
