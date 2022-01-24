package com.github.javafaker;

import com.github.javafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil;

/**
 * The CPF number (Cadastro de Pessoas Físicas, [sepeˈɛfi]; Portuguese for "Natural Persons Register")
 * is the Brazilian individual taxpayer registry identification, since its creation in 1965. This
 * number is attributed by the Brazilian Federal Revenue to Brazilians and resident aliens who,
 * directly or indirectly, pay taxes in Brazil. It's an 11-digit number in the format 000.000.000-00.
 *
 * @see <a href="https://en.wikipedia.org/wiki/CPF_number">CPF</a>
 */
public class CPF {

    protected CPF() {
    }

    /**
     * Return valid and formatted
     *
     * @return a valid CPF
     * @see IdNumberGeneratorPtBrUtil#cpf(boolean, boolean)
     */
    public String valid() {
        return IdNumberGeneratorPtBrUtil.cpf(true, true);
    }

    /**
     * Return valid and formatted
     *
     * @param formatted a (un)formatted CPF
     * @return a valid CPF
     * @see IdNumberGeneratorPtBrUtil#cpf(boolean, boolean)
     */
    public String valid(boolean formatted) {
        return IdNumberGeneratorPtBrUtil.cpf(formatted, true);
    }

    /**
     * Return invalid and formatted
     *
     * @return an invalid CPF
     * @see IdNumberGeneratorPtBrUtil#cpf(boolean, boolean)
     */
    public String invalid() {
        return IdNumberGeneratorPtBrUtil.cpf(true, false);
    }

    /**
     * Return invalid and formatted
     *
     * @param formatted a (un)formatted CPF
     * @return an invalid CPF
     * @see IdNumberGeneratorPtBrUtil#cpf(boolean, boolean)
     */
    public String invalid(boolean formatted) {
        return IdNumberGeneratorPtBrUtil.cpf(formatted, false);
    }

}
