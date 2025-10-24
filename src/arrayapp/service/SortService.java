package arrayapp.service;

import arrayapp.entity.StringArray;
import arrayapp.entity.ArrayFactory;
import arrayapp.util.LoggerUtil;

import java.util.Arrays;
import java.util.Comparator;

public class SortService {

    public StringArray sortByLengthBubble(StringArray array) {
        LoggerUtil.debug(SortService.class, "Bubble sorting by word length: " + array);

        String[] arr = array.getArray();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].length() > arr[j + 1].length()) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        StringArray sortedArray = ArrayFactory.createFromArray(arr);
        LoggerUtil.debug(SortService.class, "Bubble sorted by length: " + sortedArray);
        return sortedArray;
    }

    public StringArray sortByLengthSelection(StringArray array) {
        LoggerUtil.debug(SortService.class, "Selection sorting by word length: " + array);

        String[] arr = array.getArray();
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].length() < arr[minIndex].length()) {
                    minIndex = j;
                }
            }
            String temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        StringArray sortedArray = ArrayFactory.createFromArray(arr);
        LoggerUtil.debug(SortService.class, "Selection sorted by length: " + sortedArray);
        return sortedArray;
    }

    public StringArray sortByLengthQuick(StringArray array) {
        LoggerUtil.debug(SortService.class, "Quick sorting by word length: " + array);

        String[] arr = array.getArray();
        if (arr.length > 1) {
            quickSortByLength(arr, 0, arr.length - 1);
        }

        StringArray sortedArray = ArrayFactory.createFromArray(arr);
        LoggerUtil.debug(SortService.class, "Quick sorted by length: " + sortedArray);
        return sortedArray;
    }

    private void quickSortByLength(String[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionByLength(arr, low, high);
            quickSortByLength(arr, low, pivotIndex - 1);
            quickSortByLength(arr, pivotIndex + 1, high);
        }
    }

    private int partitionByLength(String[] arr, int low, int high) {
        int pivot = arr[high].length();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].length() <= pivot) {
                i++;
                String temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        String temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    public StringArray sortAlphabetically(StringArray array) {
        LoggerUtil.debug(SortService.class, "Sorting alphabetically: " + array);

        String[] arr = array.getArray();
        Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);

        StringArray sortedArray = ArrayFactory.createFromArray(arr);
        LoggerUtil.debug(SortService.class, "Alphabetically sorted: " + sortedArray);
        return sortedArray;
    }

    public StringArray sortByLengthDescending(StringArray array) {
        LoggerUtil.debug(SortService.class, "Sorting by length descending: " + array);

        String[] arr = array.getArray();
        Arrays.sort(arr, Comparator.comparingInt(String::length).reversed());

        StringArray sortedArray = ArrayFactory.createFromArray(arr);
        LoggerUtil.debug(SortService.class, "Sorted by length descending: " + sortedArray);
        return sortedArray;
    }

    public void testAllSortAlgorithms(StringArray originalArray) {
        LoggerUtil.info(SortService.class, "Testing all sort algorithms on: " + originalArray);

        LoggerUtil.debug(SortService.class, "Original: " + originalArray);
        LoggerUtil.debug(SortService.class, "Bubble (by length): " + sortByLengthBubble(originalArray));
        LoggerUtil.debug(SortService.class, "Selection (by length): " + sortByLengthSelection(originalArray));
        LoggerUtil.debug(SortService.class, "Quick (by length): " + sortByLengthQuick(originalArray));
        LoggerUtil.debug(SortService.class, "Alphabetical: " + sortAlphabetically(originalArray));
        LoggerUtil.debug(SortService.class, "Length descending: " + sortByLengthDescending(originalArray));
    }
}