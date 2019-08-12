package com.github.javafaker;

import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;
import java.util.List;

public class Nation {

    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    private final Faker faker;

    protected Nation(Faker faker) {
        this.faker = faker;
    }

    public String nationality() {
        return faker.fakeValuesService().resolve("nation.nationality", this, faker);
    }

    public String language() {
        return faker.fakeValuesService().resolve("nation.language", this, faker);
    }

    public String capitalCity() {
        return faker.fakeValuesService().resolve("nation.capital_city", this, faker);
    }

    public String flag() {
        @SuppressWarnings("unchecked")
        List<Integer> flagInts = (List<Integer>) faker.fakeValuesService().fetch("nation.flag");

        ByteBuffer byteBuffer = MappedByteBuffer.allocate(flagInts.size());

        for (Integer flagInt : flagInts) {
            byteBuffer.put(flagInt.byteValue());
        }

        return new String(byteBuffer.array(), UTF8_CHARSET);
    }

}
