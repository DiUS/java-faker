package com.github.javafaker.idnumbers;

import com.github.javafaker.Number;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class DNIIdNumberTest {

    private static final int NUMBER_OF_DNI_DIGITS = 8;

    private static final String DNI_REGULAR_EXPRESSION = "^[0-9]{8,8}-[A-Za-z]$";

    private DNIIdNumber dniIdNumber;

    @Mock
    private Number number;

    @Before
    public void setup() {
        this.dniIdNumber = new DNIIdNumber(number);
    }

    @Test
    public void testGenerate() {
        // given
        Long randomNumber = 99999999L;

        given(number.randomNumber(NUMBER_OF_DNI_DIGITS, true)).willReturn(randomNumber);
        // when
        String dniNumber = dniIdNumber.generate();

        // then
        String expectedDniNumber = "99999999-R";
        assertThat(dniNumber, matchesRegularExpression(DNI_REGULAR_EXPRESSION));
        assertThat(expectedDniNumber, equalTo(dniNumber));
    }

    @Test
    public void testGenerateWhenInformedSequenceNumbers() {
        // given
        Long randomNumber = 12345678L;

        given(number.randomNumber(NUMBER_OF_DNI_DIGITS, true)).willReturn(randomNumber);
        // when
        String dniNumber = dniIdNumber.generate();

        // then
        String expectedDniNumber = "12345678-Z";
        assertThat(dniNumber, matchesRegularExpression(DNI_REGULAR_EXPRESSION));
        assertThat(expectedDniNumber, equalTo(dniNumber));
    }
}
