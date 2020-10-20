package com.github.javafaker;

import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class BarcodeTest extends AbstractFakerTest {

    @Test
    public void type() {
        assertThat(faker.barcode().type(),
                matchesRegularExpression("(Code(128|39|93))|(E|J)AN(-\\d{1,2})*|Codabar|UCC|UPC(-(A|E))*|IS(B|S)N|ITF|" +
                        "Ames\\sCode|NW-7|Monarch|Code\\s2\\sof\\s7|Rationalized|ANSI\\/AIM BC3-1995|USD-4|" +
                        "GS1 Databar|MSI Plessey"));
    }

    @Test
    public void data(){
        assertThat(faker.barcode().data(), matchesRegularExpression("\\d+"));
    }

    @Test
    public void typeAndData(){
        assertThat(faker.barcode().typeAndData(), matchesRegularExpression("(\\w|\\W)+\\s\\d+$"));
    }

}
