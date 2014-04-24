package com.github.javafaker;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.join;

public class Lorem {

    private final FakeValuesService fakeValuesService;
    private final RandomService randomService;

    public Lorem(FakeValuesService fakeValuesService, RandomService randomService) {
        this.fakeValuesService = fakeValuesService;
        this.randomService = randomService;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public List<String> words(int num) {
        List<String> words = (List<String>) fakeValuesService.fetchObject("lorem.words");
        List<String> returnList = new ArrayList();
        for (int i = 0; i < num; i++) {
            returnList.add(words.get(randomService.nextInt(words.size())));
        }
        return returnList;
    }

    public List<String> words() {
        return words(3);
    }

    public String sentence(int wordCount) {
        return capitalize(join(words(wordCount + randomService.nextInt(6)), " ") + ".");
    }

    public String sentence() {
        return sentence(3);
    }

    public List<String> sentences(int sentenceCount) {
        List<String> sentences = new ArrayList<String>(sentenceCount);
        for (int i = 0; i < sentenceCount; i++) {
            sentences.add(sentence());
        }
        return sentences;
    }

    public String paragraph(int sentenceCount) {
        return join(sentences(sentenceCount + randomService.nextInt(3)), " ");
    }

    public String paragraph() {
        return paragraph(3);
    }

    public List<String> paragraphs(int paragraphCount) {
        List<String> paragraphs = new ArrayList<String>(paragraphCount);
        for (int i = 0; i < paragraphCount; i++) {
            paragraphs.add(paragraph());
        }
        return paragraphs;
    }

    /**
     * Create a string with a fixed size. Can be useful for testing
     * validator based on length string for example
     *
     * @param numberOfLetters size of the expected String
     * @return a string with a fixed size
     */
    public String fixedString(int numberOfLetters) {
        StringBuilder builder = new StringBuilder();
        while (builder.length() < numberOfLetters) {
            builder.append(sentence());
        }
        return StringUtils.substring(builder.toString(), 0, numberOfLetters);
    }
}
