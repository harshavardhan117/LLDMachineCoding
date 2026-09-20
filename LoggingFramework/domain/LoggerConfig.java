package LoggingFramework.domain;

public class LoggerConfig {
    private LogLevel logLevel;

    public LoggerConfig(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
