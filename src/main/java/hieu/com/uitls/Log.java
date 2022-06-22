package hieu.com.uitls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static final Logger Log = LogManager.getLogger(Log.class);

    public static void info(String message) {
        Log.info(message);
    }

    public static void info(Object object) {
        Log.info(object);
    }

    public static void warn(String message) {
        Log.warn(message);
    }

    public static void warn(Object object) {
        Log.warn(object);
    }

}
