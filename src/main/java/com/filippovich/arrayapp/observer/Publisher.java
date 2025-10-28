package main.java.com.filippovich.arrayapp.observer;

import main.java.com.filippovich.arrayapp.entity.StringArray;

public interface Publisher {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers(StringArray array, String eventType);
}