package com.github.javafaker;

import com.github.javafaker.service.FakeValuesInterface;
import com.github.javafaker.service.FakeValuesService;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

public class Commerce {
    private final Faker faker;

    protected Commerce(Faker faker) {
        this.faker = faker;
    }

    public String color() {
        return faker.fakeValuesService().resolve("color.name", this, faker);
    }

    public String department() {
        int numberOfDepartments = Math.max(faker.random().nextInt(4), 1);
        SortedSet<String> departments = new TreeSet<String>();
        while (departments.size() < numberOfDepartments) {
            departments.add(faker.fakeValuesService().resolve("commerce.department", this, faker));
        }
        if (departments.size() > 1) {
            String lastDepartment = departments.last();
            return StringUtils.join(departments.headSet(lastDepartment), ", ") + " & " + lastDepartment;
        } else {
            return departments.first();
        }
    }

    public String productName() {
        return StringUtils.join(new String[] {
                faker.fakeValuesService().resolve("commerce.product_name.adjective", this,faker),
                faker.fakeValuesService().resolve("commerce.product_name.material", this, faker),
                faker.fakeValuesService().resolve("commerce.product_name.product", this, faker) }, " ");
    }

    public String material() {
        return faker.fakeValuesService().resolve("commerce.product_name.material", this, faker);
    }

    /**
     * Generate a random price between 0.00 and 100.00
     */
    public String price() {
        return price(0, 100);
    }

    /**
     * Generate a random price between min and max, the format depends on locale
     * @param min the smaller number, also the inclusive lower bound of generated number
     * @param max the larger number, also the exclusive upper bound of generated number
     * @return A generated number between min and max, its format depends on locale
     */
    public String price(double min, double max) {
        double price =  min + (faker.random().nextDouble() * (max - min));
        Locale locale = new Locale("en");
        try {
            Class fclass = faker.getClass();

            Field[] dFields = fclass.getDeclaredFields();
            for (Field fd : dFields) {
                boolean flag = fd.isAccessible();
                fd.setAccessible(true);
                if (fd.getName().equals("fakeValuesService")) {
                    FakeValuesService fvc = (FakeValuesService) fd.get(faker);
                    Field[] fvcFields = fvc.getClass().getDeclaredFields();
                    for (Field fvcFd : fvcFields) {
                        boolean fvcFlag = fvcFd.isAccessible();
                        fvcFd.setAccessible(true);
                        if (fvcFd.getName().equals("fakeValuesList")) {
                            List<FakeValuesInterface> localList = (List) fvcFd.get(fvc);
                            for (FakeValuesInterface cur : localList) {
                                if (cur.getClass().getSimpleName().equals("FakeValues")) {
                                    Field[] fakeValueFields = cur.getClass().getDeclaredFields();
                                    for (Field fakeValuesFd : fakeValueFields) {
                                        boolean fakeValuesFdFlag = fakeValuesFd.isAccessible();
                                        fakeValuesFd.setAccessible(true);
                                        if (fakeValuesFd.getName().equals("locale"))
                                            locale = (Locale) fakeValuesFd.get(cur);
                                        fakeValuesFd.setAccessible(fakeValuesFdFlag);
                                    }
                                }
                            }
                        }
                        fvcFd.setAccessible(fvcFlag);
                    }
                }
                fd.setAccessible(flag);
            }
        }catch (IllegalAccessException e){
            return null;
        }
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern("#0.00");
        return decimalFormat.format(price);
    }

    public String promotionCode() {
        return promotionCode(6);
    }

    public String promotionCode(int digits) {
        return StringUtils.join(faker.resolve("commerce.promotion_code.adjective"),
                faker.resolve("commerce.promotion_code.noun"),
                faker.number().digits(digits));
    }
}
