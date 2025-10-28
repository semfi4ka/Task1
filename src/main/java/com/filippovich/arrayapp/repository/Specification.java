package main.java.com.filippovich.arrayapp.repository;

import main.java.com.filippovich.arrayapp.entity.StringArray;

@FunctionalInterface
public interface Specification {
    boolean specified(StringArray array);
}