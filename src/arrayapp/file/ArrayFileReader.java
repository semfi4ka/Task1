package arrayapp.file;

import arrayapp.entity.StringArray;
import arrayapp.entity.ArrayFactory;
import arrayapp.util.ArrayConverter;
import arrayapp.validation.ArrayValidator;
import arrayapp.exception.FileReadException;
import arrayapp.exception.InvalidDataException;
import arrayapp.util.LoggerUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArrayFileReader {
    private static final String FILE_PATH = "src/resources/data/words.txt";
    private List<StringArray> cachedArrays;

    public ArrayFileReader() {
        this.cachedArrays = null;
    }

    public List<StringArray> readArraysFromFile() {
        if (cachedArrays != null) {
            LoggerUtil.debug(ArrayFileReader.class, "Returning cached arrays");
            return new ArrayList<>(cachedArrays);
        }

        LoggerUtil.info(ArrayFileReader.class, "Reading word arrays from file: " + FILE_PATH);
        List<StringArray> arrays = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
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

            LoggerUtil.info(ArrayFileReader.class,
                    "Successfully processed " + validArraysCount + " valid word arrays from " + lineNumber + " lines");

        } catch (IOException e) {
            LoggerUtil.error(ArrayFileReader.class, "Error reading file: " + FILE_PATH + " - " + e.getMessage());
            throw new FileReadException("File not found or cannot be read: " + FILE_PATH, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LoggerUtil.warn(ArrayFileReader.class, "Error closing file reader: " + e.getMessage());
                }
            }
        }

        return arrays;
    }

    public List<StringArray> readArraysFromFile(boolean forceRefresh) {
        if (forceRefresh) {
            cachedArrays = null;
        }
        return readArraysFromFile();
    }

    private boolean processLine(String line, int lineNumber, List<StringArray> arrays) {
        try {
            if (line == null || line.trim().isEmpty()) {
                LoggerUtil.debug(ArrayFileReader.class, "Line " + lineNumber + ": Empty line - skipped");
                return false;
            }

            LoggerUtil.debug(ArrayFileReader.class, "Processing line " + lineNumber + ": '" + line + "'");

            ArrayValidator.validateLineFormat(line);
            String[] words = ArrayConverter.parseStringToArray(line);

            if (words.length > 0) {
                StringArray array = ArrayFactory.createFromArray(words);
                arrays.add(array);
                LoggerUtil.debug(ArrayFileReader.class, "Line " + lineNumber + ": Success - " + array);
                return true;
            } else {
                LoggerUtil.debug(ArrayFileReader.class, "Line " + lineNumber + ": No valid words found");
                return false;
            }

        } catch (InvalidDataException e) {
            LoggerUtil.warn(ArrayFileReader.class, "Line " + lineNumber + ": Invalid data - " + e.getMessage());
            return false;
        } catch (Exception e) {
            LoggerUtil.error(ArrayFileReader.class, "Line " + lineNumber + ": Unexpected error - " + e.getMessage());
            return false;
        }
    }

    public void printFileStatistics() {
        printFileStatistics(false);
    }

    public void printFileStatistics(boolean forceRefresh) {
        try {
            List<StringArray> arrays = readArraysFromFile(forceRefresh);

            LoggerUtil.info(ArrayFileReader.class, "=== FILE STATISTICS ===");
            LoggerUtil.info(ArrayFileReader.class, "Total valid word arrays: " + arrays.size());

            if (arrays.isEmpty()) {
                LoggerUtil.info(ArrayFileReader.class, "No valid word arrays found in file!");
                return;
            }

            for (int i = 0; i < arrays.size(); i++) {
                StringArray array = arrays.get(i);
                LoggerUtil.info(ArrayFileReader.class,
                        "Word Array " + (i + 1) + ": " + array + " (words: " + array.length() + ")");
            }
        } catch (Exception e) {
            LoggerUtil.error(ArrayFileReader.class, "Error generating statistics: " + e.getMessage());
        }
    }

    public void clearCache() {
        LoggerUtil.debug(ArrayFileReader.class, "Clearing file cache");
        cachedArrays = null;
    }

    public boolean isCached() {
        return cachedArrays != null;
    }
}