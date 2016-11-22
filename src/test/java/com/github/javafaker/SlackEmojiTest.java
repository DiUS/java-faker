package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class SlackEmojiTest extends AbstractFakerTest {

    private static final String EMOTICON_REGEX = ":([\\w-]+):";

    @Test
    public void people() {
        assertThat(faker.slackEmoji().people(), matchesRegularExpression(EMOTICON_REGEX));
    }

    @Test
    public void nature() {
        assertThat(faker.slackEmoji().nature(), matchesRegularExpression(EMOTICON_REGEX));
    }

    @Test
    public void food_and_drink() {
        assertThat(faker.slackEmoji().foodAndDrink(), matchesRegularExpression(EMOTICON_REGEX));
    }

    @Test
    public void celebration() {
        assertThat(faker.slackEmoji().celebration(), matchesRegularExpression(EMOTICON_REGEX));
    }

    @Test
    public void activity() {
        assertThat(faker.slackEmoji().activity(), matchesRegularExpression(EMOTICON_REGEX));
    }

    @Test
    public void travel_and_places() {
        assertThat(faker.slackEmoji().travelAndPlaces(), matchesRegularExpression(EMOTICON_REGEX));
    }

    @Test
    public void objects_and_symbols() {
        assertThat(faker.slackEmoji().objectsAndSymbols(), matchesRegularExpression(EMOTICON_REGEX));
    }

    @Test
    public void custom() {
        assertThat(faker.slackEmoji().custom(), matchesRegularExpression(EMOTICON_REGEX));
    }

    @Test
    public void emoji() {
        assertThat(faker.slackEmoji().emoji(), matchesRegularExpression(EMOTICON_REGEX));
    }
}
