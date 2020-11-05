package com.zipcode;

import com.zipcode.data.ZipCode;
import com.zipcode.merger.ZipCodeMerger;
import com.zipcode.util.ZipCodeUtil;

import java.util.List;
import java.util.Scanner;

/**
 * EXAMPLES:
 * If the input = [94133,94133] [94200,94299] [94600,94699]
 * Then the output should be = [94133,94133] [94200,94299] [94600,94699]
 *
 * If the input = [94133,94133] [94200,94299] [94226,94399]
 * Then the output should be = [94133,94133] [94200,94399]
 */
public class ZipCodeMergerApp {

    public static void main(String[] args) {
        System.out.println("Enter Zip Code Range with space separated lower and upper bounds like 1,2 3,4 .. ..");
        //Using Scanner for reading Input from console.
        Scanner scanner = new Scanner(System.in);
        String inputData = scanner.nextLine();
        //Convert input zip code string to List<ZipCode>.
        List<ZipCode> inputZipCodes = ZipCodeUtil.formatInputZipCodeBounds(inputData);

        //Send input zip codes to merger
        ZipCodeMerger zipCodeMerger = new ZipCodeMerger();
        List<ZipCode> mergedZipCodes = zipCodeMerger.getMergedZipCodes(inputZipCodes);

        System.out.println("Merged Zip Codes:");
        //Streaming through merged zip codes to print in console.
        mergedZipCodes.stream()
                .forEach(c -> System.out.println("[" + c.getLowerBound() + "," + c.getUpperBound() + "]"));
        System.out.println("Successfully merged zip codes.");
    }
}
