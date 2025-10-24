package arrayapp.util;

import arrayapp.validation.ArrayValidator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ArrayConverter {
    private ArrayConverter() {}

    public static String[] parseStringToArray(String line) {
        if (line == null || line.trim().isEmpty()) {
            return new String[0];
        }
        List<String> words = Arrays.stream(line.split(ArrayValidator.DELIMITER_REGEX))
                .map(String::trim)
                .filter(part -> !part.isEmpty())
                .filter(ArrayValidator::isValidWordString)
                .collect(Collectors.toList());

        return words.toArray(new String[0]);
    }

    public static int[] parseStringToIntArray(String line) {
        if (line == null || line.trim().isEmpty()) {
            return new int[0];
        }

        List<Integer> numbers = Arrays.stream(line.split(ArrayValidator.DELIMITER_REGEX))
                .map(String::trim)
                .filter(part -> !part.isEmpty())
                .filter(ArrayValidator::isValidNumberString)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int[] result = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            result[i] = numbers.get(i);
        }

        return result;
    }

    public static String[] filterWordsByMinLength(String[] array, int minLength) {
        return Arrays.stream(array)
                .filter(word -> word.length() >= minLength)
                .toArray(String[]::new);
    }

    public static String[] filterWordsByMaxLength(String[] array, int maxLength) {
        return Arrays.stream(array)
                .filter(word -> word.length() <= maxLength)
                .toArray(String[]::new);
    }

    public static int[] filterPositiveNumbers(int[] array) {
        return Arrays.stream(array)
                .filter(value -> value > 0)
                .toArray();
    }

    public static int[] filterNegativeNumbers(int[] array) {
        return Arrays.stream(array)
                .filter(value -> value < 0)
                .toArray();
    }
}