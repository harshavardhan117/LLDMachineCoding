package LoggingFramework.Simulator;

import LoggingFramework.Service.Logger;
import LoggingFramework.Writer.FileLogWriter;
import LoggingFramework.domain.LogLevel;

public class LoggingFrameworkSimulator {
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.setMinimumLogLevel(LogLevel.WARN);

        logger.DEBUG("Debug message", "LoggingFrameworkSimulator");
        logger.INFO("Info message", "LoggingFrameworkSimulator");
        logger.WARN("Warn message", "LoggingFrameworkSimulator");
        logger.ERROR("Error message", "LoggingFrameworkSimulator");
        logger.FATAL("Fatal message", "LoggingFrameworkSimulator");

        logger.setLogWriter(new FileLogWriter("application.log"));
        logger.ERROR("This message is written to application.log", "LoggingFrameworkSimulator");
    }
}
