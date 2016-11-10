package com.github.javafaker.service;

import java.net.IDN;

/**
 * Created by tshick on 10/30/16.
 */
public class FakerIDN {
    /**
     * {@link IDN#toASCII(String)} is too picky for our needs.  It was throwing exceptions for fa.yml and
     * he.yml as they're Bidi languages and something was causing them to die.  This is kind of a brute force
     * fix but it appears to fix the issue.
     */
    public static final String toASCII(String in) {
        try {
            return IDN.toASCII(in);
        } catch (Exception e) {
            // let's continue with the character by character encoding hack.
        }
        final StringBuilder sb = new StringBuilder();
        for (int i=0;i<in.length();i++) {
            try {
                sb.append(IDN.toASCII(in.substring(i, i + 1)));
            }
            catch (Exception e) {}
        }
        if (sb.length() == 0) {
            throw new RuntimeException("Unable to convert " + in + " to ASCII");
        }
        return sb.toString();
    }
}
