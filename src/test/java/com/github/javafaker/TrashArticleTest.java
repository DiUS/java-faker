package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.IsStringWithContents.isStringWithContents;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TrashArticleTest {

    @Test
    public void generateArticleTest() {
        Faker faker = new Faker();
        String topic = "trash";
        assertThat(faker.trashArticle().generateArticle(topic), isStringWithContents());
    }
    @Test
    public void generateArticleWithTopicTest() {
        Faker faker = new Faker();
        String topic = "trash";
        assertTrue(faker.trashArticle().generateArticle(topic).contains(topic));
    }
    @Test
    public void generateOneModuleTest() {
        Faker faker = new Faker();
        String topic = "trash";
        assertThat(faker.trashArticle().generateOneModule(topic,true), isStringWithContents());
        assertThat(faker.trashArticle().generateOneModule(topic,false), isStringWithContents());

    }
    @Test
    public void generateModuleWithTopicTest() {
        Faker faker = new Faker();
        String topic = "trash";
        assertTrue(faker.trashArticle().generateOneModule(topic,true).contains(topic));
    }
    @Test
    public void generateParagraphWithTopicTest() {
        Faker faker = new Faker();
        String topic = "trash";
        assertTrue(faker.trashArticle().generateParagraph(topic,true).contains(topic));
    }
    @Test
    public void generateParagraphTest() {
        Faker faker = new Faker();
        String topic = "trash";
        assertThat(faker.trashArticle().generateParagraph(topic,true), isStringWithContents());
        assertThat(faker.trashArticle().generateParagraph(topic,false), isStringWithContents());
    }
}
