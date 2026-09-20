package LoggingFramework.Formatter;

import LoggingFramework.domain.LogRecord;

public class SimpleLogFormatter implements LogFormatter {
    @Override
    public String format(LogRecord logRecord) {
        return "[" + logRecord.getTimestamp() + "] " + logRecord.getLogLevel() + ": " + logRecord.getRecord();
    }
}
