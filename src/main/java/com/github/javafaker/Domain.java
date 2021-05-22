package com.github.javafaker;


import java.util.Random;

import static org.apache.commons.lang3.StringUtils.join;

/**
 * The type Domain.
 * CS304 Issue link: https://github.com/DiUS/java-faker/issues/391
 */
public class Domain {
    private final Faker faker;

    /**
     * Instantiates a new Domain.
     *
     * @param faker the faker
     */
    protected Domain(Faker faker) {
        this.faker = faker;
    }

    /**
     * First level domain string. Such as example.com
     *
     * @param name the company name
     * @return the
     */
    public String firstLvlDomain(String name) {
        String ret;
        String top = faker.fakeValuesService().resolve("domain.top", this, faker);
        ret = join(
                name,
                ".",
                top
        );
        return ret;
    }

    /**
     * Second lvl domain string. Such as example.com.uk
     *
     * @param name the company name
     * @return the second level domain with company name
     */
    public String secondLvlDomain(String name) {
        String ret;
        String top = faker.fakeValuesService().resolve("domain.top", this, faker);
        String suffix = faker.fakeValuesService().resolve("domain.country", this, faker);
        ret = join(
                name,
                ".",
                top,
                ".",
                suffix
        );
        return ret;
    }

    /**
     * Full domain string. Such as www.example.com.uk
     *
     * @param name the company name
     * @return the full domain name
     */
    public String fullDomain(String name) {
        String ret;
        String prefix = faker.fakeValuesService().resolve("domain.prefix", this, faker);
        String top = faker.fakeValuesService().resolve("domain.top", this, faker);
        String suffix = faker.fakeValuesService().resolve("domain.country", this, faker);
        ret = join(
                prefix,
                ".",
                name,
                ".",
                top,
                ".",
                suffix
        );
        return ret;
    }


    /**
     * Return a random valid domain.
     *
     * @param name the name
     * @return the string
     */
    public String validDomain(String name) {
        String ret, prefix, top, suffix;
        boolean has_prefix, has_suffix;
        Random random = new Random();

        if (random.nextInt(3) == 1) {
            has_prefix = true;
        } else {
            has_prefix = false;
        }

        if (random.nextInt(2) == 1) {
            has_suffix = true;
        } else {
            has_suffix = false;
        }

        top = faker.fakeValuesService().resolve("domain.top", this, faker);

        ret = join(
                name,
                ".",
                top
        );

        if (has_prefix) {
            prefix = faker.fakeValuesService().resolve("domain.prefix", this, faker);
            ret = join(
                    prefix,
                    ".",
                    ret
            );
        }

        if (has_suffix) {
            suffix = faker.fakeValuesService().resolve("domain.country", this, faker);
            ret = join(
                    ret,
                    ".",
                    suffix
            );
        }

        return ret;
    }
}
