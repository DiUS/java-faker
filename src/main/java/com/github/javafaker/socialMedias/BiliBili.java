package com.github.javafaker.socialMedias;

import com.github.javafaker.Faker;

/*
 * This file was used create BiliBili Social Media.
 */
//CS304 issue link: https://github.com/DiUS/java-faker/issues/414
public class BiliBili {

    private final String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * @return a faker BiliBili Web
     */
    public String getBiliBili(Faker faker){

        StringBuffer sb=new StringBuffer();
        for(int i=0;i<8;i++){
            int number=faker.random().nextInt(62);
            sb.append(str.charAt(number));
        }
        return "https://www.bilibili.com/video/BV" + sb;

    }

    /**
     * @return a faker BiliBili BV Number
     */
    public String getBVNumber(Faker faker){

        StringBuffer sb=new StringBuffer();
        for(int i=0;i<8;i++){
            int number=faker.random().nextInt(62);
            sb.append(str.charAt(number));
        }
        return "BV" + sb;

    }
}
