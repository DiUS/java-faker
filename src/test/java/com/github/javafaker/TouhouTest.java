package com.github.javafaker;

import static org.junit.Assert.*;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;

public class TouhouTest extends AbstractFakerTest {

	@Test
	public void testCharacterName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testCharacterFirstName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testCharacterLastName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testTrackName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testGameName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

}
