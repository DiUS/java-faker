package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;

import java.util.HashMap;
import java.util.Map;

/**
 * Generate number of UIN/FIN for Singapore.
 * Algorithm is given from http://www.ngiam.net/NRIC/
 */
public class NricNumber {

	public enum Type {SINGAPOREAN_TWENTIETH_CENTURY, FOREIGNER_TWENTIETH_CENTURY, SINGAPOREAN_TWENTY_FIRST_CENTURY, FOREIGNER_TWENTY_FIRST_CENTURY}

	private static class NricType{
		private char firstLetter;
		private String matchLetters;
		private int[] code;
		private int initialValue;

		public NricType(char firstLetter, String matchLetters, int[] code, int initialValue) {
			this.firstLetter = firstLetter;
			this.matchLetters = matchLetters;
			this.code = code;
			this.initialValue = initialValue;
		}

		public String format(int[] digits) {
			int value = initialValue;
			StringBuilder id = new StringBuilder(firstLetter + "");
			for (int i = 0; i < digits.length; i++) {
				value += digits[i] * code[i];
				id.append(String.valueOf(digits[i]));
			}
			value %= 11;
			id.append(matchLetters.charAt(value));
			return id.toString();
		}
	}

	private static final int[] CODE = new int[]{0, 2, 7, 6, 5, 4, 3, 2};
	private static final String FIN_LETTERS = "XWUTRQPNMLK";
	private static final String UIN_LETTERS = "JZIHGFEDCBA";
	private static final Map<Type, NricType> INITIALIZER = new HashMap<Type, NricType>();

	static {
		INITIALIZER.put(Type.SINGAPOREAN_TWENTIETH_CENTURY, new NricType('S', UIN_LETTERS, CODE, 0));
		INITIALIZER.put(Type.FOREIGNER_TWENTIETH_CENTURY, new NricType('F', FIN_LETTERS, CODE, 0));
		INITIALIZER.put(Type.SINGAPOREAN_TWENTY_FIRST_CENTURY, new NricType('T', UIN_LETTERS, CODE, 4));
		INITIALIZER.put(Type.FOREIGNER_TWENTY_FIRST_CENTURY, new NricType('G', FIN_LETTERS, CODE, 4));
	}

	public static String getValidFIN(Faker f, Type type){
		final RandomService random = f.random();
		final int[] number = new int[7];
		for (int i = 0; i < number.length; i++) {
			number[i] =	random.nextInt(0, 9);
		}
		return INITIALIZER.get(type).format(number);
	}

}
