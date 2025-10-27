package main.java.com.filippovich.arrayapp.entity;

import main.java.com.filippovich.arrayapp.validation.ArrayValidator;
import main.java.com.filippovich.arrayapp.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public final class ArrayFactory {
    private static final Logger logger = LoggerUtil.getLogger(ArrayFactory.class);

    private ArrayFactory() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static StringArray createFromArray(String[] array) {
        logger.debug("Creating StringArray from array: {}",
                array != null ? Arrays.toString(array) : "null");

        ArrayValidator.validateArray(array);
        StringArray result = new StringArray(array);

        logger.info("Successfully created StringArray with {} elements", array.length);
        return result;
    }
}