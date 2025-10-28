package main.java.com.filippovich.arrayapp.validation;

import main.java.com.filippovich.arrayapp.exception.InvalidArrayException;
import main.java.com.filippovich.arrayapp.exception.InvalidDataException;

public interface ArrayValidator {
    String WORD_REGEX = "[a-zA-Zа-яА-Я]+";
    String DELIMITER_REGEX = "[,\\s;\\-]+";

    void validateArray(String[] array) throws InvalidArrayException;
    boolean isValidWordString(String str);
    void validateLineFormat(String line) throws InvalidDataException;
}