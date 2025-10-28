package main.java.com.filippovich.arrayapp.entity.impl;

import main.java.com.filippovich.arrayapp.exception.InvalidArrayException;
import main.java.com.filippovich.arrayapp.repository.StringArrayRepository;
import main.java.com.filippovich.arrayapp.repository.impl.StringArrayRepositoryImpl;
import main.java.com.filippovich.arrayapp.validation.impl.ArrayValidatorImpl;
import main.java.com.filippovich.arrayapp.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public final class ArrayFactory {
    private static final Logger logger = LoggerUtil.getLogger(ArrayFactory.class);
    private static final StringArrayRepository repository = StringArrayRepositoryImpl.getInstance();

    private ArrayFactory() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static StringArrayImpl createFromArray(String[] array) throws InvalidArrayException {
        logger.debug("Creating StringArray from array: {}",
                array != null ? Arrays.toString(array) : "null");

        ArrayValidatorImpl arrayValidatorImpl = new ArrayValidatorImpl();
        arrayValidatorImpl.validateArray(array);

        StringArrayImpl result = new StringArrayImpl(array);

        repository.add(result);

        logger.info("Successfully created and saved StringArray with id: {}", result.getId());
        return result;
    }
}