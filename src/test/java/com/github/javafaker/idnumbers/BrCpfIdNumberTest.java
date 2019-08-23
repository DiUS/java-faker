package com.github.javafaker.idnumbers;

import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import com.github.javafaker.AbstractFakerTest;

public class BrCpfIdNumberTest extends AbstractFakerTest {

	private static final String CPF_REGEX_PATTERN = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

	private static final Pattern CPF_PATTERN = Pattern.compile(CPF_REGEX_PATTERN);

	private BrCpfIdNumber brCpfIdNumber;

	@Before
	public void setUp() {
		brCpfIdNumber = new BrCpfIdNumber();
	}

	@Test
	public void testGetValidCpf() throws Exception {
		String cpf = brCpfIdNumber.getValidCpf(faker);
		assertTrue(CPF_PATTERN.matcher(cpf).matches());
	}

}
