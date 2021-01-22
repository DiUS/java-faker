package com.github.javafaker.idnumbers;


import com.github.javafaker.Number;

public class DNIIdNumber {

    private static final String ID_VALID_CHARS = "TRWAGMYFPDXBNJZSQVHLCKE";

    private static final Integer MOD_NUMBER = 23;

    private static final int NUMBER_OF_DIGITS = 8;
    
    private static final String DNI_FORMAT_VALUE = "%s-%s";

    private final Number number;

    public DNIIdNumber(Number number) {
        this.number = number;
    }

    public String generate() {
        Long randomNumber = number.randomNumber(NUMBER_OF_DIGITS, true);
        Long mod = randomNumber % MOD_NUMBER;
        return String.format(DNI_FORMAT_VALUE, randomNumber, ID_VALID_CHARS.charAt(mod.intValue()));
    }

}
