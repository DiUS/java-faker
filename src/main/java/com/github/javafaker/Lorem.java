package com.github.javafaker;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.join;

public class Lorem {
    private final Faker faker;

    protected Lorem(Faker faker) {
        this.faker = faker;
    }
    
    public char character() {
        return character(false);
    }

    public char character(boolean includeUppercase) {
        return characters(1, includeUppercase).charAt(0);
    }

    public String characters() {
        return characters(255, false);
    }

    public String characters(boolean includeUppercase) {
        return characters(255, includeUppercase);
    }

    public String characters(int minimumLength, int maximumLength) {
        return characters(faker.random().nextInt(maximumLength - minimumLength) + minimumLength, false);
    }

    public String characters(int minimumLength, int maximumLength, boolean includeUppercase) {
        return characters(faker.random().nextInt(maximumLength - minimumLength) + minimumLength, includeUppercase);
    }

    public String characters(int minimumLength, int maximumLength, boolean includeUppercase, boolean includeDigit) {
        return characters(faker.random().nextInt(maximumLength - minimumLength) + minimumLength, includeUppercase, includeDigit);
    }

    public String characters(int fixedNumberOfCharacters) {
        return characters(fixedNumberOfCharacters, false);
    }

    public String characters(int fixedNumberOfCharacters, boolean includeUppercase) {
        return characters(fixedNumberOfCharacters, includeUppercase, true);
    }

    public String characters(int minimumLength, int maximumLength,
                             boolean includeUppercase, boolean includeSpecial, boolean includeDigit) {
        return characters(faker.random().nextInt(maximumLength - minimumLength) + minimumLength,
                          includeUppercase, includeSpecial, includeDigit);
    }


    public String characters(int fixedNumberOfCharacters, boolean includeUppercase, boolean includeDigit) {
        return characters(fixedNumberOfCharacters,includeUppercase,false,includeDigit);
    }

    public String characters(int fixedNumberOfCharacters,
                             boolean includeUppercase, boolean includeSpecial, boolean includeDigit) {

        if(fixedNumberOfCharacters < 1)
            return "";

        char[] buffer = new char[fixedNumberOfCharacters];
        char[] special = new char[]{'!', '@', '#', '$', '%', '^', '&', '*'};
        char[] number = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] All = (new String(special) + new String(characters)).toCharArray();
        char[] SpecialAndLetter = (new String(special) + new String(letters)).toCharArray();

        int cnt = 0;
        if(includeUppercase){
            char TheUpper = Character.toUpperCase(letters[faker.random().nextInt(letters.length)]);
            if(cnt > fixedNumberOfCharacters - 1) return "";
            buffer[cnt++] = TheUpper;

        }

        if(includeSpecial){
            char TheSpecial =  special[faker.random().nextInt(special.length)];
            if(cnt > fixedNumberOfCharacters - 1) return "";
            buffer[cnt++] = TheSpecial;
        }

        if(includeDigit) {
            char TheNum =  number[faker.random().nextInt(number.length)];
            if(cnt > fixedNumberOfCharacters - 1) return "";
            buffer[cnt++] = TheNum;
        }


        for (int i = cnt; i < buffer.length; i++) {
            char randomCharacter;

            if (includeSpecial && !includeDigit){
                randomCharacter = SpecialAndLetter[faker.random().nextInt(SpecialAndLetter.length)];
            }
            else if (!includeSpecial && includeDigit ) {
                randomCharacter = characters[faker.random().nextInt(characters.length)];
            }
            else if (!includeSpecial && !includeDigit) {
                randomCharacter = letters[faker.random().nextInt(letters.length)];
            }
            else {                                            //includeSpecial && includeDigit
                randomCharacter = All[faker.random().nextInt(All.length)];
            }

            if (includeUppercase && faker.bool().bool()) {
                randomCharacter = Character.toUpperCase(randomCharacter);
            }
            buffer[i] = randomCharacter;
        }

        shuffle(buffer);
        return new String(buffer);
    }

    private void shuffle(char[] buffer){
        int length = buffer.length;
        for ( int i = length; i > 0; i-- ){
            int randInd = faker.random().nextInt(i);
            swap(buffer, randInd, i - 1);
        }
    }

    private void swap(char[] a, int i, int j){
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }



    public List<String> words(int num) {
        List<String> returnList = new ArrayList<String>();
        for (int i = 0; i < num; i++) {
            returnList.add(word());
        }
        return returnList;
    }

    public List<String> words() {
        return words(3);
    }

    public String word() {
        return faker.fakeValuesService().resolve("lorem.words", this, faker);
    }

    /**
     * Create a sentence with a random number of words within the range 4..10.
     * @return a random sentence
     */
    public String sentence() {
        return sentence(3);
    }

    /**
     * Create a sentence with a random number of words within the range (wordCount+1)..(wordCount+6).
     * @param wordCount
     * @return a random sentence
     */
    public String sentence(int wordCount) {
        return sentence(wordCount, 6);
    }

    /**
     * Create a sentence with a random number of words within the range (wordCount+1)..(wordCount+randomWordsToAdd).</p>
     * 
     * Set {@code randomWordsToAdd} to 0 to generate sentences with a fixed number of words.
     * @param wordCount
     * @param randomWordsToAdd
     * @return a random sentence
     */
    public String sentence(int wordCount, int randomWordsToAdd) {
        int numberOfWordsToAdd = randomWordsToAdd == 0 ? 0 : faker.random().nextInt(randomWordsToAdd);
        return capitalize(join(words(wordCount + numberOfWordsToAdd), " ") + ".");
    }

    public List<String> sentences(int sentenceCount) {
        List<String> sentences = new ArrayList<String>(sentenceCount);
        for (int i = 0; i < sentenceCount; i++) {
            sentences.add(sentence());
        }
        return sentences;
    }

    public String paragraph(int sentenceCount) {
        return join(sentences(sentenceCount + faker.random().nextInt(3)), " ");
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

    static {
        StringBuilder builder = new StringBuilder(36);
        for (char character = 'a'; character <= 'z'; character++) {
            builder.append(character);
        }
        letters = builder.toString().toCharArray();
        for (char number = '0'; number <= '9'; number++) {
            builder.append(number);
        }
        characters = builder.toString().toCharArray();
    }

    private static final char[] letters;
    private static final char[] characters;

}
