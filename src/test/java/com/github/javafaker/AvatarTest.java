package com.github.javafaker;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.javafaker.repeating.Repeat;

public class AvatarTest extends AbstractFakerTest {

    @Test
    @Repeat(times=10)
    public void testAvatar() {
        String avatar = faker.avatar().image();
        assertThat(avatar, matchesRegularExpression("^https:\\/\\/s3.amazonaws\\.com\\/uifaces\\/faces\\/twitter\\/[a-zA-Z0-9_]+\\/128\\.jpg$"));
    }
}
