package LoggingFramework.Service;

import LoggingFramework.Writer.ConsoleLogWriter;
import LoggingFramework.domain.*;

import LoggingFramework.Writer.*;
import  LoggingFramework.Formatter.*;


public class Logger {
    private String record;
    private String source;
    private LogLevel logLevel;
    private LoggerConfig loggerConfig;
    private LogWriter logWriter;
    private LogFormatter logFormatter;


    public Logger() {
        this.logLevel = LogLevel.INFO;
        this.loggerConfig = new LoggerConfig(LogLevel.INFO);
        this.logWriter = new ConsoleLogWriter();
        this.logFormatter = new SimpleLogFormatter();
    }

    public void setMinimumLogLevel(LogLevel logLevel) {
        this.loggerConfig.setLogLevel(logLevel);
    }

    public void setLogWriter(LogWriter logWriter) {
        this.logWriter = logWriter;
    }

    public void setLogFormatter(LogFormatter logFormatter) {
        this.logFormatter = logFormatter;
    }

    public void INFO(String record, String source){
        if (!isEnabled(LogLevel.INFO)) {
            return;
        }
        this.logLevel = LogLevel.INFO;
        this.record = record;
        this.source = source;

        LogRecord logRecord = new LogRecord(this.record, this.source);
        logRecord.setLogLevel(this.logLevel);

        this.logWriter.writeLog(logRecord, this.logFormatter);
    }

    public void ERROR(String record, String source){
        if (!isEnabled(LogLevel.ERROR)) {
            return;
        }
        this.logLevel = LogLevel.ERROR;
        this.record = record;
        this.source = source;

        LogRecord logRecord = new LogRecord(this.record, this.source);
        logRecord.setLogLevel(this.logLevel);

        this.logWriter.writeLog(logRecord, this.logFormatter);
    }


    public void DEBUG(String record, String source){
        if (!isEnabled(LogLevel.DEBUG)) {
            return;
        }
        this.logLevel = LogLevel.DEBUG;
        this.record = record;
        this.source = source;

        LogRecord logRecord = new LogRecord(this.record, this.source);
        logRecord.setLogLevel(this.logLevel);

        this.logWriter.writeLog(logRecord, this.logFormatter);
    }

    public void WARN(String record, String source){
        if (!isEnabled(LogLevel.WARN)) {
            return;
        }
        this.logLevel = LogLevel.WARN;
        this.record = record;
        this.source = source;

        LogRecord logRecord = new LogRecord(this.record, this.source);
        logRecord.setLogLevel(this.logLevel);

        this.logWriter.writeLog(logRecord, this.logFormatter);
    }

    public void FATAL(String record, String source){
        if (!isEnabled(LogLevel.FATAL)) {
            return;
        }
        this.logLevel = LogLevel.FATAL;
        this.record = record;
        this.source = source;

        LogRecord logRecord = new LogRecord(this.record, this.source);
        logRecord.setLogLevel(this.logLevel);

        this.logWriter.writeLog(logRecord, this.logFormatter);
    }

    private boolean isEnabled(LogLevel messageLevel) {
        return messageLevel.getPriority() >= loggerConfig.getLogLevel().getPriority();
    }

}
