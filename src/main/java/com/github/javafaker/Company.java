package com.github.javafaker;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.javafaker.service.FakeValuesServiceInterface;
import com.github.javafaker.service.RandomService;

import static org.apache.commons.lang3.StringUtils.join;

public class Company {
    private final FakeValuesServiceInterface fakeValuesService;
    private final Resolver resolver;
    private final RandomService randomService;

    public Company(Resolver resolver, FakeValuesServiceInterface fakeValuesService, RandomService randomService) {
        this.fakeValuesService = fakeValuesService;
        this.resolver = resolver;
        this.randomService = randomService;
    }

    public String name() {
        return fakeValuesService.resolve("company.name", this, resolver);
    }

    public String suffix() {
        return fakeValuesService.safeFetch("company.suffix");
    }

    public String industry() {
        return fakeValuesService.safeFetch("company.industry");
    }

    public String profession() {
        return fakeValuesService.safeFetch("company.profession");
    }

    public String buzzword() {
        @SuppressWarnings("unchecked")
        List<List<String>> buzzwordLists = (List<List<String>>) fakeValuesService.fetchObject("company.buzzwords");
        List<String> buzzwords = new ArrayList<String>();
        for (List<String> buzzwordList : buzzwordLists) {
            buzzwords.addAll(buzzwordList);
        }
        return buzzwords.get(randomService.nextInt(buzzwords.size()));
    }

    /**
     * Generate a buzzword-laden catch phrase.
     */
    public String catchPhrase() {
        @SuppressWarnings("unchecked")
        List<List<String>> catchPhraseLists = (List<List<String>>) fakeValuesService.fetchObject("company.buzzwords");
        return joinSampleOfEachList(catchPhraseLists, " ");
    }

    /**
     * When a straight answer won't do, BS to the rescue!
     */
    public String bs() {
        @SuppressWarnings("unchecked")
        List<List<String>> buzzwordLists = (List<List<String>>) fakeValuesService.fetchObject("company.bs");
        return joinSampleOfEachList(buzzwordLists, " ");
    }

    /**
     * Generate a random company logo url in PNG format.
     */
    public String logo() {
        int number = randomService.nextInt(13) + 1;
        return "https://pigment.github.io/fake-logos/logos/medium/color/" + number + ".png";
    }

    public String url() {
        return join(new Object[]{
                "www",
                ".",
                IDN.toASCII(domainName()),
                ".",
                domainSuffix()
        });
    }

    private String domainName(){
        return StringUtils.deleteWhitespace(name().toLowerCase().replaceAll(",", "").replaceAll("'", ""));
    }

    private String domainSuffix() {
        return fakeValuesService.fetchString("internet.domain_suffix");
    }

    private String joinSampleOfEachList(List<List<String>> listOfLists, String separator) {
        List<String> words = new ArrayList<String>();
        for (List<String> list : listOfLists) {
           words.add(list.get(randomService.nextInt(list.size())));
        }
        return StringUtils.join(words, separator);
    }
}
