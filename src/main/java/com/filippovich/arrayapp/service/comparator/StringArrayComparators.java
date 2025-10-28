package main.java.com.filippovich.arrayapp.service.comparator;

import main.java.com.filippovich.arrayapp.entity.StringArray;

import java.util.Comparator;

public interface StringArrayComparators {
    Comparator<StringArray> byId();

    Comparator<StringArray> byLength();

    Comparator<StringArray> byFirstElement();
}
