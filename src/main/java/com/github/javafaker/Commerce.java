package com.github.javafaker;

import java.text.DecimalFormat;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import com.github.javafaker.service.FakeValuesServiceInterface;
import com.github.javafaker.service.RandomService;

public class Commerce {

    private final FakeValuesServiceInterface fakeValuesService;
    private final RandomService randomService;

    public Commerce(FakeValuesServiceInterface fakeValuesService, RandomService randomService) {
        this.fakeValuesService = fakeValuesService;
        this.randomService = randomService;
    }

    public String color() {
        return fakeValuesService.fetchString("color.name");
    }

    public String department() {
        int numberOfDepartments = Math.max(randomService.nextInt(4), 1);
        SortedSet<String> departments = new TreeSet<String>();
        while (departments.size() < numberOfDepartments) {
            departments.add(fakeValuesService.fetchString("commerce.department"));
        }
        if (departments.size() > 1) {
            String lastDepartment = departments.last();
            return StringUtils.join(departments.headSet(lastDepartment), ", ") + " & " + lastDepartment;
        } else {
            return departments.first();
        }
    }

    public String productName() {
        return StringUtils.join(new String[] { fakeValuesService.fetchString("commerce.product_name.adjective"),
                fakeValuesService.fetchString("commerce.product_name.material"),
                fakeValuesService.fetchString("commerce.product_name.product") }, " ");
    }

    public String material() {
        return fakeValuesService.fetchString("commerce.product_name.material");
    }

    /**
     * Generate a random price between 0.00 and 100.00 
     */
    public String price() {
        return price(0, 100);
    }

    public String price(double min, double max) {
        double price =  min + (randomService.nextDouble() * (max - min));
        return new DecimalFormat("#0.00").format(price);
    }
}
