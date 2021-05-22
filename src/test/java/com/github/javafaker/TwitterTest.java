package com.github.javafaker;

import org.junit.Test;

import java.util.Date;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.*;

//CS304 issue link: https://github.com/DiUS/java-faker/issues/414
public class TwitterTest extends AbstractFakerTest {
    /**
     * Test for creating dates (forward)
     */
    @Test
    public void testCreatedDateForward() {
        Date testDate = new Date();
        Date constrainDate = new Date(testDate.getTime() + 3000000);
        Date generated = faker.twitter().created_time(true, testDate, constrainDate);
        boolean test = generated.after(testDate) && generated.before(constrainDate);
        assertTrue(test);
    }
    /**
     * Test for creating dates (backward)
     */
    @Test
    public void testCreatedDateBackward() {
        Date testDate = new Date();
        Date constrainDate = new Date(testDate.getTime() - 3000000);
        Date generated = faker.twitter().created_time(false, testDate, constrainDate);
        boolean test = generated.before(testDate) && generated.after(constrainDate);
        assertTrue(test);
    }
    /**
     * Test for faking twitter id length
     */
    @Test
    public void testTwitterIDLength() {
        int expectedLength = 15;
        String generatedID = faker.twitter().twitter_id(expectedLength);
        assertTrue(generatedID.length() == expectedLength);
    }
    /**
     * Test for faking twitter id unique
     */
    @Test
    public void testTwitterIDUnique() {
        int expectedLength = 15;
        String generatedIDOne = faker.twitter().twitter_id(expectedLength);
        String generatedIDTwo = faker.twitter().twitter_id(expectedLength);
        assertFalse(generatedIDOne.equals(generatedIDTwo));
    }
    /**
     * Test for faking twitter text length
     */
    @Test
    public void testTextLength() {
        int sentenceMaxLength = 15;
        int wordMaxLength = 5;
        String text =  faker.twitter().text(null, sentenceMaxLength, wordMaxLength);
        String[] textwords = text.split(" ");
        assertTrue(textwords.length <= sentenceMaxLength);
    }
    /**
     * Test for faking twitter text containing key words
     */
    @Test
    public void testTextKeyWords() {
        int sentenceMaxLength = 15;
        int wordMaxLength = 5;
        String[] keywords = new String[]{"buy", "see"};
        String text =  faker.twitter().text(keywords, sentenceMaxLength, wordMaxLength);
        String[] textwords = text.split(" ");
        boolean flag = true;
        for(int i=0; i<keywords.length; i++){
            boolean tmpFlag = false;
            for(int j=0; j<textwords.length; j++){
                if(keywords[i].equals(textwords[j])){
                    tmpFlag = true;
                }
            }
            flag = flag && tmpFlag;
            if(flag == false){
                break;
            }
        }
        assertTrue(flag);
    }
    /**
     * Test for faking twitter user name
     */
    @Test
    public void user_name_test() {
        assertThat(faker.twitter().user_name(), matchesRegularExpression("[a-zA-Z0-9_\\-\u4e00-\u9fa5]+"));
    }
    /**
     * Test for faking twitter user id
     */
    @Test
    public void user_id_test() {
        assertThat(faker.twitter().user_id(), matchesRegularExpression("[0-9]+"));
    }
    /**
     * Test for faking twitter link
     */
    @Test
    public void linkTest() {
        for (int i = 0; i < 10; i++) {
            assertThat(faker.twitter().get_link("John", 6), matchesRegularExpression("[A-Za-z0-9.:/]+"));
        }
    }
}
