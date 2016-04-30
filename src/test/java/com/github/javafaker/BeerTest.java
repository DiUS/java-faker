package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class BeerTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void testName() {
       assertThat(faker.beer().name(), matchesRegularExpression("[\\p{L}'()\\., 0-9-’’]+"));
    }

    @Test
    public void testStyle() {
        assertThat(faker.beer().style(), matchesRegularExpression("[A-Za-z'() 0-9-]+"));
    }

    @Test
    public void testHop() {
        assertThat(faker.beer().hop(), matchesRegularExpression("[A-Za-z'’()\\. 0-9-]+"));
    }

    @Test
    public void testMalt() {
        assertThat(faker.beer().malt(), matchesRegularExpression("[A-Za-z'() 0-9-]+"));
    }

    @Test
    public void testYeast() {
        assertThat(faker.beer().yeast(), matchesRegularExpression("[\\p{L}'() 0-9-]+"));
    }
}
