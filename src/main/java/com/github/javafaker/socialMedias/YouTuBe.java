package com.github.javafaker.socialMedias;

import com.github.javafaker.Faker;

/*
 * This file was used create YouTuBe Social Media.
 */
//CS304 issue link: https://github.com/DiUS/java-faker/issues/414
public class YouTuBe {

    private final String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * @return a faker YouTuBe Web
     */
    public String getYouTuBe(Faker faker){
        StringBuffer sb=new StringBuffer();

        for(int i=0;i<11;i++){
            int number=faker.random().nextInt(62);
            sb.append(str.charAt(number));
        }

        return "https://www.youtube.com/watch?v=" + sb;
    }

}
