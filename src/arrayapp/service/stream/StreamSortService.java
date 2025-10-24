package arrayapp.service.stream;

import arrayapp.entity.StringArray;
import arrayapp.entity.ArrayFactory;
import arrayapp.util.LoggerUtil;

import java.util.Arrays;
import java.util.Comparator;

public class StreamSortService {

    public StringArray sortWithStreamSorted(StringArray array) {
        LoggerUtil.debug(StreamSortService.class, "Stream sorting by length: " + array);

        String[] sorted = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);

        StringArray result = ArrayFactory.createFromArray(sorted);
        LoggerUtil.debug(StreamSortService.class, "Stream sorted by length: " + result);
        return result;
    }

    public StringArray sortDescendingWithStream(StringArray array) {
        LoggerUtil.debug(StreamSortService.class, "Stream sorting by length descending: " + array);

        String[] sorted = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length).reversed())
                .toArray(String[]::new);

        StringArray result = ArrayFactory.createFromArray(sorted);
        LoggerUtil.debug(StreamSortService.class, "Stream sorted by length descending: " + result);
        return result;
    }

    public StringArray sortAlphabeticallyWithStream(StringArray array) {
        LoggerUtil.debug(StreamSortService.class, "Stream sorting alphabetically: " + array);

        String[] sorted = Arrays.stream(array.getArray())
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toArray(String[]::new);

        StringArray result = ArrayFactory.createFromArray(sorted);
        LoggerUtil.debug(StreamSortService.class, "Stream sorted alphabetically: " + result);
        return result;
    }

    public StringArray sortAlphabeticallyDescendingWithStream(StringArray array) {
        LoggerUtil.debug(StreamSortService.class, "Stream sorting alphabetically descending: " + array);

        String[] sorted = Arrays.stream(array.getArray())
                .sorted(String.CASE_INSENSITIVE_ORDER.reversed())
                .toArray(String[]::new);

        StringArray result = ArrayFactory.createFromArray(sorted);
        LoggerUtil.debug(StreamSortService.class, "Stream sorted alphabetically descending: " + result);
        return result;
    }

    public StringArray sortParallelStream(StringArray array) {
        LoggerUtil.debug(StreamSortService.class, "Parallel stream sorting by length: " + array);

        String[] sorted = Arrays.stream(array.getArray())
                .parallel()
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);

        StringArray result = ArrayFactory.createFromArray(sorted);
        LoggerUtil.debug(StreamSortService.class, "Parallel stream sorted: " + result);
        return result;
    }

    public String[] findTopNLongest(StringArray array, int n) {
        LoggerUtil.debug(StreamSortService.class, "Finding top " + n + " longest words: " + array);

        String[] result = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(n)
                .toArray(String[]::new);

        LoggerUtil.debug(StreamSortService.class, "Top " + n + " longest words: " + Arrays.toString(result));
        return result;
    }

    public String[] findTopNShortest(StringArray array, int n) {
        LoggerUtil.debug(StreamSortService.class, "Finding top " + n + " shortest words: " + array);

        String[] result = Arrays.stream(array.getArray())
                .sorted(Comparator.comparingInt(String::length))
                .limit(n)
                .toArray(String[]::new);

        LoggerUtil.debug(StreamSortService.class, "Top " + n + " shortest words: " + Arrays.toString(result));
        return result;
    }

    public String[] findUniqueSorted(StringArray array) {
        LoggerUtil.debug(StreamSortService.class, "Finding unique sorted words: " + array);

        String[] result = Arrays.stream(array.getArray())
                .distinct()
                .sorted(Comparator.comparingInt(String::length))
                .toArray(String[]::new);

        LoggerUtil.debug(StreamSortService.class, "Unique sorted words: " + Arrays.toString(result));
        return result;
    }

    public String[] findUniqueAlphabetically(StringArray array) {
        LoggerUtil.debug(StreamSortService.class, "Finding unique alphabetically sorted words: " + array);

        String[] result = Arrays.stream(array.getArray())
                .distinct()
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .toArray(String[]::new);

        LoggerUtil.debug(StreamSortService.class, "Unique alphabetically sorted words: " + Arrays.toString(result));
        return result;
    }

    public void testAllStreamSorts(StringArray originalArray) {
        LoggerUtil.info(StreamSortService.class, "Testing all Stream sorts on: " + originalArray);

        LoggerUtil.debug(StreamSortService.class, "Original: " + originalArray);
        LoggerUtil.debug(StreamSortService.class, "By length: " + sortWithStreamSorted(originalArray));
        LoggerUtil.debug(StreamSortService.class, "By length descending: " + sortDescendingWithStream(originalArray));
        LoggerUtil.debug(StreamSortService.class, "Alphabetically: " + sortAlphabeticallyWithStream(originalArray));
        LoggerUtil.debug(StreamSortService.class, "Alphabetically descending: " + sortAlphabeticallyDescendingWithStream(originalArray));
        LoggerUtil.debug(StreamSortService.class, "Parallel: " + sortParallelStream(originalArray));

        LoggerUtil.debug(StreamSortService.class, "Top 3 longest: " + Arrays.toString(findTopNLongest(originalArray, 3)));
        LoggerUtil.debug(StreamSortService.class, "Top 3 shortest: " + Arrays.toString(findTopNShortest(originalArray, 3)));
        LoggerUtil.debug(StreamSortService.class, "Unique by length: " + Arrays.toString(findUniqueSorted(originalArray)));
        LoggerUtil.debug(StreamSortService.class, "Unique alphabetically: " + Arrays.toString(findUniqueAlphabetically(originalArray)));
    }
}