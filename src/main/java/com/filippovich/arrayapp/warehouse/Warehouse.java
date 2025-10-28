package main.java.com.filippovich.arrayapp.warehouse;

import main.java.com.filippovich.arrayapp.entity.StringArray;
import main.java.com.filippovich.arrayapp.observer.Observer;
import main.java.com.filippovich.arrayapp.repository.impl.StringArrayRepositoryImpl;
import main.java.com.filippovich.arrayapp.util.LoggerUtil;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class Warehouse implements Observer {
    private static final Logger logger = LoggerUtil.getLogger(Warehouse.class);

    private static Warehouse instance;

    private final Map<UUID, ArrayStatistics> statisticsMap = new HashMap<>();

    private Warehouse() {
        logger.info("Warehouse Singleton created.");
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
            StringArrayRepositoryImpl.getInstance().addObserver(instance);
            logger.info("Warehouse instance registered as observer with Repository.");
        }
        return instance;
    }

    @Override
    public void handleEvent(StringArray array, String eventType) {
        if (array == null) return;

        switch (eventType) {
            case "ADD":
                logger.debug("Handling 'ADD' event for array ID: {}", array.getId());
                calculateAndStore(array);
                break;
            case "REMOVE":
                logger.debug("Handling 'REMOVE' event for array ID: {}", array.getId());
                statisticsMap.remove(array.getId());
                break;
            default:
                logger.warn("Unknown event type: {}", eventType);
        }
    }

    private void calculateAndStore(StringArray array) {
        if (array.isEmpty()) {
            ArrayStatistics stats = new ArrayStatistics(0, 0, 0, 0, 0);
            statisticsMap.put(array.getId(), stats);
            logger.debug("Stored empty stats for array ID: {}", array.getId());
            return;
        }

        String[] strings = array.getArray();

        int totalChars = Arrays.stream(strings).mapToInt(String::length).sum();
        double avgLength = Arrays.stream(strings).mapToInt(String::length).average().orElse(0.0);
        int maxLength = Arrays.stream(strings).mapToInt(String::length).max().orElse(0);
        int minLength = Arrays.stream(strings).mapToInt(String::length).min().orElse(0);
        int count = strings.length;

        ArrayStatistics stats = new ArrayStatistics(avgLength, totalChars, maxLength, minLength, count);
        statisticsMap.put(array.getId(), stats);
        logger.debug("Calculated and stored stats for array ID {}: {}", array.getId(), stats);
    }

    public Optional<ArrayStatistics> getStatistics(UUID arrayId) {
        return Optional.ofNullable(statisticsMap.get(arrayId));
    }
}