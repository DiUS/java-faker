
package com.github.javafaker.idnumbers;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PtBRIdNumberTest {

    PtBRIdNumber idNumber = new PtBRIdNumber();

    @Test
    public void valid() {
        assertThat(this.idNumber.isValid("944.551.620-62"), is(true));
        assertThat(this.idNumber.isValid("664.018.170-38"), is(true));
        assertThat(this.idNumber.isValid("929.707.580-08"), is(true));
        assertThat(this.idNumber.isValid("713.478.380-40"), is(true));
        assertThat(this.idNumber.isValid("824.041.740-02"), is(true));
        assertThat(this.idNumber.isValid("094.265.470-61"), is(true));
        assertThat(this.idNumber.isValid("22437128069"), is(true));
        assertThat(this.idNumber.isValid("28922257016"), is(true));
        assertThat(this.idNumber.isValid("42133456023"), is(true));
        assertThat(this.idNumber.isValid("61011415003"), is(true));
    }

    @Test
    public void invalid() {
        assertThat(this.idNumber.isValid("8112289873a"), is(false));
        assertThat(this.idNumber.isValid("foo228-9873"), is(false));
        assertThat(this.idNumber.isValid("811228-9873"), is(false));
        assertThat(this.idNumber.isValid("811228-9875"), is(false));
        assertThat(this.idNumber.isValid("811200-9874"), is(false));
        assertThat(this.idNumber.isValid("810028-9874"), is(false));
        assertThat(this.idNumber.isValid("810028-9874aa"), is(false));
        assertThat(this.idNumber.isValid(""), is(false));
        assertThat(this.idNumber.isValid(null), is(false));
    }
}
