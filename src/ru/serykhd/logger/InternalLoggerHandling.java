package ru.serykhd.logger;

import ru.serykhd.logger.handler.LoggingHandler;

import java.util.List;

public interface InternalLoggerHandling {

    List<LoggingHandler> handlers();

    void addLoggingHandler(LoggingHandler loggingHandler);
}
