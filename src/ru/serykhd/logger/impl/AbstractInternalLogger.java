package ru.serykhd.logger.impl;

import ru.serykhd.logger.InternalLogger;
import ru.serykhd.logger.handler.LoggingHandler;
import ru.serykhd.logger.handler.global.GlobalHandler;
import ru.serykhd.logger.level.Level;
import ru.serykhd.logger.util.StringUtil;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractInternalLogger implements InternalLogger {

    private static final String EXCEPTION_MESSAGE = "Unexpected exception:";

    private final String name;

    private Level level = setLevel(Level.TRACE).getLevel();

    /**
     *
     */
    private boolean ERROR;
    private boolean WARN;
    private boolean INFO;
    private boolean DEBUG;
    private boolean TRACE;

    /**
     *
     */
    private List<LoggingHandler> handlers = new ArrayList<>(GlobalHandler.defaultHandlers);

    /**
     * Creates a new instance.
     */
    protected AbstractInternalLogger(String name) {
        this.name = Objects.requireNonNull(name, "name");

        setLevel(Level.TRACE);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public InternalLogger setLevel(@NonNull Level level) {
        this.level = level;

        this.ERROR = isEnabled(Level.ERROR);
        this.WARN = isEnabled(Level.WARN);
        this.INFO = isEnabled(Level.INFO);
        this.DEBUG = isEnabled(Level.DEBUG);
        this.TRACE = isEnabled(Level.TRACE);

        return this;
    }

    @Override
    public boolean isEnabled(Level level) {
        return isEnabled(level.toInt());
    }

    private boolean isEnabled(int level) {
        return this.level.toInt() <= level;
    }

    @Override
    public boolean isTraceEnabled() {
        return TRACE;
    }

    @Override
    public boolean isDebugEnabled() {
        return DEBUG;
    }

    @Override
    public boolean isInfoEnabled() {
        return INFO;
    }

    @Override
    public boolean isWarnEnabled() {
        return WARN;
    }

    @Override
    public boolean isErrorEnabled() {
        return ERROR;
    }

    @Override
    public void trace(Throwable t) {
        trace(EXCEPTION_MESSAGE, t);
    }

    @Override
    public void debug(Throwable t) {
        debug(EXCEPTION_MESSAGE, t);
    }

    @Override
    public void info(Throwable t) {
        info(EXCEPTION_MESSAGE, t);
    }

    @Override
    public void warn(Throwable t) {
        warn(EXCEPTION_MESSAGE, t);
    }

    @Override
    public void error(Throwable t) {
        error(EXCEPTION_MESSAGE, t);
    }

    @Override
    public List<LoggingHandler> handlers() {
        return handlers;
    }

    @Override
    public void addLoggingHandler(LoggingHandler loggingHandler) {
        handlers.add(loggingHandler);
    }

    @Override
    public String toString() {
        return StringUtil.simpleClassName(this) + '(' + name() + ')';
    }
}
