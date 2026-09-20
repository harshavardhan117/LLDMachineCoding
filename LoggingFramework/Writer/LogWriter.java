package LoggingFramework.Writer;

import LoggingFramework.domain.LogRecord;
import LoggingFramework.Formatter.*;;

public interface LogWriter {
    void writeLog(LogRecord logRecord, LogFormatter logFormatter);
}