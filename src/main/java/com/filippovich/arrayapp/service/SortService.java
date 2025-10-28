package main.java.com.filippovich.arrayapp.service;

import main.java.com.filippovich.arrayapp.entity.impl.StringArrayImpl;
import main.java.com.filippovich.arrayapp.exception.InvalidArrayException;

public interface SortService {
    StringArrayImpl sortByLengthBubble(StringArrayImpl array) throws InvalidArrayException;

    StringArrayImpl sortByLengthSelection(StringArrayImpl array) throws InvalidArrayException;

    StringArrayImpl sortByLengthQuick(StringArrayImpl array) throws InvalidArrayException;

    void quickSortByLength(String[] arr, int low, int high);

    int partitionByLength(String[] arr, int low, int high);

    StringArrayImpl sortAlphabetically(StringArrayImpl array) throws InvalidArrayException;

    StringArrayImpl sortByLengthDescending(StringArrayImpl array) throws InvalidArrayException;
}
