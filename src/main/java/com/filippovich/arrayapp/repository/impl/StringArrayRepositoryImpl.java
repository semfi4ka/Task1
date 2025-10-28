package main.java.com.filippovich.arrayapp.repository.impl;

import main.java.com.filippovich.arrayapp.entity.StringArray;
import main.java.com.filippovich.arrayapp.observer.Observer;
import main.java.com.filippovich.arrayapp.observer.Publisher;
import main.java.com.filippovich.arrayapp.repository.Specification;
import main.java.com.filippovich.arrayapp.repository.StringArrayRepository;

import java.util.*;
import java.util.stream.Collectors;

public class StringArrayRepositoryImpl implements StringArrayRepository, Publisher {

    private final Map<UUID, StringArray> storage = new HashMap<>();
    private static final StringArrayRepositoryImpl instance = new StringArrayRepositoryImpl();
    private StringArrayRepositoryImpl() {}
    public static StringArrayRepositoryImpl getInstance() {
        return instance;
    }

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        if (o != null) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(StringArray array, String eventType) {
        for (Observer observer : observers) {
            observer.handleEvent(array, eventType);
        }
    }

    @Override
    public void add(StringArray stringArray) {
        if (stringArray != null && stringArray.getId() != null) {
            storage.put(stringArray.getId(), stringArray);
            notifyObservers(stringArray, "ADD");
        }
    }

    @Override
    public boolean remove(StringArray stringArray) {
        if (stringArray == null || stringArray.getId() == null) {
            return false;
        }
        StringArray removed = storage.remove(stringArray.getId());
        if (removed != null) {
            notifyObservers(removed, "REMOVE");
            return true;
        }
        return false;
    }


    @Override
    public List<StringArray> query(Specification spec) {
        return storage.values().stream()
                .filter(spec::specified)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<StringArray> findById(UUID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<StringArray> findAll() {
        return new ArrayList<>(storage.values());
    }
}