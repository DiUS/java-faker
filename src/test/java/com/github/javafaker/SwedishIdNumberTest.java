package com.github.javafaker;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class SwedishIdNumberTest {

    @Test
    public void valid() {
        SwedishIdNumber idNumber = new SwedishIdNumber();
        assertTrue(idNumber.validSwedishSsn("670919-9530"));
        assertTrue(idNumber.validSwedishSsn("811228-9874"));
    }

    @Test
    public void invalid() {
        SwedishIdNumber idNumber = new SwedishIdNumber();
        assertTrue(!idNumber.validSwedishSsn("8112289873"));
        assertTrue(!idNumber.validSwedishSsn("foo228-9873"));
        assertTrue(!idNumber.validSwedishSsn("811228-9873"));
        assertTrue(!idNumber.validSwedishSsn("811228-9875"));
        assertTrue(!idNumber.validSwedishSsn("811200-9874"));
        assertTrue(!idNumber.validSwedishSsn("810028-9874"));
    }
}
