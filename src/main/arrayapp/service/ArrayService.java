package main.arrayapp.service;

import main.arrayapp.entity.StringArray;

public interface ArrayService {

    String findShortestWord(StringArray array);
    String findLongestWord(StringArray array);

    double calculateAverageLength(StringArray array);
    int calculateTotalCharacters(StringArray array);

    int countWordsLongerThan(StringArray array, int minLength);
    int countWordsShorterThan(StringArray array, int maxLength);
    int countWordsWithExactLength(StringArray array, int length);

    StringArray replaceWords(StringArray array, String oldWord, String newWord);
    StringArray replaceWordsByLength(StringArray array, int targetLength, String newWord);

    String findFirstAlphabetically(StringArray array);
    String findLastAlphabetically(StringArray array);
    int countWordsStartingWith(StringArray array, char letter);
    int countWordsEndingWith(StringArray array, char letter);
}