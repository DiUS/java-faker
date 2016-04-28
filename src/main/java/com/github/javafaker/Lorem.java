package com.github.javafaker;

import com.github.javafaker.service.FakeValuesServiceInterface;
import com.github.javafaker.service.RandomService;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.join;

public class Lorem {

    static {
        StringBuilder builder = new StringBuilder(36);
        for (char number = '0'; number <= '9'; number++) {
            builder.append(number);
        }
        for (char character = 'a'; character <= 'z'; character++) {
            builder.append(character);
        }
        characters = builder.toString().toCharArray();
    }

    private static final char[] characters;

    private final FakeValuesServiceInterface fakeValuesService;
    private final RandomService randomService;

    public Lorem(FakeValuesServiceInterface fakeValuesService, RandomService randomService) {
        this.fakeValuesService = fakeValuesService;
        this.randomService = randomService;
    }

    public char character() {
        return characters(1).charAt(0);
    }

    public String characters() {
        return characters(255);
    }

    public String characters(int fixedNumberOfCharacters) {
        if (fixedNumberOfCharacters < 1) {
            return "";
        }
        char[] buffer = new char[fixedNumberOfCharacters];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = characters[randomService.nextInt(characters.length)];
        }
        return new String(buffer);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<String> words(int num) {
        List<String> returnList = new ArrayList();
        for (int i = 0; i < num; i++) {
            returnList.add(word());
        }
        return returnList;
    }

    public List<String> words() {
        return words(3);
    }

    public String word() {
        return fakeValuesService.safeFetch("lorem.words");
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
