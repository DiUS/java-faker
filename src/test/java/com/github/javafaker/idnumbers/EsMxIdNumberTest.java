package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EsMxIdNumberTest {

    @Test
    public void checkValid() {
        EsMxIdNumber idNumber = new EsMxIdNumber();
        assertThat(idNumber.validEsMxSsn("ZAZD801124MBSYQN13"), is(true));
        assertThat(idNumber.validEsMxSsn("RUNO390705HSLPTC02"), is(true));
    }

    @Test
    public void checkInvalid() {
        EsMxIdNumber idNumber = new EsMxIdNumber();
        assertThat(idNumber.validEsMxSsn("IMS120607120"), is(false));
        assertThat(idNumber.validEsMxSsn("KAOB750230MASLHJ07"), is(false));
        assertThat(idNumber.validEsMxSsn("EOMR330613FDFPKT08"), is(false));
        assertThat(idNumber.validEsMxSsn("YOOR280801HXXLTK07"), is(false));
        assertThat(idNumber.validEsMxSsn("EIMV310211MQRPKT1X"), is(false));
        assertThat(idNumber.validEsMxSsn("L6NF640805MYNTNN10"), is(false));
    }

    @Test
    public void testGenerateValid(){
        EsMxIdNumber idNumber = new EsMxIdNumber();
        final Faker f = new Faker(new Locale("es_MX"));
        for (int i = 0; i < 100; i++) {
            assertThat(idNumber.validEsMxSsn(idNumber.getValidSsn(f)),is(true));

        }
    }
    @Test
    public void testGenerateInvalid(){
        EsMxIdNumber idNumber = new EsMxIdNumber();
        final Faker f = new Faker(new Locale("es_MX"));
        for (int i = 0; i < 100; i++) {
            assertThat(idNumber.validEsMxSsn(idNumber.getInvalidSsn(f)),is(false));

        }
    }
}
