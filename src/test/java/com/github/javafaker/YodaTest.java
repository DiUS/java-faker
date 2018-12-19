package com.github.javafaker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.core.IsNot.not;

import org.junit.Test;

/**
 * @author Luka Obradovic (luka@vast.com)
 */
public class YodaTest extends AbstractFakerTest {

    @Test
    public void quote() {
        assertThat(faker.yoda().quote(), not(emptyOrNullString()));
    }
}
