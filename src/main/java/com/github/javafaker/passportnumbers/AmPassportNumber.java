package com.github.javafaker.passportnumbers;

import com.github.javafaker.Faker;

public class AmPassportNumber {
    private static final String[] validCHPatterns = {"[0-9]{8}"};

    /**
     * Generates a valid America passport number
     *
     * @param f
     *            object faker
     * @return a valid Chinese passport number
     */
    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    public String getValidAm(Faker f) {
        String ch = f.regexify("[0-9A-Z]{8}");

        boolean isValid = false;
        for (String validCHPattern : validCHPatterns) {
            if (ch.matches(validCHPattern)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            ch = getValidAm(f);
        }
        return ch;
    }

    /**
     * Generates a invalid America passport number
     *
     * @param f
     *            object faker
     * @return a invalid Chinese passport number
     */
    // CS304 Issue link: https://github.com/DiUS/java-faker/issues/450
    public String getInvalidAm(Faker f) {
        String ch = f.regexify("[A-Z0-9]{1,}");

        boolean isValid = true;
        for (String validCHPattern : validCHPatterns) {
            if (ch.matches(validCHPattern)) {
                continue;
            }
            isValid = false;
        }
        if (isValid) {
            ch = getInvalidAm(f);
        }
        return ch;
    }
}
