package LoggingFramework.Formatter;

import LoggingFramework.domain.LogRecord;

public interface LogFormatter {
  String format(LogRecord logRecord);
}
