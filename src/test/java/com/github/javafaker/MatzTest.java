package com.github.javafaker;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class MatzTest extends AbstractFakerTest {

    @Test
    public void quote() {
        assertThat(faker.matz().quote(), not(is(emptyOrNullString())));
    }
}
