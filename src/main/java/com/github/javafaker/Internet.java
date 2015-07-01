package com.github.javafaker;

import com.github.javafaker.service.FakeValuesService;

import static org.apache.commons.lang.StringUtils.join;

public class Internet {

    private final Name name;
    private final FakeValuesService fakeValuesService;

    public Internet(Name name, FakeValuesService fakeValuesService) {
        this.name = name;
        this.fakeValuesService = fakeValuesService;
    }

    public String emailAddress() {
        return join(new Object[]{
                name.firstName().toLowerCase(),
                ".",
                name.lastName().toLowerCase(),
                "@",
                java.net.IDN.toASCII(fakeValuesService.fetchString("internet.free_email"))
        });
    }
    
    public String url() {
        return join(new Object[]{
                "www",
                ".",
                java.net.IDN.toASCII(
                    name.firstName().toLowerCase().replaceAll("'", "") +
                    "-" +
                    name.lastName().toLowerCase().replaceAll("'", "")
                ),
                ".",
                fakeValuesService.fetchString("internet.domain_suffix")
        });
    }

    /**
     * All this avatar have been authorized by its awesome users to be use on live websites (not just mockups). For more
     * information, please visit: http://uifaces.com/authorized
     */
    public String avatar() {
        return "https://s3.amazonaws.com/uifaces/faces/twitter/" + fakeValuesService.fetchString("internet.avatar");
    }

}
