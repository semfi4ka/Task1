package main.java.com.filippovich.arrayapp.entity;

import java.util.UUID;

public interface StringArray {

    UUID getId();

    String[] getArray();

    int length();

    boolean isEmpty();
}