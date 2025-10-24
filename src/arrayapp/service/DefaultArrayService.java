package arrayapp.service;

import arrayapp.entity.StringArray;
import arrayapp.entity.ArrayFactory;
import arrayapp.util.LoggerUtil;

public class DefaultArrayService implements ArrayService {

    @Override
    public String findShortestWord(StringArray array) {
        LoggerUtil.debug(DefaultArrayService.class, "Finding shortest word in array: " + array);

        if (array.isEmpty()) {
            LoggerUtil.warn(DefaultArrayService.class, "Attempt to find shortest word in empty array");
            return "";
        }

        String[] arr = array.getArray();
        String shortest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length() < shortest.length()) {
                shortest = arr[i];
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "Shortest word found: '" + shortest + "' (length: " + shortest.length() + ")");
        return shortest;
    }

    @Override
    public String findLongestWord(StringArray array) {
        LoggerUtil.debug(DefaultArrayService.class, "Finding longest word in array: " + array);

        if (array.isEmpty()) {
            LoggerUtil.warn(DefaultArrayService.class, "Attempt to find longest word in empty array");
            return "";
        }

        String[] arr = array.getArray();
        String longest = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].length() > longest.length()) {
                longest = arr[i];
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "Longest word found: '" + longest + "' (length: " + longest.length() + ")");
        return longest;
    }

    @Override
    public double calculateAverageLength(StringArray array) {
        LoggerUtil.debug(DefaultArrayService.class, "Calculating average word length in array: " + array);

        if (array.isEmpty()) {
            LoggerUtil.warn(DefaultArrayService.class, "Attempt to calculate average length of empty array");
            return 0.0;
        }

        String[] arr = array.getArray();
        int totalLength = 0;
        for (String word : arr) {
            totalLength += word.length();
        }

        double average = (double) totalLength / array.length();
        LoggerUtil.debug(DefaultArrayService.class, "Average word length: " + average);
        return average;
    }

    @Override
    public int calculateTotalCharacters(StringArray array) {
        LoggerUtil.debug(DefaultArrayService.class, "Calculating total characters in array: " + array);

        String[] arr = array.getArray();
        int total = 0;
        for (String word : arr) {
            total += word.length();
        }

        LoggerUtil.debug(DefaultArrayService.class, "Total characters: " + total);
        return total;
    }

    @Override
    public int countWordsLongerThan(StringArray array, int minLength) {
        LoggerUtil.debug(DefaultArrayService.class,
                "Counting words longer than " + minLength + " in array: " + array);

        String[] arr = array.getArray();
        int count = 0;
        for (String word : arr) {
            if (word.length() > minLength) {
                count++;
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "Words longer than " + minLength + ": " + count);
        return count;
    }

    @Override
    public int countWordsShorterThan(StringArray array, int maxLength) {
        LoggerUtil.debug(DefaultArrayService.class,
                "Counting words shorter than " + maxLength + " in array: " + array);

        String[] arr = array.getArray();
        int count = 0;
        for (String word : arr) {
            if (word.length() < maxLength) {
                count++;
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "Words shorter than " + maxLength + ": " + count);
        return count;
    }

    @Override
    public int countWordsWithExactLength(StringArray array, int length) {
        LoggerUtil.debug(DefaultArrayService.class,
                "Counting words with exact length " + length + " in array: " + array);

        String[] arr = array.getArray();
        int count = 0;
        for (String word : arr) {
            if (word.length() == length) {
                count++;
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "Words with length " + length + ": " + count);
        return count;
    }

    @Override
    public StringArray replaceWords(StringArray array, String oldWord, String newWord) {
        LoggerUtil.debug(DefaultArrayService.class,
                "Replacing words in array: " + array + ", oldWord: '" + oldWord + "', newWord: '" + newWord + "'");

        String[] arr = array.getArray();
        String[] result = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[i].equals(oldWord) ? newWord : arr[i];
        }

        StringArray replacedArray = ArrayFactory.createFromArray(result);
        LoggerUtil.debug(DefaultArrayService.class, "Words replaced. Result: " + replacedArray);
        return replacedArray;
    }

    @Override
    public StringArray replaceWordsByLength(StringArray array, int targetLength, String newWord) {
        LoggerUtil.debug(DefaultArrayService.class,
                "Replacing words with length " + targetLength + " with '" + newWord + "' in array: " + array);

        String[] arr = array.getArray();
        String[] result = new String[arr.length];

        for (int i = 0; i < arr.length; i++) {
            result[i] = (arr[i].length() == targetLength) ? newWord : arr[i];
        }

        StringArray replacedArray = ArrayFactory.createFromArray(result);
        LoggerUtil.debug(DefaultArrayService.class, "Words replaced by length. Result: " + replacedArray);
        return replacedArray;
    }

    @Override
    public String findFirstAlphabetically(StringArray array) {
        LoggerUtil.debug(DefaultArrayService.class, "Finding first word alphabetically in array: " + array);

        if (array.isEmpty()) {
            return "";
        }

        String[] arr = array.getArray();
        String first = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareToIgnoreCase(first) < 0) {
                first = arr[i];
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "First word alphabetically: '" + first + "'");
        return first;
    }

    @Override
    public String findLastAlphabetically(StringArray array) {
        LoggerUtil.debug(DefaultArrayService.class, "Finding last word alphabetically in array: " + array);

        if (array.isEmpty()) {
            return "";
        }

        String[] arr = array.getArray();
        String last = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareToIgnoreCase(last) > 0) {
                last = arr[i];
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "Last word alphabetically: '" + last + "'");
        return last;
    }

    @Override
    public int countWordsStartingWith(StringArray array, char letter) {
        LoggerUtil.debug(DefaultArrayService.class,
                "Counting words starting with '" + letter + "' in array: " + array);

        String[] arr = array.getArray();
        int count = 0;
        for (String word : arr) {
            if (!word.isEmpty() && Character.toLowerCase(word.charAt(0)) == Character.toLowerCase(letter)) {
                count++;
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "Words starting with '" + letter + "': " + count);
        return count;
    }

    @Override
    public int countWordsEndingWith(StringArray array, char letter) {
        LoggerUtil.debug(DefaultArrayService.class,
                "Counting words ending with '" + letter + "' in array: " + array);

        String[] arr = array.getArray();
        int count = 0;
        for (String word : arr) {
            if (!word.isEmpty() &&
                    Character.toLowerCase(word.charAt(word.length() - 1)) == Character.toLowerCase(letter)) {
                count++;
            }
        }

        LoggerUtil.debug(DefaultArrayService.class, "Words ending with '" + letter + "': " + count);
        return count;
    }
}