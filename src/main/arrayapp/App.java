package main.arrayapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import main.arrayapp.entity.StringArray;
import main.arrayapp.entity.ArrayFactory;
import main.arrayapp.service.ArrayService;
import main.arrayapp.service.DefaultArrayService;
import main.arrayapp.service.SortService;
import main.arrayapp.service.stream.ArrayStreamService;
import main.arrayapp.service.stream.StreamSortService;
import main.arrayapp.file.ArrayFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class App {
    private static final Logger logger;

    static {
        initializeLogger();
        logger = LogManager.getLogger(App.class);
    }

    private static void initializeLogger() {
        try {
            String[] configPaths = {
                    "src/main/resources/log4j2.xml",
                    "src/resources/log4j2.xml",
                    "log4j2.xml"
            };

            for (String path : configPaths) {
                File configFile = new File(path);
                if (configFile.exists()) {
                    ConfigurationSource source = new ConfigurationSource(new FileInputStream(configFile));
                    Configurator.initialize(null, source);
                    System.out.println("Log4j2 config loaded from: " + configFile.getAbsolutePath());
                    return;
                }
            }

            System.setProperty("log4j2.configurationFile", "");
            System.out.println("Using default Log4j2 configuration");

        } catch (Exception e) {
            System.err.println("Failed to initialize Log4j2: " + e.getMessage());
            System.setProperty("log4j2.configurationFile", "");
        }
    }

    public static void main(String[] args) {
        logger.info("=== String Array Application Started ===");

        try {
            runAllTests();
            logger.info("All tests completed successfully!");

        } catch (Exception e) {
            logger.error("Application terminated with error", e);
        }
    }

    private static void runAllTests() {
        logger.info("Starting all test suites...");

        testStreamOperations();
        testStreamSorting();
        testSortingAlgorithms();
        testFileOperations();

        logger.info("All test suites completed!");
    }

    private static void testFileOperations() {
        logger.info("=== FILE OPERATIONS TEST ===");
        ArrayFileReader fileReader = new ArrayFileReader();

        try {
            logger.info("Reading file...");
            var arrays = fileReader.readArraysFromFile();

            logger.info("Generating file statistics...");
            fileReader.printFileStatistics();

            if (!arrays.isEmpty()) {
                processArraysFromFile(arrays);
            } else {
                logger.warn("No valid arrays found in file!");
            }

        } catch (Exception e) {
            logger.error("File reading operation failed", e);
        }
    }

    private static void processArraysFromFile(java.util.List<StringArray> arrays) {
        logger.info("Processing arrays from file - Total arrays: {}", arrays.size());
        ArrayService arrayService = new DefaultArrayService();
        SortService sortService = new SortService();

        for (int i = 0; i < arrays.size(); i++) {
            StringArray array = arrays.get(i);
            logger.info("--- Processing Word Array {} ---", i + 1);
            logger.info("Original array: {}", array);

            String shortest = arrayService.findShortestWord(array);
            String longest = arrayService.findLongestWord(array);
            double average = arrayService.calculateAverageLength(array);
            int totalChars = arrayService.calculateTotalCharacters(array);
            int wordsLongerThan5 = arrayService.countWordsLongerThan(array, 5);

            logger.info("Shortest word: '{}'", shortest);
            logger.info("Longest word: '{}'", longest);
            logger.info("Average word length: {:.2f}", average);
            logger.info("Total characters: {}", totalChars);
            logger.info("Words longer than 5 characters: {}", wordsLongerThan5);

            StringArray sorted = sortService.sortByLengthBubble(array);
            logger.info("Sorted by length (Bubble): {}", sorted);

            logger.info("--- End of Array {} ---", i + 1);
        }
    }

    private static void testStreamOperations() {
        logger.info("=== STREAM API OPERATIONS TEST ===");

        String[] testData = {"apple", "banana", "cat", "elephant", "dog", "zebra"};
        StringArray array = ArrayFactory.createFromArray(testData);
        ArrayStreamService streamService = new ArrayStreamService();

        logger.info("Test array: {}", array);

        logger.info("--- Basic Stream Operations ---");
        logger.info("Shortest word: '{}'", streamService.findShortestWord(array));
        logger.info("Longest word: '{}'", streamService.findLongestWord(array));
        logger.info("Average length: {:.2f}", streamService.calculateAverageLength(array));
        logger.info("Total characters: {}", streamService.calculateTotalCharacters(array));
        logger.info("Words longer than 4: {}", streamService.countWordsLongerThan(array, 4));
        logger.info("Words shorter than 4: {}", streamService.countWordsShorterThan(array, 4));

        logger.info("--- Advanced Stream Operations ---");
        logger.info("First alphabetically: '{}'", streamService.findFirstAlphabetically(array));
        logger.info("Last alphabetically: '{}'", streamService.findLastAlphabetically(array));
        logger.info("Words starting with 'a': {}", streamService.countWordsStartingWith(array, 'a'));
        logger.info("Words ending with 'a': {}", streamService.countWordsEndingWith(array, 'a'));

        logger.info("--- Stream Transformations ---");
        logger.info("Words longer than 4: {}", Arrays.toString(streamService.findWordsLongerThan(array, 4)));
        logger.info("Words containing 'a': {}", Arrays.toString(streamService.findWordsContaining(array, "a")));
        logger.info("Unique words: {}", Arrays.toString(streamService.getUniqueWords(array)));
        logger.info("Sorted by length: {}", Arrays.toString(streamService.getWordsSortedByLength(array)));
        logger.info("Sorted alphabetically: {}", Arrays.toString(streamService.getWordsSortedAlphabetically(array)));

        logger.info("--- Stream Replacement ---");
        logger.info("Replace 'cat' with 'tiger': {}", streamService.replaceWords(array, "cat", "tiger"));
        logger.info("Replace words with length 3: {}", streamService.replaceWordsByLength(array, 3, "***"));
    }

    private static void testStreamSorting() {
        logger.info("=== STREAM SORTING TEST ===");

        String[] testData = {"elephant", "cat", "banana", "ant", "dog", "zebra", "cat"};
        StringArray array = ArrayFactory.createFromArray(testData);
        StreamSortService streamSortService = new StreamSortService();

        logger.info("Original array: {}", array);
        logger.info("Sorted by length: {}", streamSortService.sortWithStreamSorted(array));
        logger.info("Sorted by length descending: {}", streamSortService.sortDescendingWithStream(array));
        logger.info("Sorted alphabetically: {}", streamSortService.sortAlphabeticallyWithStream(array));
        logger.info("Sorted alphabetically descending: {}", streamSortService.sortAlphabeticallyDescendingWithStream(array));
        logger.info("Parallel sorted: {}", streamSortService.sortParallelStream(array));

        logger.info("--- Advanced Stream Sorting ---");
        logger.info("Top 2 longest: {}", Arrays.toString(streamSortService.findTopNLongest(array, 2)));
        logger.info("Top 2 shortest: {}", Arrays.toString(streamSortService.findTopNShortest(array, 2)));
        logger.info("Unique by length: {}", Arrays.toString(streamSortService.findUniqueSorted(array)));
        logger.info("Unique alphabetically: {}", Arrays.toString(streamSortService.findUniqueAlphabetically(array)));
    }

    private static void testSortingAlgorithms() {
        logger.info("=== SORTING ALGORITHMS TEST ===");

        String[] testData = {"elephant", "cat", "banana", "ant", "dog", "zebra", "programming"};
        StringArray array = ArrayFactory.createFromArray(testData);
        SortService sortService = new SortService();

        logger.info("Original array: {}", array);

        logger.info("--- Length-Based Sorting ---");
        logger.info("Bubble sort (by length): {}", sortService.sortByLengthBubble(array));
        logger.info("Selection sort (by length): {}", sortService.sortByLengthSelection(array));
        logger.info("Quick sort (by length): {}", sortService.sortByLengthQuick(array));
        logger.info("Length descending: {}", sortService.sortByLengthDescending(array));

        logger.info("--- Alphabetical Sorting ---");
        logger.info("Alphabetical sort: {}", sortService.sortAlphabetically(array));

        logger.info("--- Performance Comparison ---");
        long startTime, endTime;

        startTime = System.currentTimeMillis();
        StringArray bubbleSorted = sortService.sortByLengthBubble(array);
        endTime = System.currentTimeMillis();
        logger.debug("Bubble sort completed in {} ms", endTime - startTime);

        startTime = System.currentTimeMillis();
        StringArray quickSorted = sortService.sortByLengthQuick(array);
        endTime = System.currentTimeMillis();
        logger.debug("Quick sort completed in {} ms", endTime - startTime);

        logger.info("Sorting verification - Both produce same result: {}",
                Arrays.equals(bubbleSorted.getArray(), quickSorted.getArray()));
    }
}