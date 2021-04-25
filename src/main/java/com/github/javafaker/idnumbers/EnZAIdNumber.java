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

    /**
     * Generate a valid social security number on faker
     * @param f the java-faker
     * @return a valid social security number on faker
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/566
    public String getValidSsn(Faker f) {

        String ssn = "";
        while (!validSsn(ssn)){
            String pattern = getPattern(f);
            ssn = f.numerify(pattern);
        }
        return ssn;

    }

    /**
     * Generate a invalid social security number on faker
     * @param f the java-faker
     * @return a invalid social security number on faker
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/566
    public String getInValidSsn(Faker f) {

        String ssn = f.numerify(validPattern[f.random().nextInt(2)]);
        while (validSsn(ssn)) {
            String pattern = getPattern(f);
            ssn = f.numerify(pattern);
        }
        return ssn;
    }

    /**
     * Generate a fixed format numeric string
     * @param f the java-faker
     * @return a fixed format numeric string
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/566
    private String getPattern(Faker faker) {
        return validPattern[faker.random().nextInt(2)];
    }

    /**
     * Judge whether a social security number is valid
     * @param ssn social security number
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/566
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

    /**
     * Judge whether a numeric string of ssn can represent a legal date
     * @param ssn social security number
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/566
    private boolean parseDate(String ssn) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String dateString = ssn.substring(0, 6);
        Date date = sdf.parse(dateString);

        // want to check that the parsed date is equal to the supplied data, most of the attempts will fail
        String reversed = sdf.format(date);
        return !reversed.equals(dateString);
    }

    /**
     * Calculate the Check Number in the last number of a ssn
     * @param number a social security number not including the last number
     * @return check number of this ssn
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/566
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

    /**
     * Calculate the sum of each digit of the number
     * @return sum of each digit of the number
     */
    //CS304 issue link: https://github.com/DiUS/java-faker/issues/566
    private static int calculate(int number){
        String str = String.valueOf(number);
        int total = 0;
        for (int i = 0; i < str.length(); i++) {
            total += Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return total;
    }

}