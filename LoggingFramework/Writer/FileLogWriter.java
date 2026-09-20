package LoggingFramework.Writer;

import java.io.FileWriter;
import java.io.IOException;

import LoggingFramework.domain.LogRecord;
import LoggingFramework.Formatter.*;;

public class FileLogWriter implements LogWriter {
    private final String fileName;

    public FileLogWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeLog(LogRecord logRecord, LogFormatter logFormatter) {
        String formattedMessage = logFormatter.format(logRecord);

        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.write(formattedMessage);
            fileWriter.write(System.lineSeparator());
        } catch (IOException exception) {
            System.err.println("Unable to write log to file: " + exception.getMessage());
        }
    }
}
