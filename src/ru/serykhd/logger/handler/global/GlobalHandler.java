package ru.serykhd.logger.handler.global;

import ru.serykhd.logger.InternalLogger;
import ru.serykhd.logger.handler.LoggingHandler;
import ru.serykhd.logger.impl.InternalLoggerFactory;
import ru.serykhd.logger.level.Level;
import jline.console.ConsoleReader;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class GlobalHandler {

    public List<LoggingHandler> defaultHandlers = new ArrayList<>();
    public LogRecordProcessor writer;

    static {
        ConsoleReader reader = null;
        try {
            reader = new ConsoleReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        reader.setExpandEvents(false);

        writer = new LogRecordProcessor(reader);
        writer.start();
    }

    public void setConsoleReader(@NonNull ConsoleReader reader) {
        writer.setConsoleReader(reader);

        InternalLogger globalLogger = InternalLoggerFactory.getInstance("Global");

        System.setErr(new PrintStream(new LoggingOutputStream(writer, Level.ERROR, globalLogger), true));
        System.setOut(new PrintStream(new LoggingOutputStream(writer, Level.INFO, globalLogger), true));
    }
}
