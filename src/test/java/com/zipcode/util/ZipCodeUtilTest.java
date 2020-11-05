package com.zipcode.util;

import com.zipcode.data.ZipCode;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test class for zip code util class.
 */
public class ZipCodeUtilTest {

    /**
     * Test for formatting input zip codes.
     * Input = 94133,94133 94200,94299
     * Output = [94133,94133] [94200,94299]
     */
    @Test
    public void formatInputZipCodeBounds() {
        //Build input data
        String inputData = "94133,94133 94200,94299";
        //Invoke util for converting zip codes string to list.
        List<ZipCode> formatZipCodeBounds = ZipCodeUtil.formatInputZipCodeBounds(inputData);

        //Assertions
        assertEquals(2, formatZipCodeBounds.size());
        assertEquals(94133, formatZipCodeBounds.get(0).getLowerBound());
        assertEquals(94133, formatZipCodeBounds.get(0).getUpperBound());
        assertEquals(94200, formatZipCodeBounds.get(1).getLowerBound());
        assertEquals(94299, formatZipCodeBounds.get(1).getUpperBound());
    }

    /**
     * Test case for testing with empty input data
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithEmptyInputData() {
        ZipCodeUtil.formatInputZipCodeBounds("");
    }

    /**
     * Test case for testing with null input data
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithNullInputData() {
        ZipCodeUtil.formatInputZipCodeBounds(null);
    }
}