package com.github.javafaker.service;

import com.github.javafaker.Resolver;

public interface FakeValuesServiceInterface {

    Object fetch(String key);

    String fetchString(String key);

    String safeFetch(String key);

    Object fetchObject(String key);

    String numerify(String numberString);

    String bothify(String string);

    String bothify(String string, boolean isUpper);

    String letterify(String letterString);

    String letterify(String letterString, boolean isUpper);

    String regexify(String regex);

    String resolve(String key, Object current, Resolver resolver);
}
