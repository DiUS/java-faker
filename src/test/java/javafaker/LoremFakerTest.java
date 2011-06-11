package javafaker;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoremFakerTest {
    private static final Logger logger = LoggerFactory.getLogger(LoremFakerTest.class);

    @Test
    public void shouldReturnSpecifiedNumOfWords() {
        List<String> words = LoremFaker.words(4);
        logger.info("Test words: " + words.toString());
        Assert.assertEquals(4, words.size());
    }

    @Test
    public void printFakeSentence() {
        String sentence = LoremFaker.sentence(7);
        logger.info("Test sentence: " + sentence);
    }

    @Test
    public void printFakeSentences() {
        List<String> sentences = LoremFaker.sentences(3);
        logger.info("Test sentences: " + sentences);
    }

    @Test
    public void printFakeParagraph() {
        String paragraph = LoremFaker.paragraph(5);
        logger.info("Test paragraph: " + paragraph);
    }
}
