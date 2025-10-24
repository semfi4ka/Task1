package arrayapp.entity;

import arrayapp.validation.ArrayValidator;
import arrayapp.util.LoggerUtil;
import java.util.Arrays;

public final class ArrayFactory {
    private ArrayFactory() {}

    public static StringArray createFromArray(String[] array) {
        LoggerUtil.debug(ArrayFactory.class,
                "Creating StringArray from array: " + (array != null ? Arrays.toString(array) : "null"));

        ArrayValidator.validateArray(array);
        StringArray result = new StringArray(array);

        LoggerUtil.info(ArrayFactory.class,
                "Successfully created StringArray with " + array.length + " elements");
        return result;
    }

    public static StringArray createEmpty() {
        LoggerUtil.debug(ArrayFactory.class, "Creating empty StringArray");
        return new StringArray(new String[0]);
    }

    public static StringArray createFromString(String data) {
        LoggerUtil.debug(ArrayFactory.class, "Creating StringArray from string: '" + data + "'");

        if (data == null || data.trim().isEmpty()) {
            LoggerUtil.warn(ArrayFactory.class, "Empty or null string provided, creating empty array");
            return createEmpty();
        }

        String[] words = arrayapp.util.ArrayConverter.parseStringToArray(data);
        StringArray result = createFromArray(words);

        LoggerUtil.info(ArrayFactory.class,
                "Created StringArray from string with " + words.length + " elements");
        return result;
    }

    public static IntArray createFromIntArray(int[] array) {
        LoggerUtil.debug(ArrayFactory.class,
                "Creating IntArray from array: " + (array != null ? Arrays.toString(array) : "null"));

        arrayapp.validation.ArrayValidator.validateArray(array);
        IntArray result = new IntArray(array);

        LoggerUtil.info(ArrayFactory.class,
                "Successfully created IntArray with " + array.length + " elements");
        return result;
    }

    public static IntArray createIntFromString(String data) {
        LoggerUtil.debug(ArrayFactory.class, "Creating IntArray from string: '" + data + "'");

        if (data == null || data.trim().isEmpty()) {
            LoggerUtil.warn(ArrayFactory.class, "Empty or null string provided, creating empty array");
            return new IntArray(new int[0]);
        }

        int[] numbers = arrayapp.util.ArrayConverter.parseStringToIntArray(data);
        IntArray result = createFromIntArray(numbers);

        LoggerUtil.info(ArrayFactory.class,
                "Created IntArray from string with " + numbers.length + " elements");
        return result;
    }
}