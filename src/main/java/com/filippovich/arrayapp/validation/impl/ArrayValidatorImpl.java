package main.java.com.filippovich.arrayapp.validation.impl;

import main.java.com.filippovich.arrayapp.exception.InvalidArrayException;
import main.java.com.filippovich.arrayapp.exception.InvalidDataException;
import main.java.com.filippovich.arrayapp.validation.ArrayValidator;

public final class ArrayValidatorImpl implements ArrayValidator {

    public ArrayValidatorImpl() {
    }

    @Override
    public void validateArray(String[] array) throws InvalidArrayException {
        if (array == null) {
            throw new InvalidArrayException("Array cannot be null");
        }
    }

    @Override
    public boolean isValidWordString(String str) {
        return str != null && str.matches(WORD_REGEX);
    }

    @Override
    public void validateLineFormat(String line) throws InvalidDataException {
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