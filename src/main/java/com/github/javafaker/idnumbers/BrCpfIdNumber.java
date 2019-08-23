package com.github.javafaker.idnumbers;

import java.util.Arrays;
import java.util.regex.Pattern;

import org.apache.commons.lang3.ArrayUtils;

import com.github.javafaker.Faker;

/**
 * Generate both valid and invalid Brazilian CPF identification number.
 * 
 * For more information about CPF please visit
 * https://en.wikipedia.org/wiki/Cadastro_de_Pessoas_F%C3%ADsicas
 * 
 * The validation numbers algorithm was implemented following instructions
 * provided by the Brazilian portuguese Wikipedia page at
 * https://pt.wikipedia.org/wiki/Cadastro_de_pessoas_f%C3%ADsicas#Algoritmo
 * 
 * @author MarceloLeite2604
 * @author EullerLisowski
 *
 */
public class BrCpfIdNumber {

	private static final int INPUT_DIGITS_LENGTH = 9;

	private static final int VERIFIER_DIGITS_LENGTH = 2;

	private static final int CPF_DIGITS_LENGTH = INPUT_DIGITS_LENGTH + VERIFIER_DIGITS_LENGTH;

	private static final String CPF_FORMAT_PATTERN = "###.###.###-##";

	protected static final String FORMATTED_CPF_REGEX_PATTERN = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$";

	static final Pattern FORMATTED_CPF_PATTERN = Pattern.compile(FORMATTED_CPF_REGEX_PATTERN);

	protected static final String UNFORMATTED_CPF_REGEX_PATTERN = "^\\d{11}$";

	static final Pattern UNFORMATTED_CPF_PATTERN = Pattern.compile(UNFORMATTED_CPF_REGEX_PATTERN);

	public String getValidFormattedCpf(Faker faker) {

		int[] cpfDigits = createCpfDigits(faker);

		return convertAsFormattedString(cpfDigits);
	}

	public String getValidUnformattedCpf(Faker faker) {

		int[] cpfDigits = createCpfDigits(faker);

		return convertAsUnformattedString(cpfDigits);
	}

	public String getInvalidFormattedCpf(Faker faker) {

		int[] cpfDigits = createInvalidCpfDigits(faker);

		return convertAsFormattedString(cpfDigits);
	}

	public String getInvalidUnformattedCpf(Faker faker) {

		int[] cpfDigits = createInvalidCpfDigits(faker);

		return convertAsUnformattedString(cpfDigits);
	}

	private int[] createCpfDigits(Faker faker) {
		int[] inputDigits = createInputDigits(faker);

		int[] verifierDigits = calculateVerifierDigits(inputDigits);

		return ArrayUtils.addAll(inputDigits, verifierDigits);
	}

	private int[] createInvalidCpfDigits(Faker faker) {
		int[] inputDigits = createInputDigits(faker);

		int[] verifierDigits = calculateInvalidVerifierDigits(inputDigits, faker);

		return ArrayUtils.addAll(inputDigits, verifierDigits);
	}

	protected boolean isValid(String cpf) {

		if (!isFormatted(cpf) && !isUnformatted(cpf)) {
			return false;
		}

		int[] cpfDigits = convertAsIntegerArray(cpf);

		return isValid(cpfDigits);
	}

	private int[] convertAsIntegerArray(String cpf) {
		char[] cpfChars = cpf.toCharArray();
		int[] result = new int[CPF_DIGITS_LENGTH];
		int index = 0;

		for (char cpfChar : cpfChars) {
			if (Character.isDigit(cpfChar)) {
				result[index++] = Character.getNumericValue(cpfChar);
			}
		}

		return result;
	}

	private boolean isFormatted(String cpf) {
		return FORMATTED_CPF_PATTERN.matcher(cpf).matches();
	}

	private boolean isUnformatted(String cpf) {
		return UNFORMATTED_CPF_PATTERN.matcher(cpf).matches();
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

	protected int[] calculateVerifierDigits(int[] inputDigits) {

		int[] invertedInputDigits = Arrays.copyOf(inputDigits, inputDigits.length);

		ArrayUtils.reverse(invertedInputDigits);

		int[] verifierDigits = new int[VERIFIER_DIGITS_LENGTH];

		for (int index = 0; index < invertedInputDigits.length; index++) {
			verifierDigits[0] += invertedInputDigits[index] * (9 - (index % 10));
			verifierDigits[1] += invertedInputDigits[index] * (9 - ((index + 1) % 10));
		}

		verifierDigits[0] = (verifierDigits[0] % 11) % 10;

		verifierDigits[1] += verifierDigits[0] * 9;
		verifierDigits[1] = (verifierDigits[1] % 11) % 10;

		return verifierDigits;
	}

	private boolean isValid(int[] cpfDigits) {
		int[] inputDigits = retrieveInputDigits(cpfDigits);

		int[] cpfVerifierDigits = Arrays.copyOfRange(cpfDigits, INPUT_DIGITS_LENGTH,
				INPUT_DIGITS_LENGTH + VERIFIER_DIGITS_LENGTH);

		int[] calculatedVerifierDigits = calculateVerifierDigits(inputDigits);

		return Arrays.equals(cpfVerifierDigits, calculatedVerifierDigits);
	}

	private int[] retrieveInputDigits(int[] cpfDigits) {
		return Arrays.copyOfRange(cpfDigits, 0, INPUT_DIGITS_LENGTH);
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
