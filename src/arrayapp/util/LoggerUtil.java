package arrayapp.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LoggerUtil {
    private LoggerUtil() {}

    public static void info(Class<?> clazz, String message) {
        log("INFO", clazz, message);
    }

    public static void debug(Class<?> clazz, String message) {
        log("DEBUG", clazz, message);
    }

    public static void warn(Class<?> clazz, String message) {
        log("WARN", clazz, message);
    }

    public static void error(Class<?> clazz, String message) {
        log("ERROR", clazz, message);
    }

    public static void error(Class<?> clazz, String message, Exception e) {
        log("ERROR", clazz, message + " - " + e.getMessage());
    }

    private static void log(String level, Class<?> clazz, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
        String logMessage = String.format("%s [%s] %s - %s",
                timestamp, level, clazz.getSimpleName(), message);
        System.out.println(logMessage);
    }
}