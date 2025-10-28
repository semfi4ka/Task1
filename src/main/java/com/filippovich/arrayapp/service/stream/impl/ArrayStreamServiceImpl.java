package main.java.com.filippovich.arrayapp.service.stream.impl;

import main.java.com.filippovich.arrayapp.entity.impl.StringArrayImpl;
import main.java.com.filippovich.arrayapp.entity.impl.ArrayFactory;
import main.java.com.filippovich.arrayapp.exception.InvalidArrayException;
import main.java.com.filippovich.arrayapp.service.ArrayService;
import main.java.com.filippovich.arrayapp.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayStreamServiceImpl implements ArrayService {
    private static final Logger logger = LoggerUtil.getLogger(ArrayStreamServiceImpl.class);

    @Override
    public String findShortestWord(StringArrayImpl array) {
        logger.debug("Finding shortest word using Stream API in array: {}", array);

        String result = Arrays.stream(array.getArray())
                .min(Comparator.comparingInt(String::length))
                .orElse("");

        logger.debug("Shortest word found: '{}'", result);
        return result;
    }

    @Override
    public String findLongestWord(StringArrayImpl array) {
        logger.debug("Finding longest word using Stream API in array: {}", array);

        String result = Arrays.stream(array.getArray())
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        logger.debug("Longest word found: '{}'", result);
        return result;
    }

    @Override
    public double calculateAverageLength(StringArrayImpl array) {
        logger.debug("Calculating average word length using Stream API in array: {}", array);

        double result = Arrays.stream(array.getArray())
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        logger.debug("Average word length: {}", result);
        return result;
    }

    @Override
    public int calculateTotalCharacters(StringArrayImpl array) {
        logger.debug("Calculating total characters using Stream API in array: {}", array);

        int result = Arrays.stream(array.getArray())
                .mapToInt(String::length)
                .sum();

        logger.debug("Total characters: {}", result);
        return result;
    }

    @Override
    public int countWordsLongerThan(StringArrayImpl array, int minLength) {
        logger.debug("Counting words longer than {} using Stream API in array: {}", minLength, array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> word.length() > minLength)
                .count();

        logger.debug("Words longer than {}: {}", minLength, result);
        return (int) result;
    }

    @Override
    public int countWordsShorterThan(StringArrayImpl array, int maxLength) {
        logger.debug("Counting words shorter than {} using Stream API in array: {}", maxLength, array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> word.length() < maxLength)
                .count();

        logger.debug("Words shorter than {}: {}", maxLength, result);
        return (int) result;
    }

    @Override
    public StringArrayImpl replaceWords(StringArrayImpl array, String oldWord, String newWord) throws InvalidArrayException {
        logger.debug("Replacing words using Stream API in array: {}, '{}' -> '{}'", array, oldWord, newWord);

        String[] result = Arrays.stream(array.getArray())
                .map(word -> word.equals(oldWord) ? newWord : word)
                .toArray(String[]::new);

        StringArrayImpl replacedArray = ArrayFactory.createFromArray(result);
        logger.debug("Words replaced successfully. Result: {}", replacedArray);
        return replacedArray;
    }

    @Override
    public StringArrayImpl replaceWordsByLength(StringArrayImpl array, int targetLength, String newWord) throws InvalidArrayException {
        logger.debug("Replacing words by length using Stream API in array: {}, length {} -> '{}'",
                array, targetLength, newWord);

        String[] result = Arrays.stream(array.getArray())
                .map(word -> word.length() == targetLength ? newWord : word)
                .toArray(String[]::new);

        StringArrayImpl replacedArray = ArrayFactory.createFromArray(result);
        logger.debug("Words replaced by length successfully. Result: {}", replacedArray);
        return replacedArray;
    }

    @Override
    public String findFirstAlphabetically(StringArrayImpl array) {
        logger.debug("Finding first word alphabetically using Stream API in array: {}", array);

        String result = Arrays.stream(array.getArray())
                .min(String::compareToIgnoreCase)
                .orElse("");

        logger.debug("First word alphabetically: '{}'", result);
        return result;
    }

    @Override
    public String findLastAlphabetically(StringArrayImpl array) {
        logger.debug("Finding last word alphabetically using Stream API in array: {}", array);

        String result = Arrays.stream(array.getArray())
                .max(String::compareToIgnoreCase)
                .orElse("");

        logger.debug("Last word alphabetically: '{}'", result);
        return result;
    }

    @Override
    public int countWordsStartingWith(StringArrayImpl array, char letter) {
        logger.debug("Counting words starting with '{}' using Stream API in array: {}", letter, array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> !word.isEmpty() &&
                        Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(letter))
                .count();

        logger.debug("Words starting with '{}': {}", letter, result);
        return (int) result;
    }

    @Override
    public int countWordsEndingWith(StringArrayImpl array, char letter) {
        logger.debug("Counting words ending with '{}' using Stream API in array: {}", letter, array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> !word.isEmpty() &&
                        Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(letter))
                .count();

        logger.debug("Words ending with '{}': {}", letter, result);
        return (int) result;
    }

    public String[] findWordsLongerThan(StringArrayImpl array, int minLength) {
        logger.debug("Finding words longer than {} using Stream API in array: {}", minLength, array);

        String[] result = Arrays.stream(array.getArray())
                .filter(word -> word.length() > minLength)
                .toArray(String[]::new);

        logger.debug("Found {} words longer than {}", result.length, minLength);
        return result;
    }

    public String[] findWordsContaining(StringArrayImpl array, String substring) {
        logger.debug("Finding words containing '{}' using Stream API in array: {}", substring, array);

        String[] result = Arrays.stream(array.getArray())
                .filter(word -> word.toLowerCase().contains(substring.toLowerCase()))
                .toArray(String[]::new);

        logger.debug("Found {} words containing '{}'", result.length, substring);
        return result;
    }

    public String[] getUniqueWords(StringArrayImpl array) {
        logger.debug("Getting unique words using Stream API in array: {}", array);

        String[] result = Arrays.stream(array.getArray())
                .distinct()
                .toArray(String[]::new);

        logger.debug("Found {} unique words from {} total words", result.length, array.length());
        return result;
    }

    public String[] getWordsSortedByLength(StringArrayImpl array) {
        logger.debug("Getting words sorted by length using Stream API in array: {}", array);

        String[] result = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);

        logger.debug("Words sorted by length successfully");
        return result;
    }

    public String[] getWordsSortedAlphabetically(StringArrayImpl array) {
        logger.debug("Getting words sorted alphabetically using Stream API in array: {}", array);

        String[] result = Arrays.stream(array.getArray())
                .sorted(String::compareToIgnoreCase)
                .toArray(String[]::new);

        logger.debug("Words sorted alphabetically successfully");
        return result;
    }

    public String[] getWordsStartingWith(StringArrayImpl array, char letter) {
        logger.debug("Getting words starting with '{}' using Stream API in array: {}", letter, array);

        String[] result = Arrays.stream(array.getArray())
                .filter(word -> !word.isEmpty() &&
                        Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(letter))
                .toArray(String[]::new);

        logger.debug("Found {} words starting with '{}'", result.length, letter);
        return result;
    }

    public String[] getWordsEndingWith(StringArrayImpl array, char letter) {
        logger.debug("Getting words ending with '{}' using Stream API in array: {}", letter, array);

        String[] result = Arrays.stream(array.getArray())
                .filter(word -> !word.isEmpty() &&
                        Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(letter))
                .toArray(String[]::new);

        logger.debug("Found {} words ending with '{}'", result.length, letter);
        return result;
    }

    public int findMaxWordLength(StringArrayImpl array) {
        logger.debug("Finding maximum word length using Stream API in array: {}", array);

        int result = Arrays.stream(array.getArray())
                .mapToInt(String::length)
                .max()
                .orElse(0);

        logger.debug("Maximum word length: {}", result);
        return result;
    }

    public int findMinWordLength(StringArrayImpl array) {
        logger.debug("Finding minimum word length using Stream API in array: {}", array);

        int result = Arrays.stream(array.getArray())
                .mapToInt(String::length)
                .min()
                .orElse(0);

        logger.debug("Minimum word length: {}", result);
        return result;
    }

    public long countDistinctWordLengths(StringArrayImpl array) {
        logger.debug("Counting distinct word lengths using Stream API in array: {}", array);

        long result = Arrays.stream(array.getArray())
                .mapToInt(String::length)
                .distinct()
                .count();

        logger.debug("Distinct word lengths: {}", result);
        return result;
    }

    public String[] getTopLongestWords(StringArrayImpl array, int count) {
        logger.debug("Getting top {} longest words using Stream API in array: {}", count, array);

        String[] result = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(count)
                .toArray(String[]::new);

        logger.debug("Retrieved top {} longest words", result.length);
        return result;
    }

    public String[] getTopShortestWords(StringArrayImpl array, int count) {
        logger.debug("Getting top {} shortest words using Stream API in array: {}", count, array);

        String[] result = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length))
                .limit(count)
                .toArray(String[]::new);

        logger.debug("Retrieved top {} shortest words", result.length);
        return result;
    }

    public boolean allWordsMatchCondition(StringArrayImpl array, String condition) {
        logger.debug("Checking if all words match condition '{}' using Stream API in array: {}", condition, array);

        boolean result;
        switch (condition.toLowerCase()) {
            case "uppercase":
                result = Arrays.stream(array.getArray())
                        .allMatch(word -> word.equals(word.toUpperCase()));
                break;
            case "lowercase":
                result = Arrays.stream(array.getArray())
                        .allMatch(word -> word.equals(word.toLowerCase()));
                break;
            case "startswithvowel":
                result = Arrays.stream(array.getArray())
                        .allMatch(word -> !word.isEmpty() && "aeiou".contains(
                                String.valueOf(Character.toLowerCase(word.charAt(0)))));
                break;
            default:
                result = false;
                logger.warn("Unknown condition: {}", condition);
        }

        logger.debug("All words match condition '{}': {}", condition, result);
        return result;
    }

    public boolean anyWordMatchesCondition(StringArrayImpl array, String condition) {
        logger.debug("Checking if any word matches condition '{}' using Stream API in array: {}", condition, array);

        boolean result;
        switch (condition.toLowerCase()) {
            case "uppercase":
                result = Arrays.stream(array.getArray())
                        .anyMatch(word -> word.equals(word.toUpperCase()));
                break;
            case "palindrome":
                result = Arrays.stream(array.getArray())
                        .anyMatch(word -> {
                            String reversed = new StringBuilder(word).reverse().toString();
                            return word.equalsIgnoreCase(reversed);
                        });
                break;
            case "containsdigit":
                result = Arrays.stream(array.getArray())
                        .anyMatch(word -> word.matches(".*\\d.*"));
                break;
            default:
                result = false;
                logger.warn("Unknown condition: {}", condition);
        }

        logger.debug("Any word matches condition '{}': {}", condition, result);
        return result;
    }

    public void demonstrateAllStreamOperations(StringArrayImpl array) {
        logger.info("=== DEMONSTRATING ALL STREAM OPERATIONS ===");
        logger.info("Original array: {}", array);

        logger.info("Shortest word: '{}'", findShortestWord(array));
        logger.info("Longest word: '{}'", findLongestWord(array));
        logger.info("Average length: {:.2f}", calculateAverageLength(array));
        logger.info("Total characters: {}", calculateTotalCharacters(array));

        logger.info("Unique words: {}", Arrays.toString(getUniqueWords(array)));
        logger.info("Sorted by length: {}", Arrays.toString(getWordsSortedByLength(array)));
        logger.info("Sorted alphabetically: {}", Arrays.toString(getWordsSortedAlphabetically(array)));
        logger.info("Words longer than 4: {}", Arrays.toString(findWordsLongerThan(array, 4)));
        logger.info("Words containing 'a': {}", Arrays.toString(findWordsContaining(array, "a")));

        logger.info("Max word length: {}", findMaxWordLength(array));
        logger.info("Min word length: {}", findMinWordLength(array));
        logger.info("Distinct word lengths: {}", countDistinctWordLengths(array));
        logger.info("Top 2 longest words: {}", Arrays.toString(getTopLongestWords(array, 2)));
        logger.info("Top 2 shortest words: {}", Arrays.toString(getTopShortestWords(array, 2)));

        logger.info("All words uppercase: {}", allWordsMatchCondition(array, "uppercase"));
        logger.info("Any palindrome: {}", anyWordMatchesCondition(array, "palindrome"));

        logger.info("=== END OF STREAM OPERATIONS DEMONSTRATION ===");
    }
}