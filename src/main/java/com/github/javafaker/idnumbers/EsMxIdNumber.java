package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

//CS304 Issue link: https://github.com/DiUS/java-faker/issues/571

/**
 * Implementation based on the definition at
 * https://en.wikipedia.org/wiki/Unique_Population_Registry_Code
 */
@SuppressWarnings("checkstyle:RegexpSingleline")
public class EsMxIdNumber {

    /**
     * Represent all proper formats of CURP to generate.
     */
    private static final String[] VALID_PATTERN = {"????\\d{2}[0-1]\\d[0-3]\\d[HM]??????#",
            "????\\d{2}[0-1]\\d[0-3]\\d[HM]?????##"};
    /**
     * Represent all proper state infomations in CURP.
     */
    private static final String[] STATE = {
        "AS", "BC", "BS", "CC", "CL", "CM", "CS", "CH",
        "DF", "DG", "GT", "GR", "HG", "JC", "MC", "MN",
        "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP",
        "SL", "SR", "TC", "TS", "TL", "VZ", "YN", "ZS", "NE"
    };
    /**
     * Contains all vowels in order to generate proper CURP.
     */
    private static final char[] VOWEL = {'A', 'E', 'I', 'O', 'U'};
    /**
     * Contains all consonants in order to generate proper CURP.
     */
    private static final char[] CONSONANT = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N',
            'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'};
    /**
     * Represent the standard length of MEX CURP.
     */
    private static final int STANDARD_LENGTH = 18;

    /**
     * Get A valid MEX CURP.
     *
     * @param f faker
     * @return A valid MEX CURP.
     */
    public String getValidSsn(Faker f) {
        char[] ssn = new char[STANDARD_LENGTH];
        for (int i = 0; i < ssn.length - 1; i++) {
            i = i == 4 ? 13 : i;
            ssn[i] = CONSONANT[f.random().nextInt(CONSONANT.length)];
        }

        //second char must be a vowel
        ssn[1] = VOWEL[f.random().nextInt(VOWEL.length)];

        //generate proper date
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String birthday = sdf.format(f.date().birthday(0, 140));
        System.arraycopy(birthday.toCharArray(), 0, ssn, 4, 6);

        //generate gender
        ssn[10] = f.random().nextBoolean() ? 'H' : 'M';

        //generate state
        System.arraycopy(STATE[f.random().nextInt(STATE.length)].toCharArray(), 0, ssn, 11, 2);

        //generate random bit
        ssn[17] = '#';
        if (f.random().nextBoolean()) {
            ssn[16] = '?';
            return f.numerify(f.letterify(String.valueOf(ssn), true));
        } else {
            ssn[16] = '#';
            return f.numerify(String.valueOf(ssn));
        }
    }

    /**
     * Get An invalid MEX CURP.
     *
     * @param f faker
     * @return An invalid MEX CURP.
     */
    public String getInvalidSsn(Faker f) {
        String ssn = "HEGG560427MVZRRL04"; //seed
        while (validEsMxSsn(ssn)) {
            String regex = getRegex(f);
            ssn = f.numerify(f.letterify(regex, true));
            ssn = f.regexify(ssn);
        }
        return ssn;
    }

    /**
     * Get A random regex to generate CURP.
     *
     * @param f faker
     * @return A regex of MEX CURP.
     */
    private String getRegex(Faker f) {
        return VALID_PATTERN[f.random().nextInt(VALID_PATTERN.length)];
    }

    /**
     * Check out whether the input MEX CURP is valid.
     *
     * @param ssn String
     * @return whether this ssn is valid or not.
     */
    boolean validEsMxSsn(String ssn) {
        String stdRegex = "[A-Z][A-Z][A-Z][A-Z]\\d{2}[0-1]\\d[0-3]\\d[HM]" +
                "[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z,0-9]\\d";
        if (ssn.length() != STANDARD_LENGTH) {
            return false;
        }
        try {
            if (parseDate(ssn)) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return Pattern.matches(stdRegex,ssn)&&checkState(ssn);
    }

    /**
     * Check whether the date part in ssn is valid.
     *
     * @param ssn String
     * @return whether the date is valid or not.
     * @throws ParseException if the date is invalid
     */
    boolean parseDate(String ssn) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        String dateString = ssn.substring(4, 10);
        Date date = sdf.parse(dateString);

        String reversed = sdf.format(date);
        return !reversed.equals(dateString);
    }

    /**
     * Check whether the state part in ssn is valid.
     *
     * @param ssn String
     * @return whether the state is valid or not.
     */
    boolean checkState(String ssn) {
        String tmpState = ssn.substring(11, 13);
        for (String s : STATE) {
            if (tmpState.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
