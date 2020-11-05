package com.zipcode.util;

import com.zipcode.data.ZipCode;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Zip code utility class.
 */
public class ZipCodeUtil {

    /**
     * This method is used for converting console input string to List<ZipCode>.
     * @param inputData
     * @return List<ZipCode>
     */
    public static List<ZipCode> formatInputZipCodeBounds(String inputData) {
        System.out.println("Converting input zip code string to list of lower and upper zip codes.");
        if (Objects.isNull(inputData) || inputData.isEmpty())
            throw new IllegalArgumentException("Input data cannot be null or empty.");

        String[] zipCodeRanges = inputData.split(" ");
        List<String> zipCodeRangeList = Arrays.asList(zipCodeRanges);
        //Convert zip code range list to List<ZipCode>
        List<ZipCode> convertedZipCodes = zipCodeRangeList.stream()
                .map(c -> {
                    String[] zipCodeBounds = c.split(",");
                    if (zipCodeBounds.length == 2) {
                        int lowerBound = Integer.parseInt(zipCodeBounds[0]);
                        int upperBound = Integer.parseInt(zipCodeBounds[1]);
                        return new ZipCode(lowerBound, upperBound);
                    }
                    return null;
                }).collect(Collectors.toList());
        System.out.println("Input zip codes bounds size: " + convertedZipCodes.size());
        return convertedZipCodes;
    }
}
