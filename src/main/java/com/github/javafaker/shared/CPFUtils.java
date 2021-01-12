package com.github.javafaker.shared;

/**
 * Utility class used by CPF and CPFTest centralize shared methods
 */
public class CPFUtils {

    public static String calculateVerificationDigit(String num) {

        Integer primDig, segDig;
        int sum = 0, peso = 10;
        for (int i = 0; i < num.length(); i++)
            sum += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        if (sum % 11 == 0 | sum % 11 == 1)
            primDig = new Integer(0);
        else
            primDig = new Integer(11 - (sum % 11));

        sum = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++)
            sum += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        sum += primDig.intValue() * 2;
        if (sum % 11 == 0 | sum % 11 == 1)
            segDig = new Integer(0);
        else
            segDig = new Integer(11 - (sum % 11));

        return primDig.toString() + segDig.toString();
    }

    /**
     * Unformat CPF <br>
     * E.g: CPF, valid or invalid, return a formatted String: 284.041.598-40
     * @param cpf formated cpf
     * @return a unformatted CPF: 28404159840
     */
    public static String unformatCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }
}
