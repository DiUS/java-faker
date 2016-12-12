package com.github.javafaker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation based on the definition at
 * https://www.skatteverket.se/privat/folkbokforing/personnummer.4.3810a01c150939e893f18c29.html
 * and the description at
 * https://en.wikipedia.org/wiki/Personal_identity_number_(Sweden)
 */
class SwedishIdNumber {

    String getValidSwedishSsn(Faker f) {
        String candidate = "";
        while (!validSwedishSsn(candidate)) {
            candidate = f.numerify(f.fakeValuesService().resolve("id_number.valid", this, f));
        }

        return candidate;
    }

    String getInvalidSwedishSsn(Faker f) {
        String candidate = "121212-1212"; // Seed with a valid number
        while (validSwedishSsn(candidate)) {
            candidate = f.numerify(f.fakeValuesService().resolve("id_number.valid", this, f));
        }

        return candidate;
    }

    boolean validSwedishSsn(String ssn) {
        if (ssn.length() != 11) {
            return false;
        }

        try {
            if (parseDate(ssn)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        int calculatedChecksum = calculateChecksum(ssn);

        int checksum = Integer.parseInt(ssn.substring(10, 11));

        return checksum == calculatedChecksum;
    }

    private boolean parseDate(String ssn) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String dateString = ssn.substring(0, 6);
        Date date = sdf.parse(dateString);

        // want to check that the parsed date is equal to the supplied data, most of the attempts will fail
        String reversed = sdf.format(date);
        return !reversed.equals(dateString);
    }

    private int calculateChecksum(String number) {
        String dateString = number.substring(0, 6);
        String birthNumber = number.substring(7, 10);

        String calculatedNumber = calculateDigits(dateString + birthNumber);
        int sum = calculateDigitSum(calculatedNumber);

        int lastDigit = (sum % 10);
        int difference = 10 - lastDigit;

        return (difference % 10);
    }

    private String calculateDigits(String numbers) {
        String calculatedNumbers = "";
        for (int i = 0; i < 9; i++) {
            int res;
            int n = Integer.parseInt(numbers.substring(i, i + 1));
            if (i % 2 == 0) {
                res = n * 2;
            } else {
                res = n;
            }

            calculatedNumbers += res;
        }
        return calculatedNumbers;
    }

    private int calculateDigitSum(String numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length(); i++) {
            int n = Integer.parseInt(numbers.substring(i, i + 1));
            sum += n;
        }
        return sum;
    }


}
