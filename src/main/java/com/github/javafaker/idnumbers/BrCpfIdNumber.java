package com.github.javafaker.idnumbers;

import org.apache.commons.lang3.ArrayUtils;

import com.github.javafaker.Faker;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BrCpfIdNumber {

	private static final int INPUT_DIGITS_LENGTH = 9;

	private static final int VERIFIER_DIGITS_LENGTH = 2;

	private static final String CPF_FORMAT_PATTERN = "###.###.###-##";

	public String getValidFormattedCpf(Faker faker) {

		int[] cpfDigits = retrieveCpfDigits(faker);

		return convertAsFormattedString(cpfDigits);
	}

	public String getValidUnformattedCpf(Faker faker) {

		int[] cpfDigits = retrieveCpfDigits(faker);

		return convertAsUnformattedString(cpfDigits);
	}

	public String getInvalidFormattedCpf(Faker faker) {

		int[] cpfDigits = retrieveInvalidCpfDigits(faker);

		return convertAsFormattedString(cpfDigits);
	}

	public String getInvalidUnformattedCpf(Faker faker) {

		int[] cpfDigits = retrieveInvalidCpfDigits(faker);

		return convertAsUnformattedString(cpfDigits);
	}

	private int[] retrieveCpfDigits(Faker faker) {
		int[] inputDigits = createInputDigits(faker);

		int[] verifierDigits = calculateVerifierDigits(inputDigits);

		return organizeCpfDigits(inputDigits, verifierDigits);
	}

	private int[] retrieveInvalidCpfDigits(Faker faker) {
		int[] inputDigits = createInputDigits(faker);

		int[] verifierDigits = calculateInvalidVerifierDigits(inputDigits, faker);

		return organizeCpfDigits(inputDigits, verifierDigits);
	}

	private int[] calculateInvalidVerifierDigits(int[] inputDigits, Faker faker) {
		int[] verifierDigits = calculateVerifierDigits(inputDigits);

		for (int index = 0; index < verifierDigits.length; index++) {
			verifierDigits[index] = retrieveRandomIntDifferentFrom(verifierDigits[index], faker);
		}

		return verifierDigits;
	}

	private int retrieveRandomIntDifferentFrom(int integer, Faker faker) {
		int result;

		do {
			result = retrieveRandomDigit(faker);
		} while (result == integer);

		return result;
	}

	private String convertAsFormattedString(int[] cpfDigits) {
		String cpf = CPF_FORMAT_PATTERN;

		for (int cpfDigit : cpfDigits) {
			cpf = cpf.replaceFirst("#", Integer.toString(cpfDigit));
		}
		return cpf;
	}

	private String convertAsUnformattedString(int[] cpfDigits) {
		StringBuilder cpfStringBuilder = new StringBuilder();

		for (int cpfDigit : cpfDigits) {
			cpfStringBuilder.append(cpfDigit);
		}

		return cpfStringBuilder.toString();
	}

	private int[] organizeCpfDigits(int[] inputDigits, int[] verifierDigits) {

		ArrayUtils.reverse(inputDigits);
		return ArrayUtils.addAll(inputDigits, verifierDigits);
	}

	protected int[] calculateVerifierDigits(int[] inputDigits) {
		int[] verifierDigits = new int[VERIFIER_DIGITS_LENGTH];

		for (int index = 0; index < inputDigits.length; index++) {
			verifierDigits[0] += inputDigits[index] * (9 - (index % 10));
			verifierDigits[1] += inputDigits[index] * (9 - ((index + 1) % 10));
		}

		verifierDigits[0] = (verifierDigits[0] % 11) % 10;

		verifierDigits[1] += verifierDigits[0] * 9;
		verifierDigits[1] = (verifierDigits[1] % 11) % 10;

		return verifierDigits;
	}

	protected boolean validateCPF(int[] cpf){
		int[] firtNineNumbers = Arrays.copyOfRange(cpf,0, 8);
		int[] lastTwoNumbers = Arrays.copyOfRange(cpf,9, 10);
		int[] expectedLastTwoNumbers = calculateVerifierDigits(firtNineNumbers);
		return expectedLastTwoNumbers[0] == lastTwoNumbers[0] &&
				expectedLastTwoNumbers[1] == lastTwoNumbers[1];
	}

	private int[] createInputDigits(Faker faker) {
		int[] inputDigits = new int[INPUT_DIGITS_LENGTH];
		for (int index = 0; index < inputDigits.length; index++) {
			inputDigits[index] = retrieveRandomDigit(faker);
		}
		return inputDigits;
	}

	private int retrieveRandomDigit(Faker faker) {
		return faker.random().nextInt(10);
	}
}
