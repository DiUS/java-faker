package com.github.javafaker;

import org.hamcrest.Matchers;
import org.junit.Test;

import static com.github.javafaker.matchers.MatchesRegularExpression.matchesRegularExpression;
import static org.junit.Assert.assertThat;

public class BarcodeTest extends AbstractFakerTest {

    public static boolean isBarcodeValid(long barcode) {
        char[] array = String.valueOf(barcode).toCharArray();
        int sum = 0;
        for (int i = 0; i < array.length; i ++){
            int digit = Integer.parseInt(String.valueOf(array[i]));
            if ((i + 1) % 2 == 0)
                sum += digit;
            else
                sum = sum + (digit * 3);
        }
        return String.valueOf(sum).endsWith("0");
    }

    @Test
    public void testEan13Length() {
        assertThat(String.valueOf(faker.barcode().ean13()), matchesRegularExpression("[0-9]{13}"));
    }

    @Test
    public void testEan8Length() {
        assertThat(String.valueOf(faker.barcode().ean8()), matchesRegularExpression("[0-9]{8}"));
    }

    @Test
    public void testGtin14Length() {
        assertThat(String.valueOf(faker.barcode().gtin14()), matchesRegularExpression("[0-9]{14}"));
    }

    @Test
    public void testGtin12Length() {
        assertThat(String.valueOf(faker.barcode().gtin12()), matchesRegularExpression("[0-9]{12}"));
    }

    @Test
    public void testGtin12CheckSum() {
        long barcode = faker.barcode().gtin12();
        assertThat(BarcodeTest.isBarcodeValid(barcode), Matchers.equalTo(true));
    }

    @Test
    public void testGtin14CheckSum() {
        long barcode = faker.barcode().gtin14();
        assertThat(BarcodeTest.isBarcodeValid(barcode), Matchers.equalTo(true));
    }

    @Test
    public void testEan8CheckSum() {
        long barcode = faker.barcode().ean8();
        assertThat(BarcodeTest.isBarcodeValid(barcode), Matchers.equalTo(true));
    }

    @Test
    public void testEan13CheckSum() {
        long barcode = faker.barcode().ean13();
        char[] array = String.valueOf(barcode).toCharArray();
        int sum = 0;
        for (int i = 0; i < array.length; i ++){
            int digit = Integer.parseInt(String.valueOf(array[i]));
            if ((i + 1) % 2 == 0)
                sum = sum + digit * 3;
            else
                sum = sum + digit;
        }

        assertThat(String.valueOf(sum).endsWith("0"), Matchers.equalTo(true));
    }
}
