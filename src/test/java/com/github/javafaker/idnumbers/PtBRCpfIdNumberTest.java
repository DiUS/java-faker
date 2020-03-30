package com.github.javafaker.idnumbers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.AbstractFakerTest;

public class PtBRCpfIdNumberTest extends AbstractFakerTest {

	private PtBRCpfIdNumber ptBRCpfIdNumber;

	@Before
	public void setUp() {
		ptBRCpfIdNumber = new PtBRCpfIdNumber();
	}

	@Test
	public void testGetValidFormattedCpf() throws Exception {
		String cpf = ptBRCpfIdNumber.getValidFormattedCpf(faker);
		assertTrue(PtBRCpfIdNumber.FORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetValidUnformattedCpf() throws Exception {
		String cpf = ptBRCpfIdNumber.getValidUnformattedCpf(faker);
		assertTrue(PtBRCpfIdNumber.UNFORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetInvalidFormattedCpf() throws Exception {
		String cpf = ptBRCpfIdNumber.getInvalidFormattedCpf(faker);
		assertTrue(PtBRCpfIdNumber.FORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testGetInvalidUnformattedCpf() throws Exception {
		String cpf = ptBRCpfIdNumber.getInvalidUnformattedCpf(faker);
		assertTrue(PtBRCpfIdNumber.UNFORMATTED_CPF_PATTERN.matcher(cpf).matches());
	}

	@Test
	public void testIsValidShouldReturnTrueForValidFormattedCpfStartingWithZero() throws Exception {
		String cpf = "065.154.070-41";
		assertTrue(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnTrueForValidUnformattedCpfStartingWithZero() throws Exception {
		String cpf = "04893747002";
		assertTrue(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnFalseForInvalidFormattedCpfStartingWithZero() throws Exception {
		String cpf = "099.972.740-59";
		assertFalse(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnFalseForInvalidUnformattedCpfStartingWithZero() throws Exception {
		String cpf = "03360109025";
		assertFalse(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnTrueForValidFormattedCpfStartingWithGreaterThanZero() throws Exception {
		String cpf = "919.766.290-98";
		assertTrue(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnTrueForValidUnformattedCpfStartingWithGreaterThanZero() throws Exception {
		String cpf = "49764225004";
		assertTrue(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnFalseForInvalidFormattedCpfStartingWithGreaterThanZero() throws Exception {
		String cpf = "144.970.180-88";
		assertFalse(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testIsValidShouldReturnFalseForInvalidUnformattedCpfStartingWithGreaterThanZero() throws Exception {
		String cpf = "56803652090";
		assertFalse(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testGetValidFormattedCpfShouldReturnValidFormattedCpf() throws Exception {
		String cpf = ptBRCpfIdNumber.getValidFormattedCpf(faker);
		assertTrue(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testGetInvalidFormattedCpfShouldReturnInvalidFormattedCpf() throws Exception {
		String cpf = ptBRCpfIdNumber.getInvalidFormattedCpf(faker);
		assertFalse(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testGetValidUnformattedCpfShouldReturnValidFormattedCpf() throws Exception {
		String cpf = ptBRCpfIdNumber.getValidUnformattedCpf(faker);
		assertTrue(ptBRCpfIdNumber.isValid(cpf));
	}

	@Test
	public void testGetInvalidUnformattedCpfShouldReturnInvalidFormattedCpf() throws Exception {
		String cpf = ptBRCpfIdNumber.getInvalidUnformattedCpf(faker);
		assertFalse(ptBRCpfIdNumber.isValid(cpf));
	}
}
