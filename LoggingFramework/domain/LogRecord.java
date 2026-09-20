package LoggingFramework.domain;

import java.time.LocalDateTime;


public class LogRecord {
    private String record;
    private LogLevel logLevel;
    private LocalDateTime timestamp;
    private String source;

    public LogRecord(String record, String source) {
        this.record = record;
        this.logLevel = LogLevel.INFO;
        this.timestamp = LocalDateTime.now();
        this.source = source;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public String getRecord() {
        return record;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
