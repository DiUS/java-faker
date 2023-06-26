package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

/**
 * PtBRIdNumber
 */
public class PtBRIdNumber {

    /**
     * Generates a valid Brazilian CPF (Cadastro de Pessoas Físicas) number using the Faker library.
     *
     * @param f a Faker object used to generate random data
     * @return a String representing a valid CPF number
     * @throws IllegalArgumentException if the Faker object is null
     */
    public String getValidCpf(Faker f) throws IllegalArgumentException {
        if (f == null) {
            throw new IllegalArgumentException("Faker object cannot be null");
        }
        return this.createValidCpf();
    }


    public boolean isValid(String cpf) {
        if (cpf == null)
            return false;
        cpf = cpf.replaceAll("[^0-9]", ""); // Remove everything except digits
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
                || cpf.equals("33333333333") || cpf.equals("44444444444")
                || cpf.equals("55555555555") || cpf.equals("66666666666")
                || cpf.equals("77777777777") || cpf.equals("88888888888")
                || cpf.equals("99999999999") || (cpf.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protects the code for eventual errors of type conversion (int)
        try {
            // calculates the first digit
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // convert the i-th character of CPF into a number:
                // for example, transform the character '0' into the integer 0
                // (48 is the position of '0' in the ASCII table)
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char) (r + 48); // fix char

            // checksun digit
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char) (r + 48);

            // Check if the calculated digits match the digits provided.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return (true);
            else
                return (false);
        } catch (Exception erro) {
            return (false);
        }
    }


    private String createValidCpf() {
        String cpf = "";
        for (int i = 0; i < 9; i++) {
            cpf += (int) (Math.random() * 9);
        }

        cpf = cpf + calculateDigit(cpf);
        cpf = cpf + calculateDigit(cpf);
        return cpf;

    }

    private int calculateDigit(String cpf) {
        int sum = 0;
        int digit = 0;
        int multiplier = cpf.length() + 1;

        for (int i = 0; i < cpf.length(); i++) {
            sum += Integer.parseInt(cpf.substring(i, i + 1)) * multiplier;
            multiplier--;
        }

        digit = 11 - (sum % 11);

        if (digit > 9) {
            return 0;
        }

        return digit;
    }



}
