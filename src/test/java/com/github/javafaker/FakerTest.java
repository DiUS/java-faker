package com.github.javafaker;

import org.junit.Before;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class FakerTest {

    private Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

    @Test
    public void bothifyShouldGenerateLettersAndNumbers() {
        assertThat(faker.bothify("????##@gmail.com"), matchesRegularExpression("\\w{4}\\d{2}@gmail.com"));
    }

    @Test
    public void letterifyShouldGenerateLetters() {
        assertThat(faker.bothify("????"), matchesRegularExpression("\\w{4}"));
    }

    @Test
    public void letterifyShouldLeaveNonSpecialCharactersAlone() {
        assertThat(faker.bothify("ABC????DEF"), matchesRegularExpression("ABC\\w{4}DEF"));
    }

    @Test
    public void numerifyShouldGenerateNumbers() {
        assertThat(faker.numerify("####"), matchesRegularExpression("\\d{4}"));
    }

    @Test
    public void numerifyShouldLeaveNonSpecialCharactersAlone() {
        assertThat(faker.numerify("####123"), matchesRegularExpression("\\d{4}123"));
    }
}
