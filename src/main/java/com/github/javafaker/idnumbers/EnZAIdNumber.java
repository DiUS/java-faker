package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation based on the definition at
 * https://en.wikipedia.org/wiki/South_African_identity_card
 */

public class EnZAIdNumber {

    private static final String[] validPattern = {"##########08#","##########18#"};

    public String getValidSsn(Faker f) {

        String ssn = "";
        while (!validSsn(ssn)){
            String pattern = getPattern(f);
            ssn = f.numerify(pattern);
        }
        return ssn;

    }

    public String getInValidSsn(Faker f) {

        String ssn = f.numerify(validPattern[f.random().nextInt(2)]);
        while (validSsn(ssn)) {
            String pattern = getPattern(f);
            ssn = f.numerify(pattern);
        }
        return ssn;
    }

    private String getPattern(Faker faker) {
        return validPattern[faker.random().nextInt(2)];
    }

    boolean validSsn(String ssn) {
        if (ssn.length() != 13) {
            return false;
        }

        try {
            if (parseDate(ssn)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

        int calculatedChecksum = calculateChecksum(ssn.substring(0,12));
        int checksum = Integer.parseInt(ssn.substring(12,13));
        return (checksum == calculatedChecksum);
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

        int totalNumber = 0;

        for (int i = number.length() - 1; i >= 0; i -= 2) {
            int tmpNumber = calculate(Integer.parseInt(String.valueOf(number.charAt(i))) * 2);
            if (i == 0) {
                totalNumber += tmpNumber;
            } else {
                totalNumber += tmpNumber + Integer.parseInt(String.valueOf(number.charAt(i - 1)));
            }

        }
        if (totalNumber >= 0 && totalNumber < 9) {
            return (10 - totalNumber);
        } else {
            String str = String.valueOf(totalNumber);
            String s = String.valueOf(str.charAt(str.length() - 1));
            if (Integer.parseInt(s) == 0) {
                return 0;
            } else {
                return (10 - Integer.parseInt(s));
            }
        }

    }

    private static int calculate(int number){
        String str = String.valueOf(number);
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            total += Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return total;
    }

}