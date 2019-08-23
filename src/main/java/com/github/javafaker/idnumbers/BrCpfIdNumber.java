package com.github.javafaker.idnumbers;

import org.apache.commons.lang3.ArrayUtils;

import com.github.javafaker.Faker;

public class BrCpfIdNumber {

	private static final int INPUT_DIGITS_LENGTH = 9;
	
	private static final int VERIFIER_DIGITS_LENGTH = 2;
	
	private static final String CPF_FORMAT_PATTERN = "###.###.###-##";

	public String getValidCpf(Faker faker) {

		int[] inputDigits = createInputDigits(faker);

		int[] verifierDigits = calculateVerifierDigits(inputDigits);

		int[] cpfDigits = organizeCpfDigits(inputDigits, verifierDigits);

		return formatAsString(cpfDigits);
	}

	private String formatAsString(int[] cpfDigits) {
		String cpf = CPF_FORMAT_PATTERN;

		for (int cpfDigit : cpfDigits) {
			cpf = cpf.replaceFirst("#", Integer.toString(cpfDigit));
		}
		return cpf;
	}

	private int[] organizeCpfDigits(int[] inputDigits, int[] verifierDigits) {

		ArrayUtils.reverse(inputDigits);
		return ArrayUtils.addAll(inputDigits, verifierDigits);
	}

	private int[] calculateVerifierDigits(int[] inputDigits) {
		int[] verifierDigits = new int[VERIFIER_DIGITS_LENGTH];

		for (int index = 0; index < INPUT_DIGITS_LENGTH; index++) {
			verifierDigits[0] += inputDigits[index] * (9 - (index % 10));
			verifierDigits[1] += inputDigits[index] * (9 - ((index + 1) % 10));
		}

		verifierDigits[0] = (verifierDigits[0] % 11) % 10;

		verifierDigits[1] += verifierDigits[0] * 9;
		verifierDigits[1] = (verifierDigits[1] % 11) % 10;

		return verifierDigits;
	}

	private int[] createInputDigits(Faker faker) {
		int[] inputDigits = new int[INPUT_DIGITS_LENGTH];
		for (int index = 0; index < INPUT_DIGITS_LENGTH; index++) {
			inputDigits[index] = faker.random().nextInt(10);
		}
		return inputDigits;
	}
}
