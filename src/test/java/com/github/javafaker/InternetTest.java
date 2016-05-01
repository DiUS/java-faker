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

}
