package com.github.javafaker.idnumbers;

import com.github.javafaker.Faker;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Hanxiao
 * the test that generate valid cn idnumber
 * */
public class CnIdNumberTest {

    Faker f = new Faker();
    CnIdNumber cnid = new CnIdNumber();

    @Test
    public void testGetCnEncoding() {
        assertEquals("9",cnid.getCnEncoding("510623-19990118-701"));
        assertEquals("3",cnid.getCnEncoding("510502-19981211-073"));
    }

    @Test
    public void testGetMainStr() {
        String s = cnid.getMainString(f);
        assertNotNull(s);

        s = cnid.getMainString(f);
        assertNotNull(s);
    }

    @Test
    public void testGetValidCn() {
        String s = cnid.getValidCn(f);
        assertNotNull(s);

        s = cnid.getValidCn(f);
        assertNotNull(s);
    }
}
