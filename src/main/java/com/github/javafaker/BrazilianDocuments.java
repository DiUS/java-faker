package com.github.javafaker;

import java.util.InputMismatchException;

public class BrazilianDocuments {

    private int randomiza(int n) {
      return (int) (Math.random() * n);
    }

    private int mod(int dividendo, int divisor) {
      return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    public String cpf(boolean withPunctuation) {
      int n = 9;
      int n1 = randomiza(n);
      int n2 = randomiza(n);
      int n3 = randomiza(n);
      int n4 = randomiza(n);
      int n5 = randomiza(n);
      int n6 = randomiza(n);
      int n7 = randomiza(n);
      int n8 = randomiza(n);
      int n9 = randomiza(n);
      int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

      d1 = 11 - (mod(d1, 11));

      if (d1 >= 10)
        d1 = 0;

      int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

      d2 = 11 - (mod(d2, 11));

      String retorno;

      if (d2 >= 10)
        d2 = 0;

      if (withPunctuation)
        retorno = "" + n1 + n2 + n3 + '.' + n4 + n5 + n6 + '.' + n7 + n8 + n9 + '-' + d1 + d2;
      else
        retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;

      return retorno;
    }

    public String cnpj(boolean withPunctuation) {
      int n = 9;
      int n1 = randomiza(n);
      int n2 = randomiza(n);
      int n3 = randomiza(n);
      int n4 = randomiza(n);
      int n5 = randomiza(n);
      int n6 = randomiza(n);
      int n7 = randomiza(n);
      int n8 = randomiza(n);
      int n9 = 0;
      int n10 = 0;
      int n11 = 0;
      int n12 = 1;
      int d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;

      d1 = 11 - (mod(d1, 11));

      if (d1 >= 10)
        d1 = 0;

      int d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;

      d2 = 11 - (mod(d2, 11));

      if (d2 >= 10)
        d2 = 0;

      String retorno;

      if (withPunctuation)
        retorno = "" + n1 + n2 + "." + n3 + n4 + n5 + "." + n6 + n7 + n8 + "/" + n9 + n10 + n11 + n12 + "-" + d1 + d2;
      else
        retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2;

      return retorno;
    }

    public boolean isCPF(String CPF) {

      CPF = removeCaracteresEspeciais(CPF);

      // considera-se erro CPF's formados por uma sequencia de numeros iguais
      if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222") || CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555") || CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11))
        return (false);

      char dig10, dig11;
      int sm, i, r, num, peso;

      // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
      try {
        // Calculo do 1o. Digito Verificador
        sm = 0;
        peso = 10;
        for (i = 0; i < 9; i++) {
          // converte o i-esimo caractere do CPF em um numero:
          // por exemplo, transforma o caractere '0' no inteiro 0
          // (48 eh a posicao de '0' na tabela ASCII)
          num = (int) (CPF.charAt(i) - 48);
          sm = sm + (num * peso);
          peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
          dig10 = '0';
        else
          dig10 = (char) (r + 48); // converte no respectivo caractere numerico

        // Calculo do 2o. Digito Verificador
        sm = 0;
        peso = 11;
        for (i = 0; i < 10; i++) {
          num = (int) (CPF.charAt(i) - 48);
          sm = sm + (num * peso);
          peso = peso - 1;
        }

        r = 11 - (sm % 11);
        if ((r == 10) || (r == 11))
          dig11 = '0';
        else
          dig11 = (char) (r + 48);

        // Verifica se os digitos calculados conferem com os digitos informados.
        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
          return (true);
        else
          return (false);
      } catch (InputMismatchException erro) {
        return (false);
      }
    }

    public boolean isCNPJ(String CNPJ) {

      CNPJ = removeCaracteresEspeciais(CNPJ);

      // considera-se erro CNPJ's formados por uma sequencia de numeros iguais
      if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") || CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") || (CNPJ.length() != 14))
        return (false);

      char dig13, dig14;
      int sm, i, r, num, peso;

      // "try" - protege o código para eventuais erros de conversao de tipo (int)
      try {
        // Calculo do 1o. Digito Verificador
        sm = 0;
        peso = 2;
        for (i = 11; i >= 0; i--) {
          // converte o i-ésimo caractere do CNPJ em um número:
          // por exemplo, transforma o caractere '0' no inteiro 0
          // (48 eh a posição de '0' na tabela ASCII)
          num = (int) (CNPJ.charAt(i) - 48);
          sm = sm + (num * peso);
          peso = peso + 1;
          if (peso == 10)
            peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1))
          dig13 = '0';
        else
          dig13 = (char) ((11 - r) + 48);

        // Calculo do 2o. Digito Verificador
        sm = 0;
        peso = 2;
        for (i = 12; i >= 0; i--) {
          num = (int) (CNPJ.charAt(i) - 48);
          sm = sm + (num * peso);
          peso = peso + 1;
          if (peso == 10)
            peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1))
          dig14 = '0';
        else
          dig14 = (char) ((11 - r) + 48);

        // Verifica se os dígitos calculados conferem com os dígitos informados.
        if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
          return (true);
        else
          return (false);
      } catch (InputMismatchException erro) {
        return (false);
      }
    }

    private String removeCaracteresEspeciais(String doc) {
      if (doc.contains(".")) {
        doc = doc.replace(".", "");
      }
      if (doc.contains("-")) {
        doc = doc.replace("-", "");
      }
      if (doc.contains("/")) {
        doc = doc.replace("/", "");
      }
      return doc;
    }
}
