package com.github.javafaker;


import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class MbtiTest extends AbstractFakerTest{
    public void type() {
        assertThat(faker.mbti().type(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
        assertThat(faker.mbti().characteristic(), not(isEmptyOrNullString()));
    }
    @Test
    public void name() {
        assertThat(faker.mbti().name(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
        assertThat(faker.mbti().characteristic(), not(isEmptyOrNullString()));
    }
    @Test
    public void characteristic() {
        assertThat(faker.mbti().personage(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) '\"!?`;: ]+"));
        assertThat(faker.mbti().characteristic(), not(isEmptyOrNullString()));
    }
    @Test
    public void personage() {
        assertThat(faker.mbti().personage(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
        assertThat(faker.mbti().characteristic(), not(isEmptyOrNullString()));
    }
    @Test
    public void merit() {
        assertThat(faker.mbti().merit(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
        assertThat(faker.mbti().characteristic(), not(isEmptyOrNullString()));
    }
    @Test
    public void weakness() {
        assertThat(faker.mbti().weakness(), matchesRegularExpression("[A-Za-z,\\-\\.\\(\\) ]+"));
        assertThat(faker.mbti().characteristic(), not(isEmptyOrNullString()));
    }
}
