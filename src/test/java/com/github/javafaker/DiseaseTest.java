package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

/**
 * @author LinZhiyuan bianzheng
 * the test that generate valid disease types
 * */
public class DiseaseTest extends AbstractFakerTest {
    @Test
    public void testTypes(){
        assertThat(faker.disease().type(),matchesRegularExpression("[A-Za-z ]+"));
        assertThat(faker.disease().type(),matchesRegularExpression("[A-Za-z'() 0-9-]+"));
    }

}