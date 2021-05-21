package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Implementation based on the definition at
 * https://en.wikipedia.org/wiki/Unique_Population_Registry_Code
 */
//CS304 Issue link: https://github.com/DiUS/java-faker/issues/571
public class EsMXIdNumber {

    private String[] consonant = {"B", "C", "C", "D", "F",
            "G", "H", "J", "K", "L", "L", "M", "N", "N",
            "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"
    };

    private String[] vowel = {"A", "E", "I", "O", "U"};

    private String[] states = {"AG", "BC", "BS", "CM", "CS",
            "CH", "CO", "CL", "DF", "DG", "GT", "GR",
            "HG", "JA", "EM", "MI", "MO", "NA", "NL",
            "OA", "PU", "QT", "QR", "SL", "SI", "SO",
            "TB", "TM", "TL", "VE", "YU", "ZA", "NE",
    };

    /**
     * Get A valid MEX CURP.
     *
     * @param faker faker
     * @return A valid MEX CURP.
     */
    //CS304 Issue link: https://github.com/DiUS/java-faker/issues/571
    public String get(Faker faker) {

        String sex = new String[]{"H", "M"}[faker.random().nextInt(2)];
        String birthDay = getBirthday(faker);
        String state = states[faker.random().nextInt(33)];

        String c1 = consonant[faker.random().nextInt(24)];
        String c2 = consonant[faker.random().nextInt(24)];
        String c3 = consonant[faker.random().nextInt(24)];

        String v1 = vowel[faker.random().nextInt(5)];
        String v2 = vowel[faker.random().nextInt(5)];
        String v3 = vowel[faker.random().nextInt(5)];
        String v4 = vowel[faker.random().nextInt(5)];

        String ranNum = (Integer.parseInt(birthDay.substring(0, 4)) <= 1999) ?
                "0" : consonant[faker.random().nextInt(24)];

        String ssn = c1 + v1 + c2 + c3 + birthDay.substring(2, 8) + sex + state + v2 + v3 + v4 + ranNum;
        ssn = ssn + getChecksum(ssn);

        return ssn;
    }

    /**
     * Get A invalid MEX CURP.
     *
     * @param faker faker
     * @return A invalid MEX CURP.
     */
    //CS304 Issue link: https://github.com/DiUS/java-faker/issues/571
    public String getWrong(Faker faker) {
        String[] cha = {"HEFA560427MVZRRL04",
                "DKEM193827HDQWEF05",
                "KAKS142444HNSSFAW6",
                "KSDF414424HNSDFAW6",
                "AKDF414424MSDSFAW6",
                "ADKF144424MNSDFCD6",
                "MYDF144424MDNFAW37",
                "AKKS414424MDAFDFW6",
                "WKDF144244HSDCNFA2",
                "AKSK414244HSDATT56",
                "QWDF414424HNSDVAW4",
                "AKDF144424MDEFVFA1"
        };
        return cha[faker.random().nextInt(12)];
    }

    /**
     * Randomly gets a birthday.
     *
     * @param f A specific instance of Faker.
     * @return A valid date.
     */
    private String getBirthday(Faker f) {
        int year = f.random().nextInt(1900, 2021);
        int month = f.random().nextInt(1, 12);
        int day = validDay(year, month, f);
        return String.valueOf(year * 10000 + month * 100 + day);
    }


    /**
     * Gets a valid day according to year and month.
     *
     * @param year  A specific year.
     * @param month A specific month.
     * @param f     A specific instance of Faker.
     * @return A valid day.
     */
    private int validDay(int year, int month, Faker f) {

        List<Integer> bigMonths = Arrays.asList(1, 3, 5, 7, 8, 10, 12);

        if (month == 2) {
            if (year % 4 == 0) {
                return f.random().nextInt(1, 29);
            } else return f.random().nextInt(1, 28);
        } else if (bigMonths.contains(month)) {
            return f.random().nextInt(1, 31);
        } else return f.random().nextInt(1, 30);

    }

    /**
     * Gets the Checksum.
     *
     * @param str input string
     * @return Checksum.
     */
    private int getChecksum(String str) {
        int sum = 0;
        int v = str.length() + 1;
        for (int i = 0; i < str.length(); i++) {
            int number;
            if (str.charAt(i) < '9')
                number = str.charAt(i) - '0';
            else number = str.charAt(i) - 'A' + 10;
            sum += number * v--;
        }
        sum = Math.abs((sum % 10) - 10);
        return (sum == 10) ? 0 : sum;
    }

}