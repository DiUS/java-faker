package com.github.javafaker;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class GenderTest extends AbstractFakerTest {

    @Test
    public void testType() { assertThat(faker.gender().types(), matchesRegularExpression("([\\w-]+ ?)+")); }

    @Test
    public void testBinarytypes() { assertThat(faker.gender().binarytypes(), matchesRegularExpression("([\\w-]+ ?)+")); }

}
