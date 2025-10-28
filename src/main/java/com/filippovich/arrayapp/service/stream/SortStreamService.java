package main.java.com.filippovich.arrayapp.service.stream;

import main.java.com.filippovich.arrayapp.entity.impl.StringArrayImpl;
import main.java.com.filippovich.arrayapp.exception.InvalidArrayException;

public interface SortStreamService {
    StringArrayImpl sortWithStreamSorted(StringArrayImpl array) throws InvalidArrayException;

    StringArrayImpl sortByLengthDescending(StringArrayImpl array) throws InvalidArrayException;

    StringArrayImpl sortAlphabetically(StringArrayImpl array) throws InvalidArrayException;

    StringArrayImpl sortAlphabeticallyDescendingWithStream(StringArrayImpl array) throws InvalidArrayException;

    StringArrayImpl sortParallelStream(StringArrayImpl array) throws InvalidArrayException;

    String[] findTopNLongest(StringArrayImpl array, int n);

    String[] findTopNShortest(StringArrayImpl array, int n);

    String[] findUniqueSorted(StringArrayImpl array);

    String[] findUniqueAlphabetically(StringArrayImpl array);

    void testAllStreamSorts(StringArrayImpl originalArray) throws InvalidArrayException;
}
