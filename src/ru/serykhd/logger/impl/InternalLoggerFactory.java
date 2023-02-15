package ru.serykhd.logger.impl;

import ru.serykhd.logger.InternalLogger;
import ru.serykhd.logger.impl.jdk.JDKLoggerFactory;

public abstract class InternalLoggerFactory {

    private static InternalLoggerFactory defaultFactory = new JDKLoggerFactory();

    /**
     * Creates a new logger instance with the name of the specified class.
     */
    public static InternalLogger getInstance(Class<?> clazz) {
        return getInstance(clazz.getSimpleName());
    }

    /**
     * Creates a new logger instance with the specified name.
     */
    public static InternalLogger getInstance(String name) {
        return defaultFactory.newInstance(name);
    }

    protected abstract InternalLogger newInstance(String name);
}
