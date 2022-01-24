package com.github.javafaker;

import com.github.javafaker.idnumbers.pt.br.IdNumberGeneratorPtBrUtil;

/**
 * CNPJ: The Federal Government interest in simplifying its registration procedures on companies appears in the mid-90's, but it was only in 1998,
 * through
 * the SRF Normative Instruction No. 27, that the CNPJ (short for Cadastro Nacional da Pessoa Jurídica in Portuguese, or 'National Registry of Legal
 *  Entities')
 *  was created, replacing the former system, CGC (short for Cadastro Geral de Contribuintes in Portuguese, or 'General Taxpayers Registry').
 *  At the end of 2003 it gained a new impulse by sharing and integrating registration data and fiscal information between States and the Union.
 *  In 2009 the MEI (Microempreendedor Individual in Portuguese, or 'Individual Microentrepreneur') was created to supply market demand.
 * @see  <a href="https://en.wikipedia.org/wiki/CNPJ">CNPJ</a>
 */
public class CNPJ {

    protected CNPJ() {
    }

    /**
     * Return valid and formatted
     *
     * @return a valid CNPJ
     * @see IdNumberGeneratorPtBrUtil#cnpj(boolean, boolean)
     */
    public String valid() {
        return IdNumberGeneratorPtBrUtil.cnpj(true, true);
    }

    /**
     * Return valid and formatted
     *
     * @param formatted a CNPJ (un)formatted
     * @return a valid CNPJ
     * @see IdNumberGeneratorPtBrUtil#cnpj(boolean, boolean)
     */
    public String valid(boolean formatted) {
        return IdNumberGeneratorPtBrUtil.cnpj(formatted, true);
    }

    /**
     * Return invalid and formatted
     *
     * @return an invalid CNPJ
     * @see IdNumberGeneratorPtBrUtil#cnpj(boolean, boolean)
     */
    public String invalid() {
        return IdNumberGeneratorPtBrUtil.cnpj(true, false);
    }

    /**
     * Return invalid and (un)formatted
     *
     * @return an invalid CNPJ
     * @see IdNumberGeneratorPtBrUtil#cnpj(boolean, boolean)
     */
    public String invalid(boolean formatted) {
        return IdNumberGeneratorPtBrUtil.cnpj(formatted, false);
    }

}
