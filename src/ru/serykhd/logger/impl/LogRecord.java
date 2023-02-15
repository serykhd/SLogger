package ru.serykhd.logger.impl;

import ru.serykhd.logger.InternalLogger;
import ru.serykhd.logger.level.Level;
import ru.serykhd.logger.slf4j.helpers.FormattingTuple;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LogRecord {

    private final Level level;
    private final InternalLogger logger;
    private FormattingTuple turple;
    private final long millis = System.currentTimeMillis();

    //
    private String format;
    private Throwable t;
    private Object[] arguments;

    public LogRecord(Level level, InternalLogger logger, FormattingTuple turple) {
        this.level = level;
        this.logger = logger;
        this.turple = turple;
    }

    public LogRecord(Level level, InternalLogger logger, String format, Throwable t, Object... arguments) {
        this.level = level;
        this.logger = logger;
        this.format = format;
        this.t = t;
        this.arguments = arguments;
    }
}
