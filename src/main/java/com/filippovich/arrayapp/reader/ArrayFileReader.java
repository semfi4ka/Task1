package main.java.com.filippovich.arrayapp.reader;

import main.java.com.filippovich.arrayapp.entity.impl.StringArrayImpl;
import main.java.com.filippovich.arrayapp.exception.FileReadException;
import main.java.com.filippovich.arrayapp.exception.InvalidDataException;

import java.util.List;

public interface ArrayFileReader {
    List<StringArrayImpl> readArraysFromFile() throws FileReadException, InvalidDataException;

    void printFileStatistics();

    void clearCache();
}
