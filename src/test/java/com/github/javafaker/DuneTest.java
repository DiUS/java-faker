package com.github.javafaker;

import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class DuneTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.dune().character(), matchesRegularExpression("[A-Za-z '\\-\"]+"));
    }

    @Test
    public void title() {
        assertThat(faker.dune().title(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void planet() {
        assertThat(faker.dune().planet(), matchesRegularExpression("[A-Za-z ]+"));
    }

    @Test
    public void quote() {
        assertThat(faker.dune().quote(), matchesRegularExpression("\\P{Cc}+"));
    }

    @Test
    @Repeat(times = 10000)
    public void randomQuote() {
        assertThat(
                faker.dune().quote(faker.options().option(Dune.Quote.class)),
                matchesRegularExpression("\\P{Cc}+"));
    }

    @Test
    public void saying() {
        assertThat(faker.dune().saying(), matchesRegularExpression("\\P{Cc}+"));
    }

    @Test
    @Repeat(times = 10000)
    public void randomSaying() {
        assertThat(
                faker.dune().saying(faker.options().option(Dune.Saying.class)),
                matchesRegularExpression("\\P{Cc}+"));
    }

}
