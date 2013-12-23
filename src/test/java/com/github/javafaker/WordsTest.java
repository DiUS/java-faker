package com.github.javafaker;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

public class WordsTest {
	private Faker faker = new Faker();
	
	@Test
	public void shouldGenerateWordsFromLorem() {
		assertThat(faker.words(10).get(0), notNullValue());
		assertThat(faker.words(10).get(9), notNullValue());
	}
}
