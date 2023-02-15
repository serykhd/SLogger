package ru.serykhd.logger.handler.global;

import ru.serykhd.logger.InternalLogger;
import ru.serykhd.logger.impl.LogRecord;
import ru.serykhd.logger.level.Level;
import ru.serykhd.logger.slf4j.helpers.FormattingTuple;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class LoggingOutputStream extends ByteArrayOutputStream {

    private final LogRecordProcessor writer;

    private final Level level;
    private final InternalLogger logger;

    @Override
    public void flush() throws IOException {
        String contents = toString(StandardCharsets.UTF_8);
        super.reset();

        if (contents.isEmpty() || contents.equals(System.lineSeparator())) {
            return;
        }

        writer.getQueue().add(new LogRecord(level, logger, new FormattingTuple(contents)));
    }
}