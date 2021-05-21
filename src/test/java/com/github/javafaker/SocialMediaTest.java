package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.*;

/*
 * This file was used to test the issue #414
 * Wed May 21 GMT 2021
 * by SE_CHWJ
 */
//CS304 issue link: https://github.com/DiUS/java-faker/issues/414
public class SocialMediaTest extends AbstractFakerTest{


    //Test BiliBili Social Media.
    @Test
    public void BiliBiliWebTest() {
        for (int i = 0; i < 10; i++) {
            assertThat(faker.socialMedia().getBiliBiliWeb(), matchesRegularExpression("[A-Za-z0-9.:/]+"));
        }
    }

    //Test BiliBili Social Media.
    @Test
    public void BiliBiliBVTest() {
        for (int i = 0; i < 10; i++) {
            assertThat(faker.socialMedia().getBiliBiliBV(), matchesRegularExpression("[A-Za-z0-9.:/]+"));
            assertEquals(10, faker.socialMedia().getBiliBiliBV().length());
        }

    }

    //Test Twitter Social Media.
    @Test
    public void TwitterWebTest() {
        for (int i = 0; i < 10; i++) {
            assertThat(faker.socialMedia().getTwitterWeb(), matchesRegularExpression("[A-Za-z0-9.:/]+"));
        }
    }

    //Test Twitter Social Media.
    @Test
    public void TwitterWebTest2() {
        for (int i = 0; i < 10; i++) {
            assertThat(faker.socialMedia().getTwitterWeb(), matchesRegularExpression("[A-Za-z0-9.:/]+"));
        }
    }

    //Test YouTuBe Social Media.
    @Test
    public void YouTuBeWebTest() {
        for (int i = 0; i < 10; i++) {
            assertThat(faker.socialMedia().getTwitterWeb(), matchesRegularExpression("[A-Za-z0-9.:/]+"));
        }
    }

    //Test YouTuBe Social Media.
    @Test
    public void YouTuBeWebTest2() {
        for (int i = 0; i < 10; i++) {
            assertThat(faker.socialMedia().getTwitterWeb(), matchesRegularExpression("[A-Za-z0-9.:/]+"));
        }
    }
}
