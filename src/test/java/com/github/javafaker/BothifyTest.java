package com.github.javafaker;

import org.junit.Assert;
import org.junit.Test;

public class BothifyTest {
	private Faker faker = new Faker();

    @Test
    public void shouldGenerateLettersAndNumbers() {
    	Assert.assertTrue(faker.bothify("????##@gmail.com").matches("\\w{4}\\d{2}@gmail.com"));
    }
}
