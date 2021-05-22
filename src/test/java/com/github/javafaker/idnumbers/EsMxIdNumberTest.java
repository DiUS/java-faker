package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Es mx id number test.
 */
public class EsMxIdNumberTest {

    /**
     * Check whether MEX CURP is valid.
     */
    @Test
    public void checkValid() {
        EsMxIdNumber idNumber = new EsMxIdNumber();
        assertThat(idNumber.validEsMxSsn("ZAZD801124MBSYQN13"), is(true));
        assertThat(idNumber.validEsMxSsn("RUNO390705HSLPTC02"), is(true));
    }

    /**
     * Check whether MEX CURP's length is valid.
     */
    @Test
    public void testCheckLength() {
        EsMxIdNumber idNumber = new EsMxIdNumber();
        assertThat(idNumber.validEsMxSsn("IMS120607120"), is(false));
    }

    /**
     * Check whether MEX CURP's date part is valid.
     */
    @Test
    public void testCheckDate() {
        EsMxIdNumber idNumber = new EsMxIdNumber();
        assertThat(idNumber.validEsMxSsn("KAOB750230MASLHJ07"), is(false));
    }

    /**
     * Check whether gender part is valid.
     * The gender bit should be 'H' or 'M'.
     */
    @Test
    public void testCheckGender() {
        EsMxIdNumber idNumber = new EsMxIdNumber();
        assertThat(idNumber.validEsMxSsn("EOMR330613FDFPKT08"), is(false));
    }

    /**
     * Check whether the checksum is valid.
     * The checksum should be a digit.
     */
    @Test
    public void testCheckChecksum() {
        EsMxIdNumber idNumber = new EsMxIdNumber();
        assertThat(idNumber.validEsMxSsn("EIMV310211MQRPKT1X"), is(false));
    }

    /**
     * Check whether name part is valid.
     * There should be all capital letters.
     */
    @Test
    public void testCheckName() {
        EsMxIdNumber idNumber = new EsMxIdNumber();
        assertThat(idNumber.validEsMxSsn("L6NF640805MYNTNN10"), is(false));
    }

    /**
     * Test whether generated valid MEX CURP is valid.
     */
    @Test
    public void testGenerateValid(){
        EsMxIdNumber idNumber = new EsMxIdNumber();
        final Faker f = new Faker(new Locale("es_MX"));
        for (int i = 0; i < 100; i++) {
            assertThat(idNumber.validEsMxSsn(idNumber.getValidSsn(f)),is(true));

        }
    }

    /**
     * Test whether generated invalid MEX CURP is invalid.
     */
    @Test
    public void testGenerateInvalid(){
        EsMxIdNumber idNumber = new EsMxIdNumber();
        final Faker f = new Faker(new Locale("es_MX"));
        for (int i = 0; i < 100; i++) {
            assertThat(idNumber.validEsMxSsn(idNumber.getInvalidSsn(f)),is(false));
        }
    }
}
