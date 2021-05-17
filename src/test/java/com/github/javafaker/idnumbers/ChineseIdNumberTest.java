package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

//CS304 Issue link: https://github.com/DiUS/java-faker/issues/588
public class ChineseIdNumberTest {

    @Test
    public void testChecksum() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String idNo = f.idNumber().valid();
        assertFalse(zhCNIdNumber.invalidCheck(f, idNo));
    }

    @Test
    public void testProvinces() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String idNo = f.idNumber().valid();
        assertTrue(zhCNIdNumber.provinces.contains(idNo.substring(0,2)));
    }

    @Test
    public void testBirthday() {
        Faker f = new Faker(new Locale("zh-CN"));

        String idNo = f.idNumber().valid();
        String birthday = idNo.substring(6, 14);
        boolean isBirthday;

        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            format.setLenient(false);
            format.parse(birthday);
            isBirthday = true;

        } catch (ParseException e) {
            e.printStackTrace();
            isBirthday = false;
        }

        assertTrue(isBirthday);

    }

    @Test
    public void zhCnSsnValidTest1() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String idNo = f.idNumber().valid();
        assertFalse(zhCNIdNumber.invalidCheck(f, idNo));
    }

    @Test
    public void zhCnSsnValidTest2() {
        Faker f = new Faker(new Locale("zh-CN"));

        String idNo = f.idNumber().valid();
        assertEquals(idNo.length(), 18);
    }

    @Test
    public void zhCnSsnInValidTest1() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String inIdNo = f.idNumber().invalid();
        assertThat(zhCNIdNumber.invalidCheck(f, inIdNo), is(true));
    }

    @Test
    public void zhCnSsnInValidTest2() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String inIdNo = f.idNumber().invalid();
        assertTrue(zhCNIdNumber.invalidCheck(f, inIdNo));
    }

    @Test
    public void getValidCNTest1() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String idNo = zhCNIdNumber.getValidCN(f);
        assertFalse(zhCNIdNumber.invalidCheck(f, idNo));
    }

    @Test
    public void getValidCNTest2() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String idNo = zhCNIdNumber.getValidCN(f);
        assertEquals(idNo.length(), 18);
    }

    @Test
    public void getInvalidCNTest1() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String inIdNo = zhCNIdNumber.getInvalidCN(f);
        assertThat(zhCNIdNumber.invalidCheck(f, inIdNo), is(true));
    }

    @Test
    public void getInvalidCNTest2() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String inIdNo = zhCNIdNumber.getInvalidCN(f);
        assertTrue(zhCNIdNumber.invalidCheck(f, inIdNo));
    }

    @Test
    public void invalidCheckTest1() {

        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String invalidId = "123456123456123456";
        assertTrue(zhCNIdNumber.invalidCheck(f,invalidId));
    }

    @Test
    public void invalidCheckTest2() {
        Faker f = new Faker(new Locale("zh-CN"));
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String invalidId = "522622201303301923";
        assertFalse(zhCNIdNumber.invalidCheck(f,invalidId));
    }


}
