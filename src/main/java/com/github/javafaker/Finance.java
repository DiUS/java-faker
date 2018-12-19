package com.github.javafaker;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Finance {
    private final Faker faker;

    protected Finance(Faker faker) {
        this.faker = faker;
    }


    private static final Map<String, String> countryCodeToBasicBankAccountNumberPattern =
            createCountryCodeToBasicBankAccountNumberPatternMap();

    public String creditCard(CreditCardType creditCardType) {
        final String key = String.format("finance.credit_card.%s", creditCardType.toString().toLowerCase());
        String value = faker.fakeValuesService().resolve(key, this, faker);
        final String template = faker.numerify(value);

        String[] split = template.replaceAll("[^0-9]", "").split("");
        List<Integer> reversedAsInt = new ArrayList<Integer>();
        for (int i = 0; i < split.length; i++) {
            final String current = split[split.length - 1 - i];
            if (!current.isEmpty()) {
                reversedAsInt.add(Integer.valueOf(current));
            }
        }
        int luhnSum = 0;
        int multiplier = 1;
        for (Integer digit : reversedAsInt) {
            multiplier = (multiplier == 2 ? 1 : 2);
            luhnSum += sum(String.valueOf(digit * multiplier).split(""));
        }
        int luhnDigit = (10 - (luhnSum % 10)) % 10;
        return template.replace('\\', ' ').replace('/', ' ').trim().replace('L', String.valueOf(luhnDigit).charAt(0));
    }

    public String creditCard() {
        CreditCardType type = randomCreditCardType();
        return creditCard(type);
    }

    /**
     * Generates a random Business Identifier Code
     */
    public String bic() {
        return faker.regexify("([A-Z]){4}([A-Z]){2}([0-9A-Z]){2}([0-9A-Z]{3})?");
    }

    public String iban() {
        List<String> countryCodes = new ArrayList<String>(countryCodeToBasicBankAccountNumberPattern.keySet());
        String randomCountryCode = countryCodes.get(faker.random().nextInt(countryCodes.size()));
        return iban(randomCountryCode);
    }

    public String iban(String countryCode) {
        String basicBankAccountNumber = faker.regexify(countryCodeToBasicBankAccountNumberPattern.get(countryCode));
        String checkSum = calculateIbanChecksum(countryCode, basicBankAccountNumber);
        return countryCode + checkSum + basicBankAccountNumber;
    }

    private CreditCardType randomCreditCardType() {
        return CreditCardType.values()[this.faker.random().nextInt(CreditCardType.values().length)];
    }

    private static int sum(String[] string) {
        int sum = 0;
        for (String s : string) {
            if (!s.isEmpty()) {
                sum += Integer.valueOf(s);
            }
        }
        return sum;
    }

    private static String calculateIbanChecksum(String countryCode, String basicBankAccountNumber) {
        String basis = basicBankAccountNumber + countryCode + "00";

        StringBuilder sb = new StringBuilder();
        char[] characters = basis.toLowerCase().toCharArray();
        for (char c : characters) {
            if (Character.isLetter(c)) {
                sb.append(String.valueOf((c - 'a') + 10));
            } else {
                sb.append(c);
            }
        }

        int mod97 = new BigInteger(sb.toString()).mod(BigInteger.valueOf(97L)).intValue();
        return StringUtils.leftPad(String.valueOf(98 - mod97), 2, '0');
    }

    private static Map<String, String> createCountryCodeToBasicBankAccountNumberPatternMap() {
        // source: https://www.swift.com/standards/data-standards/iban
        Map<String, String> ibanFormats = new HashMap<String, String>();
        ibanFormats.put("AL", "\\d{8}[0-9A-Za-z]{16}");
        ibanFormats.put("AD", "\\d{4}\\d{4}[0-9A-Za-z]{12}");
        ibanFormats.put("AT", "\\d{5}\\d{11}");
        ibanFormats.put("AZ", "[A-Z]{4}[0-9A-Za-z]{20}");
        ibanFormats.put("BH", "[A-Z]{4}[0-9A-Za-z]{14}");
        ibanFormats.put("BE", "\\d{3}\\d{7}\\d{2}");
        ibanFormats.put("BA", "\\d{3}\\d{3}\\d{8}\\d{2}");
        ibanFormats.put("BR", "\\d{8}\\d{5}\\d{10}[A-Z]{1}[0-9A-Za-z]{1}");
        ibanFormats.put("BG", "[A-Z]{4}\\d{4}\\d{2}[0-9A-Za-z]{8}");
        ibanFormats.put("CR", "\\d{3}\\d{14}");
        ibanFormats.put("HR", "\\d{7}\\d{10}");
        ibanFormats.put("CY", "\\d{3}\\d{5}[0-9A-Za-z]{16}");
        ibanFormats.put("DK", "\\d{4}\\d{9}\\d{1}");
        ibanFormats.put("DO", "[0-9A-Za-z]{4}\\d{20}");
        ibanFormats.put("EE", "\\d{2}\\d{2}\\d{11}\\d{1}");
        ibanFormats.put("FI", "\\d{6}\\d{7}\\d{1}");
        ibanFormats.put("FR", "\\d{5}\\d{5}[0-9A-Za-z]{11}\\d{2}");
        ibanFormats.put("GE", "[A-Z]{2}\\d{16}");
        ibanFormats.put("DE", "\\d{8}\\d{10}");
        ibanFormats.put("GI", "[A-Z]{4}[0-9A-Za-z]{15}");
        ibanFormats.put("GR", "\\d{3}\\d{4}[0-9A-Za-z]{16}");
        ibanFormats.put("GT", "[0-9A-Za-z]{4}[0-9A-Za-z]{20}");
        ibanFormats.put("HU", "\\d{3}\\d{4}\\d{1}\\d{15}\\d{1}");
        ibanFormats.put("IS", "\\d{4}\\d{2}\\d{6}\\d{10}");
        ibanFormats.put("IE", "[A-Z]{4}\\d{6}\\d{8}");
        ibanFormats.put("IL", "\\d{3}\\d{3}\\d{13}");
        ibanFormats.put("IT", "[A-Z]{1}\\d{5}\\d{5}[0-9A-Za-z]{12}");
        ibanFormats.put("JO", "[A-Z]{4}\\d{4}[0-9A-Za-z]{18}");
        ibanFormats.put("KZ", "\\d{3}[0-9A-Za-z]{13}");
        ibanFormats.put("XK", "\\d{4}\\d{10}\\d{2}");
        ibanFormats.put("KW", "[A-Z]{4}[0-9A-Za-z]{22}");
        ibanFormats.put("LV", "[A-Z]{4}[0-9A-Za-z]{13}");
        ibanFormats.put("LB", "\\d{4}[0-9A-Za-z]{20}");
        ibanFormats.put("LI", "\\d{5}[0-9A-Za-z]{12}");
        ibanFormats.put("LT", "\\d{5}\\d{11}");
        ibanFormats.put("LU", "\\d{3}[0-9A-Za-z]{13}");
        ibanFormats.put("MK", "\\d{3}[0-9A-Za-z]{10}\\d{2}");
        ibanFormats.put("MT", "[A-Z]{4}\\d{5}[0-9A-Za-z]{18}");
        ibanFormats.put("MR", "\\d{5}\\d{5}\\d{11}\\d{2}");
        ibanFormats.put("MU", "[A-Z]{4}\\d{2}\\d{2}\\d{12}\\d{3}[A-Z]{3}");
        ibanFormats.put("MD", "[0-9A-Za-z]{2}[0-9A-Za-z]{18}");
        ibanFormats.put("MC", "\\d{5}\\d{5}[0-9A-Za-z]{11}\\d{2}");
        ibanFormats.put("ME", "\\d{3}\\d{13}\\d{2}");
        ibanFormats.put("NL", "[A-Z]{4}\\d{10}");
        ibanFormats.put("NO", "\\d{4}\\d{6}\\d{1}");
        ibanFormats.put("PK", "[A-Z]{4}[0-9A-Za-z]{16}");
        ibanFormats.put("PS", "[A-Z]{4}[0-9A-Za-z]{21}");
        ibanFormats.put("PL", "\\d{8}\\d{16}");
        ibanFormats.put("PT", "\\d{4}\\d{4}\\d{11}\\d{2}");
        ibanFormats.put("QA", "[A-Z]{4}[0-9A-Za-z]{21}");
        ibanFormats.put("RO", "[A-Z]{4}[0-9A-Za-z]{16}");
        ibanFormats.put("LC", "[A-Z]{4}[0-9A-Za-z]{24}");
        ibanFormats.put("SM", "[A-Z]{1}\\d{5}\\d{5}[0-9A-Za-z]{12}");
        ibanFormats.put("ST", "\\d{8}\\d{11}\\d{2}");
        ibanFormats.put("SA", "\\d{2}[0-9A-Za-z]{18}");
        ibanFormats.put("RS", "\\d{3}\\d{13}\\d{2}");
        ibanFormats.put("SK", "\\d{4}\\d{6}\\d{10}");
        ibanFormats.put("SI", "\\d{5}\\d{8}\\d{2}");
        ibanFormats.put("ES", "\\d{4}\\d{4}\\d{1}\\d{1}\\d{10}");
        ibanFormats.put("SE", "\\d{3}\\d{16}\\d{1}");
        ibanFormats.put("CH", "\\d{5}[0-9A-Za-z]{12}");
        ibanFormats.put("TL", "\\d{3}\\d{14}\\d{2}");
        ibanFormats.put("TN", "\\d{2}\\d{3}\\d{13}\\d{2}");
        ibanFormats.put("TR", "\\d{5}\\d{1}[0-9A-Za-z]{16}");
        ibanFormats.put("UA", "\\d{6}[0-9A-Za-z]{19}");
        ibanFormats.put("AE", "\\d{3}\\d{16}");
        ibanFormats.put("GB", "[A-Z]{4}\\d{6}\\d{8}");
        ibanFormats.put("VG", "[A-Z]{4}\\d{16}");
        return ibanFormats;
    }
}
