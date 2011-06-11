package javafaker;

import static javafaker.Faker.fetchObject;
import static org.apache.commons.lang.StringUtils.capitalize;
import static org.apache.commons.lang.StringUtils.join;
import static org.apache.commons.lang.math.RandomUtils.nextInt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

public class LoremFaker {

    public static List<String> words(int num) {
        List<String> words = (List<String>) fetchObject("lorem.words");
        Collections.shuffle(words);
        return words.subList(0, num);
    }

    public static List<String> words() {
        return words(3);
    }

    public static String sentence(int wordCount) {
        return capitalize(join(words(wordCount + RandomUtils.nextInt(6)), " ") + ".");
    }

    public static String sentence() {
        return sentence(3);
    }

    public static List<String> sentences(int sentenceCount) {
        List<String> sentences = new ArrayList<String>(sentenceCount);
        for (int i = 0; i < sentenceCount; i++) {
            sentences.add(sentence());
        }
        return sentences;
    }

    public static String paragraph(int sentenceCount) {
        return join(sentences(sentenceCount + nextInt(3)), " ");
    }

    public static String paragraph() {
        return paragraph(3);
    }

    public static List<String> paragraphs(int paragraphCount) {
        List<String> paragraphs = new ArrayList<String>(paragraphCount);
        for (int i = 0; i < paragraphCount; i++) {
            paragraphs.add(paragraph());
        }
        return paragraphs;
    }
}
