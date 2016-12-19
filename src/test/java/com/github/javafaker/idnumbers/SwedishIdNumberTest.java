package com.github.javafaker.idnumbers;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SwedishIdNumberTest {

    @Test
    public void valid() {
        SvSEIdNumber idNumber = new SvSEIdNumber();
        assertThat(idNumber.validSwedishSsn("670919-9530"), is(true));
        assertThat(idNumber.validSwedishSsn("811228-9874"), is(true));
    }

    @Test
    public void invalid() {
        SvSEIdNumber idNumber = new SvSEIdNumber();
        assertThat(idNumber.validSwedishSsn("8112289873"), is(false));
        assertThat(idNumber.validSwedishSsn("foo228-9873"), is(false));
        assertThat(idNumber.validSwedishSsn("811228-9873"), is(false));
        assertThat(idNumber.validSwedishSsn("811228-9875"), is(false));
        assertThat(idNumber.validSwedishSsn("811200-9874"), is(false));
        assertThat(idNumber.validSwedishSsn("810028-9874"), is(false));
    }
}
