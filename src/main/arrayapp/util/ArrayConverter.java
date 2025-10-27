package main.arrayapp.util;

import main.arrayapp.validation.ArrayValidator;
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
}