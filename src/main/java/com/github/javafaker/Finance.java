package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;
import com.github.javafaker.service.RandomService;

import java.util.ArrayList;
import java.util.List;

public class Finance {
    private final FakeValuesServiceInterface fakeValuesService;
    private final RandomService randomService;

    public Finance(FakeValuesServiceInterface fakeValuesService, RandomService randomService) {
        this.fakeValuesService = fakeValuesService;
        this.randomService = randomService;
    }

    public String creditCard() {
        CreditCardType type = randomCreditCardType();
        final String key = String.format("credit_card.%s", type.toString().toLowerCase());
        String value = fakeValuesService.fetchString(key);
        final String template = fakeValuesService.numerify(value);

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

    /**
     * Generates a random Business Identifier Code
     */
    public String bic() {
        return fakeValuesService.regexify("([A-Z]){4}([A-Z]){2}([0-9A-Z]){2}([0-9A-Z]{3})?");
    }

    private CreditCardType randomCreditCardType() {
        return CreditCardType.values()[this.randomService.nextInt(CreditCardType.values().length)];
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
}
