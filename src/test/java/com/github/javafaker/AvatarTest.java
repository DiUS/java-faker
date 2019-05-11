package com.github.javafaker;

import org.junit.jupiter.api.RepeatedTest;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.MatcherAssert.assertThat;

public class AvatarTest extends AbstractFakerTest {

    @RepeatedTest(10)
    public void testAvatar() {
        String avatar = faker.avatar().image();
        assertThat(avatar, matchesRegularExpression("^https:\\/\\/s3.amazonaws\\.com\\/uifaces\\/faces\\/twitter\\/[a-zA-Z0-9_]+\\/128\\.jpg$"));
    }
}
