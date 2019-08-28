package com.github.javafaker.idnumbers;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ZhTWIdNumberTest {

    @Test
    public void valid() {
        ZhTWIdNumber idNumber = new ZhTWIdNumber();
        assertThat(idNumber.validIdNumber("A123456789"), is(true));
        assertThat(idNumber.validIdNumber("E242419408"), is(true));
    }

    @Test
    public void invalid() {
        ZhTWIdNumber idNumber = new ZhTWIdNumber();
        assertThat(idNumber.validIdNumber("a21448034"), is(false));
        assertThat(idNumber.validIdNumber("E-314480349"), is(false));
        assertThat(idNumber.validIdNumber("Z21448034"), is(false));
        assertThat(idNumber.validIdNumber("ABCDEGFTHD"), is(false));
        assertThat(idNumber.validIdNumber("ABC-998"), is(false));
    }
}
