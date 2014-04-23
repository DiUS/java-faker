package com.github.javafaker;

import org.junit.Test;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoremTest extends AbstractFakerTest {

    public LoremTest(Locale locale, Random random) {
        super(locale, random);
    }

    @Test
    public void testSpecifiedNumOfWords() {
        List<String> words = faker.words(4);
        logger.info("Test words: " + words.toString());
        assertEquals(4, words.size());

        words = faker.lorem().words(4);
        logger.info("Test words: " + words.toString());
        assertEquals(4, words.size());
    }

    @Test
    public void testSentence() {
        String sentence = faker.sentence(7);
        logger.info("Test sentence: " + sentence);
        assertNotNull(sentence);

        sentence = faker.lorem().sentence(7);
        logger.info("Test sentence: " + sentence);
        assertNotNull(sentence);
    }

    @Test
    public void testSentences() {
        List<String> sentences = faker.sentences(3);
        logger.info("Test sentences: " + sentences);
        assertEquals(3, sentences.size());

        sentences = faker.lorem().sentences(3);
        logger.info("Test sentences: " + sentences);
        assertEquals(3, sentences.size());
    }

    @Test
    public void testSpecifiedNumOfParagraphs() {
        String paragraph = faker.paragraph(5);
        logger.info("Test paragraph: " + paragraph);
        assertNotNull(paragraph);

        paragraph = faker.lorem().paragraph(5);
        logger.info("Test paragraph: " + paragraph);
        assertNotNull(paragraph);
    }

    @Test
    public void testParagraph() {
        String paragraph = faker.paragraph();
        logger.info("Paragraph: " + paragraph);
        assertNotNull(paragraph);

        paragraph = faker.lorem().paragraph();
        logger.info("Paragraph: " + paragraph);
        assertNotNull(paragraph);
    }

    @Test
    public void testParagraphs() {
        final int paragraphCount = 10;
        List<String> paragraphs = faker.paragraphs(paragraphCount);
        logger.info("Paragraphs: " + paragraphs);
        assertEquals(paragraphCount, paragraphs.size());

        paragraphs = faker.lorem().paragraphs(paragraphCount);
        logger.info("Paragraphs: " + paragraphs);
        assertEquals(paragraphCount, paragraphs.size());
    }
}
