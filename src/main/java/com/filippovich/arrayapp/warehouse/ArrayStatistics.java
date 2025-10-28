package main.java.com.filippovich.arrayapp.warehouse;

import java.util.StringJoiner;

public class ArrayStatistics {

    private double averageLength;
    private int totalCharacters;
    private int maxLength;
    private int minLength;
    private int wordCount;

    public ArrayStatistics(double averageLength, int totalCharacters, int maxLength, int minLength, int wordCount) {
        this.averageLength = averageLength;
        this.totalCharacters = totalCharacters;
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.wordCount = wordCount;
    }

    public double getAverageLength() {
        return averageLength;
    }

    public int getTotalCharacters() {
        return totalCharacters;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public int getWordCount() {
        return wordCount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ArrayStatistics.class.getSimpleName() + "[", "]")
                .add("avg=" + averageLength)
                .add("sum=" + totalCharacters)
                .add("max=" + maxLength)
                .add("min=" + minLength)
                .add("count=" + wordCount)
                .toString();
    }
}