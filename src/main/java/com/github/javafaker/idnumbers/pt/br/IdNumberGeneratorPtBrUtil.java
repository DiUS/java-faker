package com.github.javafaker.idnumbers.pt.br;

import java.util.Random;

public class IdNumberGeneratorPtBrUtil {

    private static final Random random = new Random();

    private IdNumberGeneratorPtBrUtil() {
    }

    /**
     * https://en.wikipedia.org/wiki/CNPJ
     *
     * @param formatted a cnpj (un)formatted
     * @param valid a cnpj (in)valid
     */
    public static String cnpj(boolean formatted, boolean valid) {
        String cnpj = "";

        if (valid) {
            StringBuilder partial = new StringBuilder();
            for (int i = 0; i < 8; i++) {
                partial.append((int) (Math.random() * 9));
            }

            cnpj = partial.append("0001").toString();

            int d1 = digit(calculateWeight(cnpj.substring(4, 12), 9) + calculateWeight(cnpj.substring(0, 4), 5));
            int d2 = digit((d1 * 2) + calculateWeight(cnpj.substring(5, 12), 9) + calculateWeight(cnpj.substring(0, 5), 6));

            cnpj = (cnpj + d1) + d2;
        } else {
            cnpj = String.valueOf(random.nextInt(1000000000) + (random.nextInt(90) + 10) * 1000000000000L);
        }

        return (formatted) ? DocumentFormatterUtil.cnpj(cnpj) : cnpj;
    }

    /**
     * https://en.wikipedia.org/wiki/CPF_number
     *
     * @param formatted a CPF (un)formatted
     * @param valid a CPF (in)valid
     */
    public static String cpf(boolean formatted, boolean valid) {
        String cpf = "";
        if (valid) {
            StringBuilder partial = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                partial.append((int) (Math.random() * 10));
            }
            cpf = partial.toString();

            int d1 = digit(calculateWeight(cpf, 10));
            int d2 = digit((d1 * 2) + calculateWeight(cpf, 11));

            cpf = (cpf + d1) + d2;
        } else {
            cpf = String.valueOf(random.nextInt(1000000000) + (random.nextInt(90) + 10) * 1000000000L);
        }
        return formatted ? DocumentFormatterUtil.cpf(cpf) : cpf;
    }

    public static int calculateWeight(final String num, final int weight) {
        int sum = 0;
        int weightAux = weight;

        for (int index = 0; index < num.length(); index++) {
            sum += Integer.parseInt(num.substring(index, index + 1)) * weightAux--;
        }
        return sum;
    }

    public static int digit(int verifyingDigit) {
        if (verifyingDigit % 11 == 0 || verifyingDigit % 11 == 1)
            return 0;
        else
            return 11 - verifyingDigit % 11;
    }

}
