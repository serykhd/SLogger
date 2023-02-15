package ru.serykhd.logger.handler.impl;

import ru.serykhd.logger.handler.LoggingHandler;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import ru.serykhd.common.thread.SThreadFactory;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileHandler implements LoggingHandler {

    private static final Path DEFAULT_PATH = Path.of("logs", "log.log");
    private final Queue<String> queue = new ConcurrentLinkedQueue<>();

    private final Path file;

    private ScheduledExecutorService executor;

    public FileHandler() {
        this(DEFAULT_PATH);
    }

    public FileHandler(@NonNull Path path) {
        file = path;
        mount();
        executor = Executors.newSingleThreadScheduledExecutor(new SThreadFactory("SLogger FileHandler"));
        executor.scheduleAtFixedRate(() -> {
            try {
                flush();
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    public void handle(@NonNull String s) {
        queue.add(s);
    }

    @SneakyThrows
    private void mount() {
        Files.createDirectories(file.getParent());

        if (Files.exists(file)) {
            return;
        }

        Files.createFile(file);
    }

    @SneakyThrows
    public void flush() {
        if (queue.isEmpty()) {
            return;
        }

        @Cleanup
        BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        String line = null;

        while ((line = queue.poll()) != null) {
            writer.write(line);
        }
    }

    public void shutdown() {
        executor.shutdownNow();

        flush();
    }
}