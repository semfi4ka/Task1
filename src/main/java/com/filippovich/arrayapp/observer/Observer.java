package main.java.com.filippovich.arrayapp.observer;

import main.java.com.filippovich.arrayapp.entity.StringArray;

public interface Observer {
    void handleEvent(StringArray array, String eventType);
}