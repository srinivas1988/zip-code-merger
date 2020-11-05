package com.zipcode.merger;

import com.zipcode.data.ZipCode;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Zip Code Merger Process.
 *      1. Sorts the list of zip codes.
 *      2. Merge if previous upper bound is greater than or equal to current lower bound.
 *      3. Remove duplicate from the merged list.
 */
public class ZipCodeMerger {

    public List<ZipCode> getMergedZipCodes(List<ZipCode> zipCodeList) {
        System.out.println("Starting sorting zip codes...");
        //Validate input zip code bounds
        if (Objects.isNull(zipCodeList) || zipCodeList.isEmpty())
            throw new IllegalArgumentException("Input Zip Codes bounds cannot be null.");

        //Sort zip code ranges by lower bounds.
        List<ZipCode> sortedZipCodeRanges = getSortedZipCodes(zipCodeList);

        //Merge lower and upper bounds if required.
        List<ZipCode> mergedZipCodes = mergeZipCodes(sortedZipCodeRanges);

        //Remove duplicate lower and upper bounds then return final bounds.
        return removeDuplicates(mergedZipCodes);
    }

    /**
     * Merge Zip code lower and upper bounds.
     * @param sortedZipCodeRanges
     * @return List<ZipCode>
     */
    private List<ZipCode> mergeZipCodes(List<ZipCode> sortedZipCodeRanges) {
        //Creating new linked list to hold merged zip codes.
        List<ZipCode> mergedZipCodes = new LinkedList<>();

        ZipCode previousZipCode = null;
        //Iterating through each zip code and merge into merged zip code list.
        for (ZipCode zipCode : sortedZipCodeRanges) {
            //If previous zip code object is null then set current zip code as previous zip code.
            if (Objects.isNull(previousZipCode)) {
                mergedZipCodes.add(zipCode);
                previousZipCode = zipCode;
                continue;
            }

            int previousUpperBound = previousZipCode.getUpperBound();
            int currentLowerBound = zipCode.getLowerBound();
            //Check if previous upper bound is greater than or equal to current lower bound
            //then find max of both and set it to the previous upper bound.
            if (previousUpperBound >= currentLowerBound) {
                int maxUpperBound = Math.max(previousUpperBound, zipCode.getUpperBound());
                //Add previous lower bound and max upper bound to merged list.
                previousZipCode.setUpperBound(maxUpperBound);
            } else {
                previousZipCode = zipCode;
            }
            mergedZipCodes.add(previousZipCode);
        }
        return mergedZipCodes;
    }

    /**
     * Remove duplicates from merged zip code list.
     * @param mergedZipCodes
     * @return List<ZipCode>
     */
    private List<ZipCode> removeDuplicates(List<ZipCode> mergedZipCodes) {
        return mergedZipCodes.stream()
                    .distinct()
                    .collect(Collectors.toList());
    }

    /**
     * This method takes input zip code list and return the sorted zip code list.
     *
     * @param zipCodeList
     * @return List<ZipCode>
     */
    private List<ZipCode> getSortedZipCodes(List<ZipCode> zipCodeList) {
        List<ZipCode> sortedZipCodeRanges = zipCodeList.stream()
                .sorted(Comparator.comparing(ZipCode::getLowerBound))
                .collect(Collectors.toList());

        System.out.println("Sorted Ranges: " + sortedZipCodeRanges);
        return sortedZipCodeRanges;
    }
}
