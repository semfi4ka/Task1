package main.java.com.filippovich.arrayapp.reader.impl;

import main.java.com.filippovich.arrayapp.entity.impl.StringArrayImpl;
import main.java.com.filippovich.arrayapp.entity.impl.ArrayFactory;
import main.java.com.filippovich.arrayapp.reader.ArrayFileReader;
import main.java.com.filippovich.arrayapp.parser.impl.ArrayParserImpl;
import main.java.com.filippovich.arrayapp.validation.impl.ArrayValidatorImpl;
import main.java.com.filippovich.arrayapp.exception.FileReadException;
import main.java.com.filippovich.arrayapp.exception.InvalidDataException;
import main.java.com.filippovich.arrayapp.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayFileReaderImpl implements ArrayFileReader {
    private static final Logger logger = LoggerUtil.getLogger(ArrayFileReaderImpl.class);
    private static final String FILE_PATH = "src/main/java/com/filippovich/resources/data/words.txt";
    private List<StringArrayImpl> cachedArrays;

    public ArrayFileReaderImpl() {
        this.cachedArrays = null;
    }

    @Override
    public List<StringArrayImpl> readArraysFromFile() throws FileReadException, InvalidDataException {
        if (cachedArrays != null) {
            logger.debug("Returning cached arrays");
            return new ArrayList<>(cachedArrays);
        }

        logger.info("Reading word arrays from file: {}", FILE_PATH);
        List<StringArrayImpl> arrays = new ArrayList<>();

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
        } catch (InvalidDataException e) {
            throw new InvalidDataException("Invalid data in: " + FILE_PATH);
        }

        return arrays;
    }

    private boolean processLine(String line, int lineNumber, List<StringArrayImpl> arrays) throws InvalidDataException {
        try {
            if (line == null || line.trim().isEmpty()) {
                logger.debug("Line {}: Empty line - skipped", lineNumber);
                return false;
            }

            logger.debug("Processing line {}: '{}'", lineNumber, line);
            ArrayValidatorImpl arrayValidatorImpl = new ArrayValidatorImpl();
            arrayValidatorImpl.validateLineFormat(line);
            ArrayParserImpl parser = new ArrayParserImpl();
            String[] words = parser.parseStringToArray(line);

            if (words.length > 0) {
                StringArrayImpl array = ArrayFactory.createFromArray(words);
                arrays.add(array);
                logger.debug("Line {}: Success - {}", lineNumber, array);
                return true;
            } else {
                logger.debug("Line {}: No valid words found", lineNumber);
                return false;
            }

        } catch (Exception e) {
            logger.error("Line {}: Unexpected error", lineNumber, e);
            return false;
        }
    }

    @Override
    public void printFileStatistics() {
        try {
            List<StringArrayImpl> arrays = readArraysFromFile();

            logger.info("=== FILE STATISTICS ===");
            logger.info("Total valid word arrays: {}", arrays.size());

            if (arrays.isEmpty()) {
                logger.info("No valid word arrays found in file!");
                return;
            }

            for (int i = 0; i < arrays.size(); i++) {
                StringArrayImpl array = arrays.get(i);
                logger.info("Word Array {}: {} (words: {})",
                        i + 1, array, array.length());
            }
        } catch (Exception e) {
            logger.error("Error generating statistics: {}", e.getMessage(), e);
        }
    }

    @Override
    public void clearCache() {
        logger.debug("Clearing file cache");
        cachedArrays = null;
    }
}