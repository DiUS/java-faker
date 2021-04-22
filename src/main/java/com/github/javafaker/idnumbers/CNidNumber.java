package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 Generate valid CN-ID Number according to random locations.
 Reference from https://zh.wikipedia.org/wiki/%E4%B8%AD%E5%8D%8E%E4%BA%BA%E6%B0%91%E5%85%B1%E5%92%8C%E5%9B%BD%E5%85%AC%E6%B0%91%E8%BA%AB%E4%BB%BD%E5%8F%B7%E7%A0%81
 */
public class CNidNumber {
    private static final int[] checkSum = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final int[] validate = new int[]{1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private final List<Integer> monthWith31Days = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
    private final List<Integer> monthWith30Days = Arrays.asList(4, 6, 9, 11);
    String regionNumber;

//    public static void main(String[] args) {
//        CNidNumber cNidNumber = new CNidNumber("110113");
//        for (int i = 0; i < 20; i++) {
//            System.out.println(cNidNumber.getValidCNID());
//        }
//    }

    public CNidNumber(String regionNumber) {
        this.regionNumber = regionNumber;
    }

    private String randomBirthday() {
        boolean isLeapYear = false;
        Random random = new Random();
        int year = random.nextInt(122); // change according to current year.
        year += 1900;
        isLeapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        int month = random.nextInt(12) + 1;
        int day;
        if (monthWith31Days.contains(month)) {
            day = random.nextInt(31) + 1;
        } else if (monthWith30Days.contains(month)) {
            day = random.nextInt(30) + 1;
        } else {
            day = isLeapYear ? 29 : 28;
        }
        StringBuilder ret = new StringBuilder();
        ret.append(year);
        if (month < 10) ret.append(0);
        ret.append(month);
        if (day < 10) ret.append(0);
        ret.append(day);
        return ret.toString();
    }

    private String randomOrderNumber() {
        Random random = new Random();
        int ret = random.nextInt(999) + 1;
        if (ret < 10)
            return "00" + ret;
        else if (ret < 100)
            return "0" + ret;
        else
            return "" + ret;
    }

    public String getValidCNID(Faker faker) {
        StringBuilder ret = new StringBuilder();
        String ID = this.regionNumber + randomBirthday() + randomOrderNumber();
        int sum = 0;
        int Wi;
        for (int i = 0; i < 17; i++) {
            int cur = ID.charAt(i) - 48;
            Wi = checkSum[i];
            sum += cur * Wi;
        }
        int last = validate[sum % 11];
        ret.append(ID);
        if (last == 10)
            ret.append('X');
        else
            ret.append(last);
        return ret.toString();
    }

    public static boolean checkValidIDNumber(String ID) {
        if (ID == null || ID.length() != 18) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            int cur = ID.charAt(i) - 48;
            sum += cur * checkSum[i];
        }
        int last = validate[sum % 11];
        if (last == 10 && ID.charAt(17) == 'X')
            return true;
        else return ID.charAt(17) - 48 == last;
    }
}
