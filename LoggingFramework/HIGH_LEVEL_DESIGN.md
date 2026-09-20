# Logging Framework — LLD Study Guide

This module is a small reusable logging framework for practising layered Java design. It supports level-based filtering, configurable formatting, console output, and file output.

## 1. Problem Statement

Design a logging module that applications can call to record messages. The module should decide whether a message meets the configured minimum log level, format it, and write it to an output destination.

## 2. Requirements and Assumptions

### Functional requirements

- Support `DEBUG`, `INFO`, `WARN`, `ERROR`, and `FATAL` log levels.
- Allow the application to configure a minimum log level. Messages below it are ignored.
- Include timestamp, level, message, and source/class name in a `LogRecord`.
- Support console and file output in version 1.
- Support simple and detailed log formatting.

### Intentional simplifications

- `Logger` owns one output writer. Sending a record to several writers is a future enhancement.
- Database output is not implemented.
- A file is opened, appended to, and closed for each record. This keeps the first implementation simple.
- If a file write fails, the framework writes a small internal error to `stderr` and does not crash the application.

## 3. Design Approach

```text
LoggingFrameworkSimulator (application/client)
        ↓
Logger (creates record and applies level filtering)
        ↓
LogWriter (output abstraction)
        ↓
LogFormatter (converts record into text)
        ↓
Console or file
```

### Why this separation matters

- The application does not need to know formatting or file-writing details.
- `Logger` coordinates the logging flow and owns the minimum-level configuration.
- A writer focuses on where the log is written.
- A formatter focuses only on how the log looks.

## 4. Core Domain Objects

| Object | Responsibility |
| --- | --- |
| `LogLevel` | Fixed levels and their priority lookup. |
| `LogRecord` | One log event: timestamp, level, source, and message. |
| `LoggerConfig` | Stores the minimum allowed `LogLevel`. |

`LogLevel` uses a `Map<LogLevel, Integer>` to associate each fixed level with its priority:

```text
DEBUG → 1, INFO → 2, WARN → 3, ERROR → 4, FATAL → 5
```

## 5. Logging Flow

1. The application calls a method such as `logger.INFO(message, source)`.
2. `Logger` checks whether the message level is at least the configured minimum level.
3. If it is below the threshold, `Logger` returns immediately.
4. Otherwise, `Logger` creates a `LogRecord`.
5. `Logger` calls `LogWriter.writeLog(record, formatter)`.
6. The writer uses the formatter and writes the resulting text to its destination.

Example with minimum level `WARN`:

```text
DEBUG → ignored
INFO  → ignored
WARN  → written
ERROR → written
FATAL → written
```

## 6. Output and Formatting Abstractions

```text
LogWriter                         LogFormatter
├── ConsoleLogWriter              ├── SimpleLogFormatter
└── FileLogWriter                 └── detailedLogFormatter
```

- `ConsoleLogWriter` prints the formatted text.
- `FileLogWriter` receives a file name in its constructor and appends formatted text to that file.
- `SimpleLogFormatter` includes timestamp, level, and message.
- `detailedLogFormatter` also includes the source/class name.

## 7. Pattern Used: Strategy

The framework already uses the Strategy pattern through interfaces:

- `LogWriter` lets `Logger` work with console or file output without knowing their details.
- `LogFormatter` lets `Logger` switch between simple and detailed output formats.

No factory is needed yet. A factory becomes useful only when configuration such as `"FILE"` or `"CONSOLE"` must decide which writer object to construct.

## 8. Package Guide

| Package | What belongs there |
| --- | --- |
| `domain` | `LogLevel`, `LogRecord`, and `LoggerConfig`. |
| `Service` | `Logger`, which coordinates one logging request. |
| `Writer` | `LogWriter`, console output, and file output. |
| `Formatter` | `LogFormatter` and its formatting implementations. |
| `Simulator` | Demonstrates framework usage. |

## 9. Current Simulator Scenario

`LoggingFrameworkSimulator` sets the minimum level to `WARN` and demonstrates filtering:

1. `DEBUG` and `INFO` are ignored.
2. `WARN`, `ERROR`, and `FATAL` print to the console.
3. The writer is switched to `FileLogWriter("application.log")`.
4. An `ERROR` message is appended to `application.log`.

Run from the `LoggingFramework` folder:

```powershell
New-Item -ItemType Directory -Force out
javac -d out (Get-ChildItem -Recurse -Filter *.java | ForEach-Object FullName)
java -cp out LoggingFramework.Simulator.LoggingFrameworkSimulator
```

## 10. Future Enhancements

Do these one at a time after practising the related topic:

1. **Concurrency:** remove per-request mutable fields from `Logger`; make file writing safe when several threads write to the same file.
2. **Multiple outputs:** let `Logger` hold a list of writers so one record can go to console and file together.
3. **Persistent file writer:** keep a buffered writer open and add an explicit `close()` or `flush()` lifecycle method.
4. **General filters:** add filters beyond minimum level, for example filtering by source or message content.
5. **Factory:** create writer/formatter implementations from configuration values only when that configuration requirement exists.
6. **File rotation:** create a new file when a log file becomes too large or reaches a time limit.
7. **Asynchronous logging:** use a queue and a background writer thread to reduce application-thread latency.
8. **Structured logging:** write JSON logs for searching and analysis tools.

## 11. Interview Checklist

State the scope first: levels, a global minimum level, one writer, console/file output, and formatting. Explain that `LogWriter` and `LogFormatter` are strategy abstractions. Demonstrate the happy path and level filtering, then mention concurrency and multi-destination output as deliberate future extensions rather than pretending they are already complete.
