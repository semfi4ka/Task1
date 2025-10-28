package main.java.com.filippovich.arrayapp.service.stream.impl;

import main.java.com.filippovich.arrayapp.entity.impl.StringArrayImpl;
import main.java.com.filippovich.arrayapp.entity.impl.ArrayFactory;
import main.java.com.filippovich.arrayapp.exception.InvalidArrayException;
import main.java.com.filippovich.arrayapp.service.stream.SortStreamService;
import main.java.com.filippovich.arrayapp.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Comparator;

public class SortStreamServiceImpl implements SortStreamService {
    private static final Logger logger = LoggerUtil.getLogger(SortStreamServiceImpl.class);

    @Override
    public StringArrayImpl sortWithStreamSorted(StringArrayImpl array) throws InvalidArrayException {
        logger.debug("Stream sorting by length: {}", array);

        if (array.isEmpty()) {
            logger.debug("Empty array - nothing to sort");
            return array;
        }

        String[] sorted = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);

        StringArrayImpl result = ArrayFactory.createFromArray(sorted);
        logger.debug("Stream sorted by length: {}", result);
        return result;
    }

    @Override
    public StringArrayImpl sortByLengthDescending(StringArrayImpl array) throws InvalidArrayException {
        logger.debug("Stream sorting by length descending: {}", array);

        if (array.isEmpty()) {
            logger.debug("Empty array - nothing to sort");
            return array;
        }

        String[] sorted = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length).reversed())
                .toArray(String[]::new);

        StringArrayImpl result = ArrayFactory.createFromArray(sorted);
        logger.debug("Stream sorted by length descending: {}", result);
        return result;
    }

    @Override
    public StringArrayImpl sortAlphabetically(StringArrayImpl array) throws InvalidArrayException {
        logger.debug("Stream sorting alphabetically: {}", array);

        if (array.isEmpty()) {
            logger.debug("Empty array - nothing to sort");
            return array;
        }

        String[] sorted = Arrays.stream(array.getArray())
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toArray(String[]::new);

        StringArrayImpl result = ArrayFactory.createFromArray(sorted);
        logger.debug("Stream sorted alphabetically: {}", result);
        return result;
    }

    @Override
    public StringArrayImpl sortAlphabeticallyDescendingWithStream(StringArrayImpl array) throws InvalidArrayException {
        logger.debug("Stream sorting alphabetically descending: {}", array);

        if (array.isEmpty()) {
            logger.debug("Empty array - nothing to sort");
            return array;
        }

        String[] sorted = Arrays.stream(array.getArray())
                .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .toArray(String[]::new);

        StringArrayImpl result = ArrayFactory.createFromArray(sorted);
        logger.debug("Stream sorted alphabetically descending: {}", result);
        return result;
    }

    @Override
    public StringArrayImpl sortParallelStream(StringArrayImpl array) throws InvalidArrayException {
        logger.debug("Parallel stream sorting by length: {}", array);

        if (array.isEmpty()) {
            logger.debug("Empty array - nothing to sort");
            return array;
        }

        String[] sorted = Arrays.stream(array.getArray())
                .parallel()
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);

        StringArrayImpl result = ArrayFactory.createFromArray(sorted);
        logger.debug("Parallel stream sorted: {}", result);
        return result;
    }

    @Override
    public String[] findTopNLongest(StringArrayImpl array, int n) {
        logger.debug("Finding top {} longest words: {}", n, array);

        if (array.isEmpty()) {
            logger.debug("Empty array - no words to find");
            return new String[0];
        }

        String[] result = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(n)
                .toArray(String[]::new);

        logger.debug("Top {} longest words: {}", n, Arrays.toString(result));
        return result;
    }

    @Override
    public String[] findTopNShortest(StringArrayImpl array, int n) {
        logger.debug("Finding top {} shortest words: {}", n, array);

        if (array.isEmpty()) {
            logger.debug("Empty array - no words to find");
            return new String[0];
        }

        String[] result = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length))
                .limit(n)
                .toArray(String[]::new);

        logger.debug("Top {} shortest words: {}", n, Arrays.toString(result));
        return result;
    }

    @Override
    public String[] findUniqueSorted(StringArrayImpl array) {
        logger.debug("Finding unique sorted words: {}", array);

        if (array.isEmpty()) {
            logger.debug("Empty array - no unique words");
            return new String[0];
        }

        String[] result = Arrays.stream(array.getArray())
                .distinct()
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);

        logger.debug("Unique sorted words: {}", Arrays.toString(result));
        return result;
    }

    @Override
    public String[] findUniqueAlphabetically(StringArrayImpl array) {
        logger.debug("Finding unique alphabetically sorted words: {}", array);

        if (array.isEmpty()) {
            logger.debug("Empty array - no unique words");
            return new String[0];
        }

        String[] result = Arrays.stream(array.getArray())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toArray(String[]::new);

        logger.debug("Unique alphabetically sorted words: {}", Arrays.toString(result));
        return result;
    }

    @Override
    public void testAllStreamSorts(StringArrayImpl originalArray) throws InvalidArrayException {
        logger.info("Testing all Stream sorts on: {}", originalArray);

        logger.debug("Original: {}", originalArray);
        logger.debug("By length: {}", sortWithStreamSorted(originalArray));
        logger.debug("By length descending: {}", sortByLengthDescending(originalArray));
        logger.debug("Alphabetically: {}", sortAlphabetically(originalArray));
        logger.debug("Alphabetically descending: {}", sortAlphabeticallyDescendingWithStream(originalArray));
        logger.debug("Parallel: {}", sortParallelStream(originalArray));

        logger.debug("Top 3 longest: {}", Arrays.toString(findTopNLongest(originalArray, 3)));
        logger.debug("Top 3 shortest: {}", Arrays.toString(findTopNShortest(originalArray, 3)));
        logger.debug("Unique by length: {}", Arrays.toString(findUniqueSorted(originalArray)));
        logger.debug("Unique alphabetically: {}", Arrays.toString(findUniqueAlphabetically(originalArray)));
    }
}