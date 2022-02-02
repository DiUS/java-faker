package com.github.javafaker;

import com.github.javafaker.idnumbers.EnIdNumber;
import com.github.javafaker.idnumbers.EsMxIdNumber;
import com.github.javafaker.idnumbers.SvSEIdNumber;

public class IdNumber {
    private final Faker faker;

    protected IdNumber(Faker faker) {
        this.faker = faker;
    }

    public String valid() {
        return faker.fakeValuesService().resolve("id_number.valid", this, faker);
    }

    public String invalid() {
        return faker.numerify(faker.fakeValuesService().resolve("id_number.invalid", this, faker));
    }

    public String ssnValid() {
        EnIdNumber enIdNumber = new EnIdNumber();
        return enIdNumber.getValidSsn(faker);
    }

    /**
     * Specified as #{IDNumber.valid_sv_se_ssn} in sv-SE.yml
     */
    public String validSvSeSsn() {
        SvSEIdNumber svSEIdNumber = new SvSEIdNumber();
        return svSEIdNumber.getValidSsn(faker);
    }

    /**
     * Specified as #{IDNumber.invalid_sv_se_ssn} in sv-SE.yml
     */
    public String invalidSvSeSsn() {
        SvSEIdNumber svSEIdNumber = new SvSEIdNumber();
        return svSEIdNumber.getInvalidSsn(faker);
    }

    /**
     * Specified as #{IDNumber.valid_es_mx_ssn} in es-MX.yml
     * @return A valid MEX CURP
     */
    public String validEsMxSsn() {
        EsMxIdNumber svSEIdNumber = new EsMxIdNumber();
        return svSEIdNumber.getValidSsn(faker);
    }

    /**
     * Specified as #{IDNumber.invalid_es_mx_ssn} in sv-SE.yml
     * @return An invalid MEX CURP
     */
    public String invalidEsMxSsn() {
        EsMxIdNumber svSEIdNumber = new EsMxIdNumber();
        return svSEIdNumber.getInvalidSsn(faker);
    }
}
