package main.arrayapp.validation;

import main.arrayapp.exception.InvalidArrayException;
import main.arrayapp.exception.InvalidDataException;

public final class ArrayValidator {
    private ArrayValidator() {}

    public static final String WORD_REGEX = "[a-zA-Zа-яА-Я]+";
    public static final String NUMBER_REGEX = "-?\\d+";
    public static final String DELIMITER_REGEX = "[,\\s;\\-]+";

    public static void validateArray(String[] array) {
        if (array == null) {
            throw new InvalidArrayException("Array cannot be null");
        }
    }

    public static void validateArray(int[] array) {
        if (array == null) {
            throw new InvalidArrayException("Array cannot be null");
        }
    }

    public static boolean isValidWordString(String str) {
        return str != null && str.matches(WORD_REGEX);
    }

    public static boolean isValidNumberString(String str) {
        return str != null && str.matches(NUMBER_REGEX);
    }

    public static void validateLineFormat(String line) {
        if (line == null || line.trim().isEmpty()) {
            throw new InvalidDataException("Line is empty or null");
        }

        String[] parts = line.split(DELIMITER_REGEX);
        boolean hasValidWord = false;

        for (String part : parts) {
            if (!part.trim().isEmpty() && isValidWordString(part.trim())) {
                hasValidWord = true;
                break;
            }
        }

        if (!hasValidWord) {
            throw new InvalidDataException("Line contains no valid words: " + line);
        }
    }
}