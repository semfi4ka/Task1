package main.java.com.filippovich.arrayapp.repository;

import main.java.com.filippovich.arrayapp.entity.StringArray;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StringArrayRepository {
    void add(StringArray stringArray);
    Optional<StringArray> findById(UUID id);
    List<StringArray> findAll();
    boolean remove(StringArray stringArray);

    List<StringArray> query(Specification spec);
}