package com.github.javafaker;

import com.github.javafaker.shared.CPFUtils;

import java.util.Random;

/**
 * Partial code copy from https://github.com/jrjuniorsp/GeradorValidadorCPFCNPJ/blob/master/src/com/jrmobile/service/GeradorCPF.java
 */
public class CPF {

    private Faker faker;

    protected CPF(Faker faker) {
        this.faker = faker;
    }

    /**
     * Return valid and formatted
     * @see com.github.javafaker.shared.CPFUtils#unformatCPF(String) to unformat
     * @return a valid CPF
     */
    public String valid() {
        return generateCPF(true);
    }

    /**
     * Return invalid and formatted
     * @see com.github.javafaker.shared.CPFUtils#unformatCPF(String) to unformat
     * @return an invalid CPF
     */
    public String invalid() {
        return generateCPF(false);
    }

    public String generateCPF(boolean valid) {
        String partial = "";
        String cpf = "";
        Integer number;

        for (int i = 0; i < 9; i++) {
            number = new Integer((int) (Math.random() * 10));
            partial += number.toString();
        }

        if(valid) {
            cpf = partial + CPFUtils.calculateVerificationDigit(partial);
        } else {
            String twoFakeDigits = String.valueOf(new Random().nextInt(9 + 1)) + String.valueOf(new Random().nextInt(9 + 1));
            cpf = partial + twoFakeDigits;
        }

        return formatCPF(cpf);
    }

    private String formatCPF(String cpf) {
        String block1 = cpf.substring(0, 3);
        String block2 = cpf.substring(3, 6);
        String block3 = cpf.substring(6, 9);
        String block4 = cpf.substring(9, 11);

        return  String.format("%s.%s.%s-%s", block1, block2, block3, block4);
    }

}
