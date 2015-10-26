package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.*;

public class InternetTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
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
    public void testImageWithExplicitParams() {
        String imageUrl = faker.internet().image(800, 600, false, "bugs");
        assertThat(imageUrl, matchesRegularExpression("^https:\\/\\/ssl\\.webpack\\.de/lorempixel\\.com/800/600/\\w+/bugs$"));
    }

}
