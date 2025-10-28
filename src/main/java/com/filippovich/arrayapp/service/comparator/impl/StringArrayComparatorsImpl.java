package main.java.com.filippovich.arrayapp.service.comparator.impl;

import main.java.com.filippovich.arrayapp.entity.StringArray;
import main.java.com.filippovich.arrayapp.service.comparator.StringArrayComparators;

import java.util.Comparator;

public class StringArrayComparatorsImpl implements StringArrayComparators {
    public StringArrayComparatorsImpl() {
    }


    @Override
    public Comparator<StringArray> byId() {
        return Comparator.comparing(StringArray::getId);
    }


    @Override
    public Comparator<StringArray> byLength() {
        return Comparator.comparingInt(StringArray::length);
    }

    @Override
    public Comparator<StringArray> byFirstElement() {
        return (arr1, arr2) -> {
            if (arr1.isEmpty() && arr2.isEmpty()) return 0;
            if (arr1.isEmpty()) return -1;
            if (arr2.isEmpty()) return 1;

            String first1 = arr1.getArray()[0];
            String first2 = arr2.getArray()[0];
            return first1.compareToIgnoreCase(first2);
        };
    }
}