package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

/**
 * Portuguese VAT identification number (NIF)
 *
 * The number is 9 digits, with modulus 11 checksum digit.
 * There is fixed list of valid first digits to signify different types of NIF numbers
 *
 * @see <a href="https://pt.wikipedia.org/wiki/N%C3%BAmero_de_identifica%C3%A7%C3%A3o_fiscal">Número de identificação fiscal</a>
 * @see <a href="https://en.wikipedia.org/wiki/VAT_identification_number>VAT identification number</a>
 */
public class PtNifIdNumber {

	private static final Character[] validFirstDigits = { '1', '2', '3', '5', '6', '8' };
	private static final String[] validFirstDoubleDigits =
		{ "45", "70", "71", "72", "74", "75", "77", "79", "90", "91", "98", "99" };

	public String getInvalid(final Faker faker) {
		String digits = faker.number().digits(8);
		int digitSum = calculateDigitSum(digits);
		// by adding 5 to a valid checksum, we should invalidate
		// by having the wrong checksum or just the wrong number of digits
		return digits + (digitSum + 5);
	}

	public String getValid(final Faker faker) {
		String digits;
		if (faker.random().nextBoolean()){
			int i = faker.number().numberBetween(0, validFirstDigits.length - 1);
			char firstDigit = validFirstDigits[i];
			digits = firstDigit + faker.number().digits(7);
		} else {
			int i = faker.number().numberBetween(0, validFirstDoubleDigits.length - 1);
			String firstDoubleDigit = validFirstDoubleDigits[i];
			digits = firstDoubleDigit + faker.number().digits(6);
		}
		int digitSum = calculateDigitSum(digits);
		return digits + digitSum;
	}

	private int calculateDigitSum(String numbers) {
		int checkSum = 0;
		for (int i = 1; i <= numbers.length(); i++) {
			int digit = Character.getNumericValue(numbers.charAt(i - 1));
			checkSum += (10 - i) * digit;
		}

		int val = (checkSum / 11) * 11;
		checkSum -= val;
		if (checkSum == 0 || checkSum == 1) {
			checkSum = 0;
		} else {
			checkSum = 11 - checkSum;
		}
		return checkSum;
	}
}
