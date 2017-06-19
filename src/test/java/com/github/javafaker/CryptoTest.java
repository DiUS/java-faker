package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;


public class CryptoTest extends AbstractFakerTest {

    @Test
    public void testMd5() {
        assertThat(faker.crypto().md5(), matchesRegularExpression("[a-z\\d]+"));
    }

    @Test
    public void testSha1() {
        assertThat(faker.crypto().sha1(), matchesRegularExpression("[a-z\\d]+"));
    }

    @Test
    public void testSha256() {
        assertThat(faker.crypto().sha256(), matchesRegularExpression("[a-z\\d]+"));
    }

    @Test
    public void testSha512() {
        assertThat(faker.crypto().sha512(), matchesRegularExpression("[a-z\\d]+"));
    }
}
