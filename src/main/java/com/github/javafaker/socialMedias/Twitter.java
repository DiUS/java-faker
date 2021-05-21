package com.github.javafaker.socialMedias;

import com.github.javafaker.Faker;

/*
 * This file was used create Twitter Social Media.
 */
//CS304 issue link: https://github.com/DiUS/java-faker/issues/414
public class Twitter {

    private final String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final int maxLength = 40;

    /**
     * @return a faker Twitter Web
     */
    public String getTwitter(Faker faker){
        StringBuffer sb=new StringBuffer();
        int length = faker.random().nextInt(maxLength);

        for(int i=0;i<length;i++){
            int number=faker.random().nextInt(62);
            sb.append(str.charAt(number));
        }

        return "https://twitter.com/" + sb;
    }

}
