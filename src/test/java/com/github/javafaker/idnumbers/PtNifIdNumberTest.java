package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PtNifIdNumberTest {

	private static final int CONSTANT_SEED_VALUE = 10;
	private Faker faker;

	@Before
	public void before() {
		final Random random = new Random(CONSTANT_SEED_VALUE);
		faker = new Faker(new Locale("pt"), random);
	}

	@Test
	public void testInValid() {
		PtNifIdNumber idNumber = new PtNifIdNumber();
		Assert.assertEquals("304867688", idNumber.getInvalid(faker));
	}

	@Test
	public void testValid() {
	    PtNifIdNumber idNumber = new PtNifIdNumber();
		Assert.assertEquals("275700623", idNumber.getValid(faker));
	}

	@Test
	public void testValidWithFaker() {
		PtNifIdNumber idNumber = new PtNifIdNumber();
		Assert.assertEquals("275700623", faker.idNumber().valid());
	}

}
