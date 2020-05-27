package com.github.javafaker;

public class TrashArticle {
    private final Faker faker;
    protected TrashArticle(Faker faker){this.faker=faker;}

    /**
     * Generate a paragraph with specific topic
     * @param topic the specific topic
     * @param condition judge it is start paragraph or not
     * @return a paragraph.
     */
    public String generateParagraph(String topic, Boolean condition){
        StringBuilder paragraph= new StringBuilder();
        int moduleRange=faker.number().numberBetween(1,3);
        for(int i=0;i<moduleRange;i++){
            paragraph.append(generateOneModule(topic,condition));
        }
        paragraph.append("\n");
        return paragraph.toString();
    }

    /**
     * Generate an article with topic
     * @param topic the specific topic
     * @return an article with specific topic
     */
    public String generateArticle(String topic){
        StringBuilder total= new StringBuilder();
        while(total.length()<200){
            total.append(generateParagraph(topic,true));
        }
            total.append(generateParagraph(topic,false));
        String end = faker.fakeValuesService().resolve("trash_article.ends",this,faker).replace("TOPIC",topic);
        total.append(end);
        total.append("\n");
        return total.toString();
    }

    /**
     * Generate one module( with a start, quote and end) with specific topic
     * @param topic the specific topic
     * @param condition judge whether it is the start in paragraph or not
     * @return a module with topic
     */
    public String generateOneModule(String topic,Boolean condition){
        String firstLine="";
        String quoteLine="";
        String endLine="";
        if(condition){
            firstLine=faker.fakeValuesService().resolve("trash_article.good_first", this, faker).replace("TOPIC",topic);
            quoteLine=faker.fakeValuesService().resolve("trash_article.good_quote", this, faker).replace("TOPIC",topic);
            endLine=faker.fakeValuesService().resolve("trash_article.good_end", this, faker).replace("TOPIC",topic);
        }
        else{
            firstLine=faker.fakeValuesService().resolve("trash_article.bad_first", this, faker).replace("TOPIC",topic);
        }
        return firstLine+quoteLine+endLine;
    }
}
