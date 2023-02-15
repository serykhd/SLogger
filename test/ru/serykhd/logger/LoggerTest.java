package ru.serykhd.logger;

import jline.console.ConsoleReader;
import ru.serykhd.logger.handler.global.GlobalHandler;
import ru.serykhd.logger.handler.impl.FileHandler;
import ru.serykhd.logger.impl.InternalLoggerFactory;

import java.io.IOException;

public class LoggerTest {

    public static void main(String[] args) throws IOException {
        GlobalHandler.defaultHandlers.add(new FileHandler());
        GlobalHandler.setConsoleReader(new ConsoleReader());

        InternalLogger logger = InternalLoggerFactory.getInstance(LoggerTest.class);

        logger.info("sss");
        logger.info("sss {}", 54);

        System.out.println("xx");

        Thread.dumpStack();
    }
}
