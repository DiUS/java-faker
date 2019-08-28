package com.github.javafaker;

import com.github.javafaker.idnumbers.EnIdNumber;
import com.github.javafaker.idnumbers.SvSEIdNumber;
import com.github.javafaker.idnumbers.ZhTWIdNumber;

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
     * Taiwan don't have ssn so use identity number
     * Specified as #{IDNumber.valid_zh_tw_id_number} in zh-TW.yml
     */
    public String validZhTwIdNumber() {
        ZhTWIdNumber zhTWIdNumber = new ZhTWIdNumber();
        return zhTWIdNumber.getValidIdNumber(faker);
    }

    /**
     * Taiwan don't have ssn so use identity number
     * Specified as #{IDNumber.invalid_zh_tw_id_number} in zh-TW.yml
     */
    public String invalidZhTwIdNumber() {
        ZhTWIdNumber zhTWIdNumber = new ZhTWIdNumber();
        return zhTWIdNumber.getInvalidIdNumber(faker);
    }
}
