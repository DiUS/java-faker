package com.github.javafaker;

import java.util.Random;

public class Code {

    private Random random = new Random();

    public String isbn10() {
        StringBuilder isbn10 = new StringBuilder();
        int sum = 0;
        for (int i = 10; i > 1; i--) {
            int n = random.nextInt(10);
            sum += i * n;
            isbn10.append(n);
        }
        int check = (11 - sum % 11) % 11;
        isbn10.append('-');
        isbn10.append(check != 10 ? check : "X");
        return isbn10.toString();
    }

    public String isbn13() {
        StringBuilder isbn13 = new StringBuilder();
        int sum = 0;
        int multiplier = 1;
        int a = 1;
        int b = 2;
        for (int i = 0; i < 12; i++) {
            int n = random.nextInt(10);
            sum += multiplier * n;
            multiplier = multiplier == 1 ? 3 : 1;
            isbn13.append(n);
            if (i == b) {
                isbn13.append('-');
                int t = b;
                b += a;
                a = t;
            }
        }
        int check = (10 - sum % 10) % 10;
        isbn13.append(check);
        return isbn13.toString();
    }
}
