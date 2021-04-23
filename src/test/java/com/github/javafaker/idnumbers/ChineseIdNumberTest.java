package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        ZhCNIdNumber zhCNIdNumber = new ZhCNIdNumber();

        String idNo = f.idNumber().valid();
        String birthday = idNo.substring(6, 14);
        boolean isBirthday;

        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            format.setLenient(false);
            Date date = format.parse(birthday);
            isBirthday = true;

        } catch (ParseException e) {
            e.printStackTrace();
            isBirthday = false;
        }

        assertTrue(isBirthday);

    }



}
