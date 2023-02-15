package ru.serykhd.logger.handler.global;

import ru.serykhd.logger.handler.LoggingHandler;
import ru.serykhd.logger.impl.LogRecord;
import ru.serykhd.logger.level.Level;
import ru.serykhd.logger.slf4j.helpers.FormattingTuple;
import ru.serykhd.logger.slf4j.helpers.MessageFormatter;
import jline.console.ConsoleReader;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.fusesource.jansi.Ansi;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LogRecordProcessor extends Thread {

    public static Level CONSOLE_LOG_LEVEL = Level.valueOf(System.getProperty( "console-log-level", "TRACE" ));

    private static SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");


    @Setter
    private ConsoleReader consoleReader;

    @Getter
    private final BlockingQueue<LogRecord> queue = new LinkedBlockingQueue<>();

    @Getter
    private List<LoggingHandler> handlers = new ArrayList<>();

    public LogRecordProcessor(ConsoleReader consoleReader) {
        super("LogRecord Processor Thread");
        this.consoleReader = consoleReader;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            LogRecord record;
            try {
                record = queue.take();
            } catch (InterruptedException ex) {
                continue;
            }

            processRecord(record);
        }

        for (LogRecord record : queue) {
            processRecord(record);
        }
    }

    @SneakyThrows
    private void processRecord(LogRecord record) {
        String message = formatRecord(record);

        record.getLogger().handlers().forEach(handler -> {
            handler.handle(message);
        });

        if (CONSOLE_LOG_LEVEL.toInt() > record.getLevel().toInt()) {
            return;
        }

        consoleReader.print( Ansi.ansi().eraseLine( Ansi.Erase.ALL ).toString() + ConsoleReader.RESET_LINE + message + Ansi.ansi().reset().toString() );
        consoleReader.drawLine();
        consoleReader.flush();
    }

    private String formatRecord(LogRecord record) {
        StringBuilder formatted = new StringBuilder();

        formatted.append( date.format( record.getMillis() ) );
        formatted.append( " [" );
        formatted.append(record.getLogger().name());
        formatted.append( "] [" );
        formatted.append(record.getLevel());
        formatted.append( "] " );

        FormattingTuple turple;

        // message check
        if (record.getTurple() != null) {
            turple = record.getTurple();
        }
        else if (record.getT() != null) {
            turple = MessageFormatter.arrayFormat(record.getFormat(), record.getArguments(), record.getT());
        }
        else {
            turple = MessageFormatter.arrayFormat(record.getFormat(), record.getArguments());
        }
        formatted.append( turple.getMessage() );

        formatted.append( '\n' );

        if (record.getT() != null ) {
            StringWriter writer = new StringWriter();
            record.getT().printStackTrace( new PrintWriter( writer ) );
            formatted.append( writer );
        }

        return formatted.toString();
    }
}
