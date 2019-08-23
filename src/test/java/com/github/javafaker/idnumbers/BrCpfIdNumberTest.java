package com.github.javafaker.idnumbers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.AbstractFakerTest;

public class BrCpfIdNumberTest extends AbstractFakerTest {

	private BrCpfIdNumber brCpfIdNumber;

	@Before
	public void setUp() {
		brCpfIdNumber = new BrCpfIdNumber();
	}

	@Test
	public void testGetValidFormattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getValidFormattedCpf(faker);
		assertTrue(BrCpfIdNumber.FORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetValidUnformattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getValidUnformattedCpf(faker);
		assertTrue(BrCpfIdNumber.UNFORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetInvalidFormattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getInvalidFormattedCpf(faker);
		assertTrue(BrCpfIdNumber.FORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetInvalidUnformattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getInvalidUnformattedCpf(faker);
		assertTrue(BrCpfIdNumber.UNFORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testIsValidShouldReturnTrueForValidFormattedCpfStartingWithZero() throws Exception {
		String cpf = "065.154.070-41";
		assertTrue(brCpfIdNumber.isValid(cpf));
	}
	
	@Test
	public void testIsValidShouldReturnTrueForValidUnformattedCpfStartingWithZero() throws Exception {
		String cpf = "04893747002";
		assertTrue(brCpfIdNumber.isValid(cpf));
	}
	
	@Test
	public void testIsValidShouldReturnFalseForInvalidFormattedCpfStartingWithZero() throws Exception {
		String cpf = "099.972.740-59";
		assertFalse(brCpfIdNumber.isValid(cpf));
	}
	
	@Test
	public void testIsValidShouldReturnFalseForInvalidUnformattedCpfStartingWithZero() throws Exception {
		String cpf = "03360109025";
		assertFalse(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnTrueForValidFormattedCpfStartingWithGreaterThanZero() throws Exception {
		String cpf = "919.766.290-98";
		assertTrue(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnTrueForValidUnformattedCpfStartingWithGreaterThanZero() throws Exception {
		String cpf = "49764225004";
		assertTrue(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void debugDoTesteDeCima() throws Exception{
		int[] cpf = { 4, 9, 6, 4, 2, 2, 5, 0 };
		int[] expectedValues = {0, 4};

		int[] actualValues = brCpfIdNumber.calculateVerifierDigits(cpf);
		assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	public void testIsValidShouldReturnFalseForInvalidFormattedCpfStartingWithGreaterThanZero() throws Exception {
		String cpf = "144.970.180-88";
		assertFalse(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnFalseForInvalidUnformattedCpfStartingWithGreaterThanZero() throws Exception {
		String cpf = "56803652090";
		assertFalse(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testGetValidFormattedCpfShouldReturnValidFormattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getValidFormattedCpf(faker);
		assertTrue(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testGetInvalidFormattedCpfShouldReturnInvalidFormattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getInvalidFormattedCpf(faker);
		assertFalse(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testGetValidUnformattedCpfShouldReturnValidFormattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getValidUnformattedCpf(faker);
		assertTrue(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testGetInvalidUnformattedCpfShouldReturnInvalidFormattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getInvalidUnformattedCpf(faker);
		assertFalse(brCpfIdNumber.isValid(cpf));
	}
}
