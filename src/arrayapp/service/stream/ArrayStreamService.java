package arrayapp.service.stream;

import arrayapp.entity.StringArray;
import arrayapp.entity.ArrayFactory;
import arrayapp.service.ArrayService;
import arrayapp.util.LoggerUtil;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayStreamService implements ArrayService {

    @Override
    public String findShortestWord(StringArray array) {
        LoggerUtil.debug(ArrayStreamService.class, "Finding shortest word using Stream API: " + array);

        String result = Arrays.stream(array.getArray())
                .min(Comparator.comparingInt(String::length))
                .orElse("");

        LoggerUtil.debug(ArrayStreamService.class, "Shortest word: '" + result + "'");
        return result;
    }

    @Override
    public String findLongestWord(StringArray array) {
        LoggerUtil.debug(ArrayStreamService.class, "Finding longest word using Stream API: " + array);

        String result = Arrays.stream(array.getArray())
                .max(Comparator.comparingInt(String::length))
                .orElse("");

        LoggerUtil.debug(ArrayStreamService.class, "Longest word: '" + result + "'");
        return result;
    }

    @Override
    public double calculateAverageLength(StringArray array) {
        LoggerUtil.debug(ArrayStreamService.class, "Calculating average length using Stream API: " + array);

        double result = Arrays.stream(array.getArray())
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        LoggerUtil.debug(ArrayStreamService.class, "Average length: " + result);
        return result;
    }

    @Override
    public int calculateTotalCharacters(StringArray array) {
        LoggerUtil.debug(ArrayStreamService.class, "Calculating total characters using Stream API: " + array);

        int result = Arrays.stream(array.getArray())
                .mapToInt(String::length)
                .sum();
        LoggerUtil.debug(ArrayStreamService.class, "Total characters: " + result);
        return result;
    }

    @Override
    public int countWordsLongerThan(StringArray array, int minLength) {
        LoggerUtil.debug(ArrayStreamService.class,
                "Counting words longer than " + minLength + " using Stream API: " + array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> word.length() > minLength)
                .count();

        LoggerUtil.debug(ArrayStreamService.class, "Words longer than " + minLength + ": " + result);
        return (int) result;
    }

    @Override
    public int countWordsShorterThan(StringArray array, int maxLength) {
        LoggerUtil.debug(ArrayStreamService.class,
                "Counting words shorter than " + maxLength + " using Stream API: " + array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> word.length() < maxLength)
                .count();

        LoggerUtil.debug(ArrayStreamService.class, "Words shorter than " + maxLength + ": " + result);
        return (int) result;
    }

    @Override
    public int countWordsWithExactLength(StringArray array, int length) {
        LoggerUtil.debug(ArrayStreamService.class,
                "Counting words with length " + length + " using Stream API: " + array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> word.length() == length)
                .count();

        LoggerUtil.debug(ArrayStreamService.class, "Words with length " + length + ": " + result);
        return (int) result;
    }

    @Override
    public StringArray replaceWords(StringArray array, String oldWord, String newWord) {
        LoggerUtil.debug(ArrayStreamService.class,
                "Replacing words using Stream API: " + array + ", '" + oldWord + "' -> '" + newWord + "'");

        String[] result = Arrays.stream(array.getArray())
                .map(word -> word.equals(oldWord) ? newWord : word)
                .toArray(String[]::new);

        StringArray replacedArray = ArrayFactory.createFromArray(result);
        LoggerUtil.debug(ArrayStreamService.class, "Words replaced. Result: " + replacedArray);
        return replacedArray;
    }

    @Override
    public StringArray replaceWordsByLength(StringArray array, int targetLength, String newWord) {
        LoggerUtil.debug(ArrayStreamService.class,
                "Replacing words by length using Stream API: " + array + ", length " + targetLength + " -> '" + newWord + "'");

        String[] result = Arrays.stream(array.getArray())
                .map(word -> word.length() == targetLength ? newWord : word)
                .toArray(String[]::new);

        StringArray replacedArray = ArrayFactory.createFromArray(result);
        LoggerUtil.debug(ArrayStreamService.class, "Words replaced by length. Result: " + replacedArray);
        return replacedArray;
    }

    @Override
    public String findFirstAlphabetically(StringArray array) {
        LoggerUtil.debug(ArrayStreamService.class, "Finding first word alphabetically using Stream API: " + array);

        String result = Arrays.stream(array.getArray())
                .min(String::compareToIgnoreCase)
                .orElse("");

        LoggerUtil.debug(ArrayStreamService.class, "First word alphabetically: '" + result + "'");
        return result;
    }

    @Override
    public String findLastAlphabetically(StringArray array) {
        LoggerUtil.debug(ArrayStreamService.class, "Finding last word alphabetically using Stream API: " + array);

        String result = Arrays.stream(array.getArray())
                .max(String::compareToIgnoreCase)
                .orElse("");

        LoggerUtil.debug(ArrayStreamService.class, "Last word alphabetically: '" + result + "'");
        return result;
    }

    @Override
    public int countWordsStartingWith(StringArray array, char letter) {
        LoggerUtil.debug(ArrayStreamService.class,
                "Counting words starting with '" + letter + "' using Stream API: " + array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> !word.isEmpty() &&
                        Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(letter))
                .count();

        LoggerUtil.debug(ArrayStreamService.class, "Words starting with '" + letter + "': " + result);
        return (int) result;
    }

    @Override
    public int countWordsEndingWith(StringArray array, char letter) {
        LoggerUtil.debug(ArrayStreamService.class,
                "Counting words ending with '" + letter + "' using Stream API: " + array);

        long result = Arrays.stream(array.getArray())
                .filter(word -> !word.isEmpty() &&
                        Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(letter))
                .count();

        LoggerUtil.debug(ArrayStreamService.class, "Words ending with '" + letter + "': " + result);
        return (int) result;
    }

    public String[] findWordsLongerThan(StringArray array, int minLength) {
        return Arrays.stream(array.getArray())
                .filter(word -> word.length() > minLength)
                .toArray(String[]::new);
    }
}