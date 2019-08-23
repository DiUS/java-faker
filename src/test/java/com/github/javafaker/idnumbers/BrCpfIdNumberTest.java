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
	public void testValidCpfStartingWithZero() throws Exception {
		int[] cpf = { 0, 8, 5, 3, 2, 5, 9, 4, 0 };
		int[] expectedValues = {2, 6};
		
		int[] actualValues = brCpfIdNumber.calculateVerifierDigits(cpf);
		
		assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	public void testValidCpfStartingWithGreaterThanZero() throws Exception {
		int[] cpf = { 9, 3, 7, 8, 5, 1, 4, 8, 0 };
		int[] expectedValues = {4, 6};
		
		int[] actualValues = brCpfIdNumber.calculateVerifierDigits(cpf);
		
		assertArrayEquals(expectedValues, actualValues);
	}

	@Test
	public void testVerifyValidCpfStartingWithZero() throws Exception {
		int[] cpf = { 0, 8, 5, 3, 2, 5, 9, 4, 0, 2, 6 };
		assertTrue(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testVerifyValidCpfStartingWithGreaterThanZero() throws Exception {
		int[] cpf = { 7, 9, 3, 1, 3, 9, 8, 4, 0, 8, 3 };
		assertTrue(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testVerifyInvalidCpfStartingWithZero() throws Exception {
		int[] cpf = { 0, 0, 4, 6, 6, 7, 6, 4, 0, 6, 5 };
		assertFalse(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testVerifyInvalidCpfStartingWithGreaterThanZero() throws Exception {
		int[] cpf = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1 };
		assertFalse(brCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnTrueForValidFormattedCpf() throws Exception {
		String cpf = "011.063.470-56";
		assertTrue(brCpfIdNumber.isValid(cpf));
	}

}
