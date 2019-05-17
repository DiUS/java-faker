package com.github.javafaker;

public class Barcode {
    private final Faker faker;

    public Barcode(Faker faker) {
        this.faker = faker;
    }

    public long ean13() {
        return ean(13);
    }

    public long ean8() {
        return ean(8);
    }

    public long gtin14() {
        return ean(14);
    }

    public long gtin12() {
        return ean(12);
    }

    public long gtin13() {
        return ean13();
    }

    public long gtin8() {
        return ean8();
    }

    private static int roundToHighestMultiplyOfTen(int number) {
        if (number % 10 == 0)
            return number;
        else {
            int ones = number % 10;
            int add = 10 - ones;
            return number + add;
        }

    }

    private long ean(int length) {
        long first = 0;
        switch (length) {
            case 13:
                first = this.faker.number().randomNumber(12, true);
                break;
            case 8:
                first = this.faker.number().randomNumber(7, true);
                break;
            case 14:
                first = this.faker.number().randomNumber(13, true);
                break;
            case 12:
                first = this.faker.number().randomNumber(11, true);
                break;
        }
        char[] array = String.valueOf(first).toCharArray();
        int odd = 0;
        int even = 0;
        for (int i = 0; i < array.length; i ++){
            int digit = Integer.parseInt(String.valueOf(array[i]));
            if ((i + 1) % 2 == 0)
                even += digit;
            else
                odd += digit;
        }
        int var = 0;

        if (length == 13){
            var = (even * 3) + odd;
        }
        if (length == 8 || length == 14 || length == 12) {
            var = (odd * 3) + even;
        }

        int rounded = roundToHighestMultiplyOfTen((var));
        int checkDigit = rounded - var;

        return Long.parseLong(String.valueOf(first) + String.valueOf(checkDigit));
    }
}
