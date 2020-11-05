package com.zipcode.data;

/**
 * Zip Code pojo holds upper and lower bounds.
 */
public class ZipCode {

    private int lowerBound;

    private int upperBound;

    public ZipCode(int lowerBound, int upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                '}';
    }
}
