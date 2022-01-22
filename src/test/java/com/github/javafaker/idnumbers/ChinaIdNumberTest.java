package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import com.github.javafaker.repeating.Repeat;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ChinaIdNumberTest {
    Faker faker = new Faker(new Locale("Zh-CN"));

    @Test
    @Repeat(times = 100)
    public void valid() {
        assertThat(CNidNumber.checkValidIDNumber(faker.idNumber().validCNID()), is(true));
    }

    @Test
    @Repeat(times = 100)
    public void invalid() {
        assertThat(CNidNumber.checkValidIDNumber(faker.idNumber().invalidCNID()), is(false));
    }


}
