package ru.serykhd.logger;

import ru.serykhd.logger.level.Level;

public interface InternalLogger extends InternalLoggerHandling {

    String name();

    Level getLevel();

    InternalLogger setLevel(Level level);

    boolean isTraceEnabled();

    void trace(String msg);

    void trace(String format, Object arg);

    void trace(String format, Throwable t, Object... arguments);

    void trace(String format, Object... arguments);

    void trace(String msg, Throwable t);

    void trace(Throwable t);

    boolean isDebugEnabled();

    void debug(String msg);

    void debug(String format, Object arg);

    void debug(String format, Throwable t, Object... arguments);

    void debug(String format, Object... arguments);

    void debug(String msg, Throwable t);

    void debug(Throwable t);

    boolean isInfoEnabled();

    void info(String msg);

    void info(String format, Object arg);

    void info(String format, Throwable t, Object... arguments);

    void info(String format, Object... arguments);

    void info(String msg, Throwable t);

    void info(Throwable t);

    boolean isWarnEnabled();

    void warn(String msg);

    void warn(String format, Object... arguments);

    void warn(String format, Throwable t, Object... arguments);

    void warn(String msg, Throwable t);

    void warn(Throwable t);

    boolean isErrorEnabled();

    void error(String msg);

    void error(String format, Object arg);

    void error(String format, Throwable t, Object... arguments);

    void error(String format, Object... arguments);

    void error(String msg, Throwable t);

    void error(Throwable t);

    boolean isEnabled(Level level);

    void log(Level level, String msg);

    void log(Level level, String format, Throwable t, Object... arguments);;

    void log(Level level, String format, Object... arguments);

    void log(Level level, String msg, Throwable t);
}
