package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class Battlefield1Test extends AbstractFakerTest {

    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/711
    @Test
    public void classes() {
        assertThat(faker.battlefield1().classes(), matchesRegularExpression("[A-Za-z ]+"));
    }

    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/711
    @Test
    public void weapon() {
        assertThat(faker.battlefield1().weapon(), matchesRegularExpression("[A-Za-z0-9,\\.\\(\\)\\/ ]+"));
    }

    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/711
    @Test
    public void vehicle() {
        assertThat(faker.battlefield1().vehicle(), matchesRegularExpression("[A-Za-z0-9,\\-\\.\\(\\)\\/ ]+"));
    }

    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/711
    @Test
    public void map() {
        assertThat(faker.battlefield1().map(), matchesRegularExpression("[A-Za-z0-9,\\-\\.\\(\\)\\' ]+"));
    }

    //CS304 (manually written) Issue link: https://github.com/DiUS/java-faker/issues/711
    @Test
    public void faction() {
        assertThat(faker.battlefield1().faction(), matchesRegularExpression("[A-Za-z,\\- ]+"));
    }
}
