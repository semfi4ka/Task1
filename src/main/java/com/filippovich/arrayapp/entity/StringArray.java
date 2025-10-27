package main.java.com.filippovich.arrayapp.entity;

import java.util.Arrays;

public class StringArray {
    private final String[] array;

    public StringArray(String[] array) {
        this.array = array != null ? array.clone() : new String[0];
    }

    public String[] getArray() {
        return array.clone();
    }

    public int length() {
        return array.length;
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}