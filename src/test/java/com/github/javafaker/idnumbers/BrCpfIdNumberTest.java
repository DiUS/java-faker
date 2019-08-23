package com.github.javafaker.idnumbers;

import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.AbstractFakerTest;

import static org.junit.Assert.*;

public class BrCpfIdNumberTest extends AbstractFakerTest {

	private static final String FORMATTED_CPF_REGEX_PATTERN = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

	private static final Pattern FORMATTED_CPF_PATTERN = Pattern.compile(FORMATTED_CPF_REGEX_PATTERN);

	private static final String UNFORMATTED_CPF_REGEX_PATTERN = "^\\d{11}$";

	private static final Pattern UNFORMATTED_CPF_PATTERN = Pattern.compile(UNFORMATTED_CPF_REGEX_PATTERN);

	private BrCpfIdNumber brCpfIdNumber;

	@Before
	public void setUp() {
		brCpfIdNumber = new BrCpfIdNumber();
	}

	@Test
	public void testGetValidFormattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getValidFormattedCpf(faker);
		assertTrue(FORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetValidUnformattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getValidUnformattedCpf(faker);
		assertTrue(UNFORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetInvalidFormattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getInvalidFormattedCpf(faker);
		assertTrue(FORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetInvalidUnformattedCpf() throws Exception {
		String cpf = brCpfIdNumber.getInvalidUnformattedCpf(faker);
		assertTrue(UNFORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testValidCpfStartingWithZero() throws Exception {
		int[] cpf = {0,8,5,3,2,5,9,4,0};
		assertSame(brCpfIdNumber.calculateVerifierDigits(cpf)[0], 2);
		assertSame(brCpfIdNumber.calculateVerifierDigits(cpf)[1], 6);
	}

	@Test
	public void testValidCpfStartingWithGreaterThanZero() throws Exception {
		int[] cpf = {9,3,7,8,5,1,4,8,0};
		assertSame(brCpfIdNumber.calculateVerifierDigits(cpf)[0], 4);
		assertSame(brCpfIdNumber.calculateVerifierDigits(cpf)[1], 6);
	}

	@Test
	public void testVerifyValidCpfStartingWithZero() throws Exception {
		int[] cpf = {0,8,5,3,2,5,9,4,0,2,6};
		assertEquals(brCpfIdNumber.validateCPF(cpf),true);
	}

	@Test
	public void testVerifyValidCpfStartingWithGreaterThanZero() throws Exception {
		int[] cpf = {7,9,3,1,3,9,8,4,0,8,3};
		assertEquals(brCpfIdNumber.validateCPF(cpf),true);
	}

	@Test
	public void testVerifyInvalidCpfStartingWithZero() throws Exception {
		int[] cpf = {0,0,4,6,6,7,6,4,0,6,5};
		assertEquals(brCpfIdNumber.validateCPF(cpf),false);
	}

	@Test
	public void testVerifyInvalidCpfStartingWithGreaterThanZero() throws Exception {
		int[] cpf = {1,2,3,4,5,6,7,8,9,0,1};
		assertEquals(brCpfIdNumber.validateCPF(cpf),false);
	}
}
