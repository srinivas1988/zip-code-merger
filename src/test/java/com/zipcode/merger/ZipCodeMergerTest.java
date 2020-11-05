package com.zipcode.merger;

import com.zipcode.data.ZipCode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ZipCodeMergerTest {

    private ZipCodeMerger zipCodeMerger;

    /**
     * This method runs before every test case.
     */
    @Before
    public void setUp() {
        zipCodeMerger = new ZipCodeMerger();
    }

    /**
     * This test case is used for testing with no conflicts upper or lower bounds.
     * <p>
     * Input = [94133,94133] [94200,94299] [94600,94699]
     * Output = [94133,94133] [94200,94299] [94600,94699]
     */
    @Test
    public void testZipCodesWithoutConflictingZipCodes() {
        //Build test data
        List<ZipCode> zipCodes = new ArrayList<>();
        zipCodes.add(new ZipCode(94133, 94133));
        zipCodes.add(new ZipCode(94200, 94299));
        zipCodes.add(new ZipCode(94600, 94699));
        //Invoke zip code merger for merging zip codes.
        List<ZipCode> mergeZipCodes = zipCodeMerger.getMergedZipCodes(zipCodes);

        //Assert for validations
        assertEquals(3, mergeZipCodes.size());
        assertEquals(94133, mergeZipCodes.get(0).getLowerBound());
        assertEquals(94133, mergeZipCodes.get(0).getUpperBound());
        assertEquals(94200, mergeZipCodes.get(1).getLowerBound());
        assertEquals(94299, mergeZipCodes.get(1).getUpperBound());
        assertEquals(94600, mergeZipCodes.get(2).getLowerBound());
        assertEquals(94699, mergeZipCodes.get(2).getUpperBound());
    }

    /**
     * This test case is used for testing merge upper or lower bounds.
     * <p>
     * Input = [94133,94133] [94200,94299] [94226,94399]
     * Output = [94133,94133] [94200,94399]
     */
    @Test
    public void testZipCodesMerging() {
        //Build test data
        List<ZipCode> zipCodes = new ArrayList<>();
        zipCodes.add(new ZipCode(94133, 94133));
        zipCodes.add(new ZipCode(94200, 94299));
        zipCodes.add(new ZipCode(94226, 94399));
        //Invoke zip code merger for merging zip codes.
        List<ZipCode> mergeZipCodes = zipCodeMerger.getMergedZipCodes(zipCodes);

        //Assert for validations
        assertEquals(2, mergeZipCodes.size());
        assertEquals(94133, mergeZipCodes.get(0).getLowerBound());
        assertEquals(94133, mergeZipCodes.get(0).getUpperBound());
        assertEquals(94200, mergeZipCodes.get(1).getLowerBound());
        assertEquals(94399, mergeZipCodes.get(1).getUpperBound());
    }

    /**
     * This test case is used for testing merge upper or lower bounds.
     * <p>
     * Input = [94133,94133] [94604,94999] [94200,94299] [94226,94399] [94500,94549] [94654,94955]
     * Output = [94133,94133] [94200,94399]
     */
    @Test
    public void testZipCodesMergingWithBiggerSet() {
        //Invoke zip code merger for merging zip codes.
        List<ZipCode> mergedZipCodes = zipCodeMerger.getMergedZipCodes(buildZipCodes());

        //Assert merged zip codes for validations
        assertMergedZipCodes(mergedZipCodes);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMergeZipCodesWithNullInput() {
        zipCodeMerger.getMergedZipCodes(null);
    }

    /**
     * Assert merger zip codes against input data.
     *
     * @param mergedZipCodes
     */
    private void assertMergedZipCodes(List<ZipCode> mergedZipCodes) {
        assertEquals(4, mergedZipCodes.size());
        assertEquals(94133, mergedZipCodes.get(0).getLowerBound());
        assertEquals(94133, mergedZipCodes.get(0).getUpperBound());
        assertEquals(94200, mergedZipCodes.get(1).getLowerBound());
        assertEquals(94399, mergedZipCodes.get(1).getUpperBound());
        assertEquals(94500, mergedZipCodes.get(2).getLowerBound());
        assertEquals(94549, mergedZipCodes.get(2).getUpperBound());
        assertEquals(94604, mergedZipCodes.get(3).getLowerBound());
        assertEquals(94999, mergedZipCodes.get(3).getUpperBound());
    }

    /**
     * Build zip codes test data.
     *
     * @return List<ZipCode>
     */
    private List<ZipCode> buildZipCodes() {
        List<ZipCode> zipCodes = new ArrayList<>();
        zipCodes.add(new ZipCode(94133, 94133));
        zipCodes.add(new ZipCode(94604, 94999));
        zipCodes.add(new ZipCode(94200, 94299));
        zipCodes.add(new ZipCode(94226, 94399));
        zipCodes.add(new ZipCode(94500, 94549));
        zipCodes.add(new ZipCode(94654, 94955));
        return zipCodes;
    }

    /**
     * This method runs after every test case.
     */
    @After
    public void tearDown() {
        zipCodeMerger = null;
    }
}