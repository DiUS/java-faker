package com.github.javafaker;

import com.github.javafaker.idnumbers.EnIdNumber;
import com.github.javafaker.idnumbers.SvSEIdNumber;
import com.github.javafaker.idnumbers.ZhCNIdNumber;

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

    //CS304 Issue link: https://github.com/DiUS/java-faker/issues/588
    /**
     * Asks for a valid Chinese ID number.
     * Specified as "#{IDNumber.zh_cn_ssn_valid}" in zh-CN.yml.
     * @return A valid Chinese ID number.
     */
    public String zhCnSsnValid() {
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();
        return zhCNIdNumber.getValidCN(faker);
    }

    /**
     * Asks for an invalid Chinese ID number.
     * Specified as "#{IDNumber.zh_cn_ssn_in_valid}" in zh-CN.yml.
     * @return An invalid Chinese ID number.
     */
    public String zhCnSsnInValid() {
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();
        return zhCNIdNumber.getInvalidCN(faker);
    }
}
