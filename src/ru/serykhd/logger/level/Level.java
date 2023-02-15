package ru.serykhd.logger.level;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Level {

    ERROR(LevelConstants.ERROR_INT),
    WARN(LevelConstants.WARN_INT),
    INFO(LevelConstants.INFO_INT),
    DEBUG(LevelConstants.DEBUG_INT),
    TRACE(LevelConstants.TRACE_INT);

    private final int levelInt;

    public int toInt() {
        return levelInt;
    }
}
