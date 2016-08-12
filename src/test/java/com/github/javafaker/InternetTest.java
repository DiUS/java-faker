package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.*;

import org.apache.commons.validator.routines.EmailValidator;

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
        assertThat(imageUrl, matchesRegularExpression("^https:\\/\\/ssl\\.webpack\\.de/lorempixel\\.com(/g)?/\\d{1,4}/\\d{1,4}/\\w+/$"));
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
        assertThat(imageUrl, matchesRegularExpression("^https:\\/\\/ssl\\.webpack\\.de/lorempixel\\.com/800/600/\\w+/bugs$"));
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

}
