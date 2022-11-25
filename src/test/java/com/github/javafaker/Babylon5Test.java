package com.github.javafaker;

import static com.github.javafaker.matchers.IsStringWithContents.isStringWithContents;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class Babylon5Test extends AbstractFakerTest {

    @Test
    public void character() {
        assertThat(faker.babylon5().character(), isStringWithContents());
    }

    @Test
    public void quote() {
        assertThat(faker.babylon5().quote(), isStringWithContents());
    }
    
}