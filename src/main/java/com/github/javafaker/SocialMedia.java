package com.github.javafaker;

import com.github.javafaker.socialMedias.BiliBili;
import com.github.javafaker.socialMedias.Twitter;
import com.github.javafaker.socialMedias.YouTuBe;

/*
 * This file was used create fake Social Media.
 * And you can easily continue to add new Social Medias in this class
 */
//CS304 issue link: https://github.com/DiUS/java-faker/issues/414
public class SocialMedia {

    private final Faker faker;

    /**
     * constructor
     * @param faker Faker
     */
    public SocialMedia(Faker faker){
        this.faker = faker;
    }

    /**
     * @return a faker Twitter Web
     */
    public String getTwitterWeb() {
        Twitter twitter = new Twitter();
        return twitter.getTwitter(faker);
    }

    /**
     * @return a faker BiliBili Web
     */
    public String getBiliBiliWeb() {
        BiliBili bilibili = new BiliBili();
        return bilibili.getBiliBili(faker);
    }

    /**
     * @return a faker BiliBili BV Number
     */
    public String getBiliBiliBV() {
        BiliBili bilibili = new BiliBili();
        return bilibili.getBVNumber(faker);
    }

    /**
     * @return a faker YouTuBe Web
     */
    public String getYouTuBe() {
        YouTuBe youtube = new YouTuBe();
        return youtube.getYouTuBe(faker);
    }

}
