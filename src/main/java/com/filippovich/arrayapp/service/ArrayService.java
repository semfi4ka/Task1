package main.java.com.filippovich.arrayapp.service;

import main.java.com.filippovich.arrayapp.entity.impl.StringArrayImpl;
import main.java.com.filippovich.arrayapp.exception.InvalidArrayException;

public interface ArrayService {

    String findShortestWord(StringArrayImpl array);
    String findLongestWord(StringArrayImpl array);

    double calculateAverageLength(StringArrayImpl array);
    int calculateTotalCharacters(StringArrayImpl array);

    int countWordsLongerThan(StringArrayImpl array, int minLength);
    int countWordsShorterThan(StringArrayImpl array, int maxLength);

    StringArrayImpl replaceWords(StringArrayImpl array, String oldWord, String newWord) throws InvalidArrayException;
    StringArrayImpl replaceWordsByLength(StringArrayImpl array, int targetLength, String newWord) throws InvalidArrayException;

    String findFirstAlphabetically(StringArrayImpl array);
    String findLastAlphabetically(StringArrayImpl array);
    int countWordsStartingWith(StringArrayImpl array, char letter);
    int countWordsEndingWith(StringArrayImpl array, char letter);
}