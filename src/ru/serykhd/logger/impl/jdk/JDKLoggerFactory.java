package ru.serykhd.logger.impl.jdk;

import ru.serykhd.logger.InternalLogger;
import ru.serykhd.logger.impl.InternalLoggerFactory;

public class JDKLoggerFactory extends InternalLoggerFactory {

    @Override
    protected InternalLogger newInstance(String name) {
        return new JDKLogger(name);
    }
}
