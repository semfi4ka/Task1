package arrayapp;

import arrayapp.entity.StringArray;
import arrayapp.entity.ArrayFactory;
import arrayapp.service.ArrayService;
import arrayapp.service.DefaultArrayService;
import arrayapp.service.SortService;
import arrayapp.service.stream.ArrayStreamService;
import arrayapp.service.stream.StreamSortService;
import arrayapp.file.ArrayFileReader;
import arrayapp.util.LoggerUtil;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        LoggerUtil.info(App.class, "=== String Array Application ===");

        try {
            runAllTests();

            LoggerUtil.info(App.class, "🎉 All tests completed successfully!");

        } catch (Exception e) {
            LoggerUtil.error(App.class, "Application error: " + e.getMessage());
        }
    }

    private static void runAllTests() {
        LoggerUtil.info(App.class, "Starting all tests...");

        testFileOperations();
        testStreamOperations();
        testManualParsing();
        testBasicFunctionality();
        testSortingAlgorithms();

        LoggerUtil.info(App.class, "All test suites completed!");
    }

    private static void testFileOperations() {
        LoggerUtil.info(App.class, "=== FILE OPERATIONS TEST ===");
        ArrayFileReader fileReader = new ArrayFileReader();

        try {
            LoggerUtil.info(App.class, "Reading existing file...");
            var arrays = fileReader.readArraysFromFile();

            LoggerUtil.info(App.class, "Generating statistics from cached data...");
            fileReader.printFileStatistics();

            if (!arrays.isEmpty()) {
                processArraysFromFile(arrays);
            } else {
                LoggerUtil.warn(App.class, "No valid arrays found in file!");
            }

        } catch (Exception e) {
            LoggerUtil.error(App.class, "File reading failed: " + e.getMessage());
        }
    }

    private static void processArraysFromFile(List<StringArray> arrays) {
        LoggerUtil.info(App.class, "Processing arrays from file");
        ArrayService arrayService = new DefaultArrayService();
        SortService sortService = new SortService();

        for (int i = 0; i < arrays.size(); i++) {
            StringArray array = arrays.get(i);
            LoggerUtil.info(App.class, "Processing Word Array " + (i + 1) + ": " + array);

            String shortest = arrayService.findShortestWord(array);
            String longest = arrayService.findLongestWord(array);
            double average = arrayService.calculateAverageLength(array);
            int totalChars = arrayService.calculateTotalCharacters(array);

            LoggerUtil.info(App.class, "Shortest word: '" + shortest + "'");
            LoggerUtil.info(App.class, "Longest word: '" + longest + "'");
            LoggerUtil.info(App.class, "Average word length: " + average);
            LoggerUtil.info(App.class, "Total characters: " + totalChars);

            StringArray sorted = sortService.sortByLengthBubble(array);
            LoggerUtil.info(App.class, "Sorted by length: " + sorted);
        }
    }

    private static void testStreamOperations() {
        LoggerUtil.info(App.class, "=== STREAM API OPERATIONS TEST ===");

        String[] testData = {"apple", "banana", "cat", "elephant", "dog", "zebra"};
        StringArray array = ArrayFactory.createFromArray(testData);

        ArrayStreamService streamService = new ArrayStreamService();

        LoggerUtil.info(App.class, "Test word array: " + array);

        LoggerUtil.info(App.class, "--- Basic Stream Operations ---");
        LoggerUtil.info(App.class, "Shortest word: '" + streamService.findShortestWord(array) + "'");
        LoggerUtil.info(App.class, "Longest word: '" + streamService.findLongestWord(array) + "'");
        LoggerUtil.info(App.class, "Average length: " + streamService.calculateAverageLength(array));
        LoggerUtil.info(App.class, "Total characters: " + streamService.calculateTotalCharacters(array));

        LoggerUtil.info(App.class, "--- Advanced Stream Operations ---");
        LoggerUtil.info(App.class, "First alphabetically: '" + streamService.findFirstAlphabetically(array) + "'");
        LoggerUtil.info(App.class, "Last alphabetically: '" + streamService.findLastAlphabetically(array) + "'");

        String[] longWords = streamService.findWordsLongerThan(array, 4);
        LoggerUtil.info(App.class, "Words longer than 4: " + Arrays.toString(longWords));
    }

    private static void testManualParsing() {
        LoggerUtil.info(App.class, "=== MANUAL PARSING TEST ===");

        String[] testStrings = {
                "hello, world, java",
                "apple;banana;cherry",
                "cat dog elephant"
        };

        for (String testString : testStrings) {
            try {
                StringArray array = ArrayFactory.createFromString(testString);
                LoggerUtil.info(App.class, "'" + testString + "' -> " + array);
            } catch (Exception e) {
                LoggerUtil.error(App.class, "'" + testString + "' -> ERROR: " + e.getMessage());
            }
        }
    }

    private static void testBasicFunctionality() {
        LoggerUtil.info(App.class, "=== BASIC FUNCTIONALITY TEST ===");

        String[] testData = {"programming", "java", "code", "computer", "algorithm"};
        StringArray array = ArrayFactory.createFromArray(testData);
        ArrayService service = new DefaultArrayService();

        LoggerUtil.info(App.class, "Test word array: " + array);
        LoggerUtil.info(App.class, "Shortest word: '" + service.findShortestWord(array) + "'");
        LoggerUtil.info(App.class, "Longest word: '" + service.findLongestWord(array) + "'");
        LoggerUtil.info(App.class, "Average word length: " + service.calculateAverageLength(array));
    }

    private static void testSortingAlgorithms() {
        LoggerUtil.info(App.class, "=== SORTING ALGORITHMS TEST ===");

        String[] testData = {"elephant", "cat", "banana", "ant", "dog", "zebra"};
        StringArray array = ArrayFactory.createFromArray(testData);
        SortService sortService = new SortService();

        LoggerUtil.info(App.class, "Original array: " + array);
        LoggerUtil.info(App.class, "Bubble sort: " + sortService.sortByLengthBubble(array));
        LoggerUtil.info(App.class, "Quick sort: " + sortService.sortByLengthQuick(array));
        LoggerUtil.info(App.class, "Alphabetical sort: " + sortService.sortAlphabetically(array));
    }
}