package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import java.util.*;

/**
 * Implementation based on the definition at
 * https://zh.wikipedia.org/wiki/%E4%B8%AD%E8%8F%AF%E6%B0%91%E5%9C%8B%E5%9C%8B%E6%B0%91%E8%BA%AB%E5%88%86%E8%AD%89
 */
public class ZhTWIdNumber {
    private static final Map<String, String> cityCodeMap = new HashMap<String, String>();
    private static final List<String> cityCodeMapKeySet;
    private static final int[] validCode = {1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1};

    static {
        cityCodeMap.put("A", "10"); //臺北市
        cityCodeMap.put("B", "11"); //臺中市
        cityCodeMap.put("C", "12"); //基隆市
        cityCodeMap.put("D", "13"); //臺南市
        cityCodeMap.put("E", "14"); //高雄市
        cityCodeMap.put("F", "15"); //新北市
        cityCodeMap.put("G", "16"); //宜蘭縣
        cityCodeMap.put("H", "17"); //桃園市
        cityCodeMap.put("I", "34"); //嘉義市
        cityCodeMap.put("J", "18"); //新竹縣
        cityCodeMap.put("K", "19"); //苗栗縣
        cityCodeMap.put("M", "21"); //南投縣
        cityCodeMap.put("N", "22"); //彰化縣
        cityCodeMap.put("O", "35"); //新竹市
        cityCodeMap.put("P", "23"); //雲林縣
        cityCodeMap.put("Q", "24"); //嘉義縣
        cityCodeMap.put("T", "27"); //屏東縣
        cityCodeMap.put("U", "28"); //花蓮縣
        cityCodeMap.put("V", "29"); //臺東縣
        cityCodeMap.put("W", "32"); //金門縣
        cityCodeMap.put("X", "30"); //澎湖縣
        cityCodeMap.put("Z", "33"); //連江縣
        // For get a random key in cityCodeMap
        cityCodeMapKeySet = new ArrayList<String>(cityCodeMap.keySet());
    }

    public String getValidIdNumber(Faker f) {
        return getIdNumber(f, true);
    }

    public String getInvalidIdNumber(Faker f) {
        return getIdNumber(f, false);
    }

    private String getIdNumber(Faker f, boolean valid) {
        StringBuilder idNumber = new StringBuilder();
        idNumber.append(getRandomCityCode());
        idNumber.append(f.random().nextInt(1, 2)); // 1 for male 0 for female
        for (int i = 0; i < 7; i++) {
            idNumber.append(f.random().nextInt(0, 9));
        }
        String lastNumber = getLastNumber(valid, idNumber.toString());
        return idNumber.append(lastNumber).toString();
    }

    private String getRandomCityCode() {
        return cityCodeMapKeySet.get((int) (Math.random() * cityCodeMapKeySet.size()));
    }

    private int calculateChecksumWithoutLastNumber(String idNumber) {
        int checksum = 0;
        StringBuilder checksumNumber = new StringBuilder();
        checksumNumber.append(cityCodeMap.get(idNumber.substring(0, 1)));
        checksumNumber.append(idNumber.substring(1, 9));

        for (int i = 0; i < checksumNumber.length(); i++) {
            checksum += ((int) checksumNumber.charAt(i) - 48) * validCode[i];
        }
        return checksum;
    }

    private int calculateChecksum(String idNumber) {
        return calculateChecksumWithoutLastNumber(idNumber) +
                (((int) idNumber.charAt(9)) - 48) * validCode[10];
    }

    private String getLastNumber(boolean valid, String idNumber) {
        int checksum = calculateChecksumWithoutLastNumber(idNumber);
        int lastNumber = 0;
        while (validIdNumber(idNumber + lastNumber) != valid) {
            lastNumber++;
        }
        return String.valueOf(lastNumber);
    }

    boolean validIdNumber(String idNumber) {
        String[] numbers = idNumber.split("");
        if (!cityCodeMap.containsKey(numbers[0]))
            return false;
        else if (!numbers[1].equals("1") && !numbers[1].equals("2"))
            return false;
        else if (idNumber.length() != 10)
            return false;
        else if (calculateChecksum(idNumber) % 10 != 0)
            return false;
        else
            return true;
    }
}
