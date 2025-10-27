package main.java.com.filippovich.arrayapp.file;

import main.java.com.filippovich.arrayapp.entity.StringArray;
import main.java.com.filippovich.arrayapp.entity.ArrayFactory;
import main.java.com.filippovich.arrayapp.util.ArrayConverter;
import main.java.com.filippovich.arrayapp.validation.ArrayValidator;
import main.java.com.filippovich.arrayapp.exception.FileReadException;
import main.java.com.filippovich.arrayapp.exception.InvalidDataException;
import main.java.com.filippovich.arrayapp.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayFileReader {
    private static final Logger logger = LoggerUtil.getLogger(ArrayFileReader.class);
    private static final String FILE_PATH = "src/main/resources/data/words.txt";
    private List<StringArray> cachedArrays;

    public ArrayFileReader() {
        this.cachedArrays = null;
    }

    public List<StringArray> readArraysFromFile() {
        if (cachedArrays != null) {
            logger.debug("Returning cached arrays");
            return new ArrayList<>(cachedArrays);
        }

        logger.info("Reading word arrays from file: {}", FILE_PATH);
        List<StringArray> arrays = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            int lineNumber = 0;
            int validArraysCount = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (processLine(line, lineNumber, arrays)) {
                    validArraysCount++;
                }
            }

            cachedArrays = new ArrayList<>(arrays);

            logger.info("Successfully processed {} valid word arrays from {} lines",
                    validArraysCount, lineNumber);

        } catch (IOException e) {
            logger.error("Error reading file: {} - {}", FILE_PATH, e.getMessage(), e);
            throw new FileReadException("File not found or cannot be read: " + FILE_PATH, e);
        }

        return arrays;
    }

    private boolean processLine(String line, int lineNumber, List<StringArray> arrays) {
        try {
            if (line == null || line.trim().isEmpty()) {
                logger.debug("Line {}: Empty line - skipped", lineNumber);
                return false;
            }

            logger.debug("Processing line {}: '{}'", lineNumber, line);

            ArrayValidator.validateLineFormat(line);
            String[] words = ArrayConverter.parseStringToArray(line);

            if (words.length > 0) {
                StringArray array = ArrayFactory.createFromArray(words);
                arrays.add(array);
                logger.debug("Line {}: Success - {}", lineNumber, array);
                return true;
            } else {
                logger.debug("Line {}: No valid words found", lineNumber);
                return false;
            }

        } catch (InvalidDataException e) {
            logger.warn("Line {}: Invalid data - {}", lineNumber, e.getMessage());
            return false;
        } catch (Exception e) {
            logger.error("Line {}: Unexpected error", lineNumber, e);
            return false;
        }
    }

    public void printFileStatistics() {
        try {
            List<StringArray> arrays = readArraysFromFile();

            logger.info("=== FILE STATISTICS ===");
            logger.info("Total valid word arrays: {}", arrays.size());

            if (arrays.isEmpty()) {
                logger.info("No valid word arrays found in file!");
                return;
            }

            for (int i = 0; i < arrays.size(); i++) {
                StringArray array = arrays.get(i);
                logger.info("Word Array {}: {} (words: {})",
                        i + 1, array, array.length());
            }
        } catch (Exception e) {
            logger.error("Error generating statistics: {}", e.getMessage(), e);
        }
    }

    public void clearCache() {
        logger.debug("Clearing file cache");
        cachedArrays = null;
    }
}