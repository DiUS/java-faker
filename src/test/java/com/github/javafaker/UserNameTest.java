package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class UserNameTest extends AbstractFakerTest{
    @Test //This is trying to test the boundary of the input parameters
    public void testUsernameBoundary() {
        String test = faker.name().username(6,25);
        assertThat(test, matchesRegularExpression("^(\\w+)\\.(\\w+)$"));
        assertThat(test.length(), allOf(greaterThanOrEqualTo(6), lessThanOrEqualTo(25)));
    }

    @Test //This test case is trying to test when min==max, in other words we produce the username in a fixed length
    public void testUsernameCase1(){
        String test = faker.name().username(12,12);
        assertThat(test, matchesRegularExpression("^(\\w+)\\.(\\w+)$"));
        assertThat(test.length(),equalTo(12));
    }
    
    @Test //This test case is trying to test the normal cases
    public void testUsernameCase2(){
        String test = faker.name().username(17,20);
        assertThat(test, matchesRegularExpression("^(\\w+)\\.(\\w+)$"));
        assertThat(test.length(), allOf(greaterThanOrEqualTo(17), lessThanOrEqualTo(20)));
    }
    
    @Test //This test case is trying to test when the max < 6 or min >25, then we will return an error message
    public void OutOfBoundary(){
        String test1 = faker.name().username(1,5);
        String test2 = faker.name().username(26,30);
        assertThat(test1, equalToIgnoringWhiteSpace("the min should not be greater than 25 and the max should not be smaller than 6"));
        assertThat(test2, equalToIgnoringWhiteSpace("the min should not be greater than 25 and the max should not be smaller than 6"));
    }

    @Test //This test case is trying to test when min>max, then we will return an error message
    public void errorWithMinAndMax(){
        String test = faker.name().username(10,8);
        assertThat(test, equalToIgnoringWhiteSpace("max should not be smaller than min"));
    }
}
