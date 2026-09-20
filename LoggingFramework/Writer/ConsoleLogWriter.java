package LoggingFramework.Writer;

import LoggingFramework.domain.LogRecord;
import LoggingFramework.Formatter.*;

public class ConsoleLogWriter implements LogWriter {
    @Override
    public void writeLog(LogRecord logRecord, LogFormatter logFormatter) {
        String formattedMessage = logFormatter.format(logRecord);
        System.out.println(formattedMessage);
    }
}
