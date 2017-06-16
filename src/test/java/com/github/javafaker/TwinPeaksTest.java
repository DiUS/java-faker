package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class TwinPeaksTest extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.twinPeaks().character(), matchesRegularExpression("^([\\w']+ ?){2,}$"));
    }

    @Test
    public void location() {
        assertThat(faker.twinPeaks().location(), matchesRegularExpression("^[A-Za-z0-9'&,\\- ]+$"));
    }

    @Test
    public void quote() {
        assertThat(faker.twinPeaks().quote(), not(isEmptyOrNullString()));
    }
}
