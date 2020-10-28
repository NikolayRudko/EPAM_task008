package com.epam.task.eighth.validator;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorSAXXSDTest {

    private final static String FILE_VALID = "src/test/resources/vouchers.xml";
    private final static String FILE_INVALID = "src/test/resources/vouchers_broken.xml";
    private final static String SCHEME = "src/test/resources/voucher.xsd";

    private ValidatorSAXXSD validator = new ValidatorSAXXSD(SCHEME);

    @Test
    public void testValidShouldReturnTrueWhenFileIsCorrect() {
        //when

        //given
        boolean actual = validator.valid(FILE_VALID);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void testValidShouldReturnFalseWhenFileIsNotCorrect() {
        //when

        //given
        boolean actual = validator.valid(FILE_INVALID);

        //then
        Assert.assertFalse(actual);
    }


}