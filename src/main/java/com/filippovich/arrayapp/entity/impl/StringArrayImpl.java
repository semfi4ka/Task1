package main.java.com.filippovich.arrayapp.entity.impl;

import main.java.com.filippovich.arrayapp.entity.StringArray;

import java.util.Arrays;
import java.util.UUID;

public class StringArrayImpl implements StringArray {

    private final UUID id;
    private final String[] array;

    public StringArrayImpl(String[] array) {
        this.id = UUID.randomUUID();
        this.array = array != null ? array.clone() : new String[0];
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String[] getArray() {
        return array.clone();
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public String toString() {
        return "StringArrayImpl{" +
                "id=" + id +
                ", array=" + Arrays.toString(array) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringArrayImpl that = (StringArrayImpl) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}