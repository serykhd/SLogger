package ru.serykhd.logger.impl.jdk;

import ru.serykhd.logger.handler.global.GlobalHandler;
import ru.serykhd.logger.impl.AbstractInternalLogger;
import ru.serykhd.logger.impl.LogRecord;
import ru.serykhd.logger.level.Level;
import ru.serykhd.logger.slf4j.helpers.FormattingTuple;

public class JDKLogger extends AbstractInternalLogger {

    /**
     * Creates a new instance.
     *
     * @param name
     */
    protected JDKLogger(String name) {
        super(name);
    }

    @Override
    public void trace(String msg) {
        if (isTraceEnabled()) {
            log(Level.TRACE, msg);
        }
    }

    @Override
    public void trace(String format, Object arg) {
        if (isTraceEnabled()) {
            log(Level.TRACE, format, arg);
        }
    }

    @Override
    public void trace(String format, Throwable t, Object... arguments) {
        if (isTraceEnabled()) {
            log(Level.TRACE, format, t, arguments);
        }
    }

    @Override
    public void trace(String format, Object... arguments) {
        if (isTraceEnabled()) {
            log(Level.TRACE, format, arguments);
        }
    }

    @Override
    public void trace(String msg, Throwable t) {
        if (isTraceEnabled()) {
            log(Level.TRACE, msg, t);
        }
    }

    public void debug(String msg) {
        if (isDebugEnabled()) {
            log(Level.DEBUG, msg);
        }
    }

    @Override
    public void debug(String format, Object arg) {
        if (isDebugEnabled()) {
            log(Level.DEBUG, format, arg);
        }
    }

    @Override
    public void debug(String format, Throwable t, Object... arguments) {
        if (isDebugEnabled()) {
            log(Level.DEBUG, format, t, arguments);
        }
    }

    @Override
    public void debug(String format, Object... arguments) {
        if (isDebugEnabled()) {
            log(Level.DEBUG, format, arguments);
        }
    }

    @Override
    public void debug(String msg, Throwable t) {
        if (isDebugEnabled()) {
            log(Level.DEBUG, msg, t);
        }
    }

    public void warn(String msg) {
        if (isWarnEnabled()) {
            log(Level.WARN, msg);
        }
    }

    @Override
    public void warn(String format, Object... arguments) {
        if (isWarnEnabled()) {
            log(Level.WARN, format, arguments);
        }
    }

    @Override
    public void warn(String format, Throwable t, Object... arguments) {
        if (isWarnEnabled()) {
            log(Level.WARN, format, t, arguments);
        }
    }

    @Override
    public void warn(String msg, Throwable t) {
        if (isWarnEnabled()) {
            log(Level.WARN, msg, t);
        }
    }

    public void info(String msg) {
        if (isInfoEnabled()) {
            log(Level.INFO, msg);
        }
    }

    @Override
    public void info(String format, Object arg) {
        if (isInfoEnabled()) {
            log(Level.INFO, format, arg);
        }
    }

    @Override
    public void info(String format, Throwable t, Object... arguments) {
        if (isInfoEnabled()) {
            log(Level.INFO, format, t, arguments);
        }
    }

    @Override
    public void info(String format, Object... arguments) {
        if (isInfoEnabled()) {
            log(Level.INFO, format, arguments);
        }
    }

    @Override
    public void info(String msg, Throwable t) {
        if (isInfoEnabled()) {
            log(Level.INFO, msg, t);
        }
    }

    public void error(String msg) {
        if (isErrorEnabled()) {
            log(Level.ERROR, msg);
        }
    }

    @Override
    public void error(String format, Object arg) {
        if (isErrorEnabled()) {
            log(Level.ERROR, format, arg);
        }
    }

    @Override
    public void error(String format, Throwable t, Object... arguments) {
        if (isErrorEnabled()) {
            log(Level.ERROR, format, t, arguments);
        }
    }

    @Override
    public void error(String format, Object... arguments) {
        if (isErrorEnabled()) {
            log(Level.ERROR, format, arguments);
        }
    }

    @Override
    public void error(String msg, Throwable t) {
        if (isErrorEnabled()) {
            log(Level.ERROR, msg, t);
        }
    }

    /**
     *
     */
    @Override
    public void log(Level level, String msg, Throwable cause) {
        log(level, new FormattingTuple(msg, cause));
    }

    @Override
    public void log(Level level, String msg) {
        log(level, new FormattingTuple(msg));
    }

    @Override
    public void log(Level level, String format, Throwable t, Object... arguments) {
        LogRecord record = new LogRecord(level, this, format, t, t, arguments);

        GlobalHandler.writer.getQueue().add(record);
    }

    @Override
    public void log(Level level, String format, Object... arguments) {
        LogRecord record = new LogRecord(level, this, format, null, arguments);

        GlobalHandler.writer.getQueue().add(record);
    }

    private void log(Level level, FormattingTuple turple) {
        LogRecord record = new LogRecord(level, this, turple);

        GlobalHandler.writer.getQueue().add(record);
    }
}
