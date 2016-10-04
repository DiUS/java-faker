package com.github.javafaker;

import org.junit.Before;

public class AbstractFakerTest {
    protected Faker faker;

    @Before
    public void before() {
        faker = new Faker();
    }

}
