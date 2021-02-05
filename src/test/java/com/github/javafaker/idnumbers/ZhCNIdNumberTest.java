package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import com.github.javafaker.idnumbers.ZhCnIdNumber;
import com.github.javafaker.matchers.MatchesRegularExpression;
import org.junit.Test;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Locale;

import static org.junit.Assert.*;

public class ZhCNIdNumberTest {
    @Test
    public void testValidChineseIdNumber(){
        for (int i=0;i<100; i++){
            Faker faker = new Faker(new Locale("zh_CN"));
            String idNumber=faker.idNumber().valid();
            boolean isSatisfied=true;
            if(idNumber.length()!=18)isSatisfied=false;
            for(int j=0;j<idNumber.length();j++){
                char ch=idNumber.charAt(j);
                if(j!=idNumber.length()-1){
                    if(ch>'9'||ch<'0'){isSatisfied=false;break;}
                }
                else{
                    if((ch>'9'||ch<'0')&&ch!='X'){isSatisfied=false;break;}
                }
            }
            assertTrue(isSatisfied);
        }
    }

    @Test
    public void testChecksumOfChineseIdNumber(){
        for (int i=0;i<100; i++){
            Faker faker = new Faker(new Locale("zh_CN"));
            String s=faker.idNumber().valid();
            boolean isSatisfied=true;
            int count=0;
            count+=(s.charAt(0)-'0')*7;
            count+=(s.charAt(1)-'0')*9;
            count+=(s.charAt(2)-'0')*10;
            count+=(s.charAt(3)-'0')*5;
            count+=(s.charAt(4)-'0')*8;
            count+=(s.charAt(5)-'0')*4;
            count+=(s.charAt(6)-'0')*2;
            count+=(s.charAt(7)-'0');
            count+=(s.charAt(8)-'0')*6;
            count+=(s.charAt(9)-'0')*3;
            count+=(s.charAt(10)-'0')*7;
            count+=(s.charAt(11)-'0')*9;
            count+=(s.charAt(12)-'0')*10;
            count+=(s.charAt(13)-'0')*5;
            count+=(s.charAt(14)-'0')*8;
            count+=(s.charAt(15)-'0')*4;
            count+=(s.charAt(16)-'0')*2;
            count%=11;
            if(count==10){
                if(s.charAt(17)!='X') isSatisfied=false;
            }
            else if((int)(s.charAt(17)-'0')!=count)isSatisfied=false;
            assertTrue(isSatisfied);
        }
    }

    @Test(expected = ParseException.class)
    public void testParseExcpetion() throws Throwable {
        Faker faker=new Faker();
        ZhCnIdNumber idNumber=new ZhCnIdNumber();
        Class<?> cls=ZhCnIdNumber.class;
        Field startTime=cls.getDeclaredField("startTime");
        Field endTime=cls.getDeclaredField("endTime");
        startTime.setAccessible(true);
        endTime.setAccessible(true);
        startTime.set(idNumber,"abcde");
        endTime.set(idNumber,"abcde");
        startTime.setAccessible(false);
        endTime.setAccessible(false);
        System.out.println(idNumber.getValidSsn(faker));
        fail("Should throw ParseExpection");
    }

    @Test
    public void testValidZhCnIdNumber() throws ParseException {
        for (int i=0;i<100; i++){
            Faker faker=new Faker();
            ZhCnIdNumber id=new ZhCnIdNumber();
            String idNumber=id.getValidSsn(faker);
            boolean isSatisfied=true;
            if(idNumber.length()!=18)isSatisfied=false;
            for(int j=0;j<idNumber.length();j++){
                char ch=idNumber.charAt(j);
                if(j!=idNumber.length()-1){
                    if(ch>'9'||ch<'0'){isSatisfied=false;break;}
                }
                else{
                    if((ch>'9'||ch<'0')&&ch!='X'){isSatisfied=false;break;}
                }
            }
            assertTrue(isSatisfied);
        }
    }

}
