package com.github.javafaker.idnumbers.pt.br;

public class DocumentFormatterUtil {

    private DocumentFormatterUtil() {
    }

    private static final String PATTERN_CPF = "([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})";

    private static final String PATTERN_CNPJ = "([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})";

    public static String cnpj(String cnpj) {
        return cnpj.replaceAll(PATTERN_CNPJ, "$1\\.$2\\.$3/$4-$5");
    }

    public static String cpf(String cpf) {
        return cpf.replaceAll(PATTERN_CPF, "$1\\.$2\\.$3-$4");
    }

    public static String unmask(String doc) {
        return doc.replaceAll("\\D+", "");
    }

}
