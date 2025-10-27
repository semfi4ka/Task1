package main.java.com.filippovich.arrayapp.exception;

public class InvalidArrayException extends RuntimeException {
    public InvalidArrayException(String message) {
        super(message);
    }
}