package com.github.javafaker.idnumbers;

import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.AbstractFakerTest;

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

}
