package com.github.javafaker;

import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class StockTest extends AbstractFakerTest {


	@Test
	public void testNasdaq() {
		assert(faker.stock().nsdqSymbol() != "");
	}
	
	@Test
	public void testNYSE() {
		assert(faker.stock().nyseSymbol() != "");
	}

}
