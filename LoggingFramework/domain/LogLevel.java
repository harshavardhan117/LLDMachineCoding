package LoggingFramework.domain;

import java.util.Map;

public enum LogLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL;

    private static final Map<LogLevel, Integer> PRIORITY_BY_LEVEL = Map.of(
        DEBUG, 1,
        INFO, 2,
        WARN, 3,
        ERROR, 4,
        FATAL, 5
    );

    public int getPriority() {
        return PRIORITY_BY_LEVEL.get(this);
    }
}
