package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.*;

import org.junit.Test;

public class MinecraftTest extends AbstractFakerTest {

	@Test
	public void testItemName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testTileName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testEntityName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testMonsterName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testAnimalName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

	@Test
	public void testTileItemName() {
		assertThat(faker.name().fullName(), matchesRegularExpression("([\\w']+\\.?( )?){2,4}"));
	}

}
