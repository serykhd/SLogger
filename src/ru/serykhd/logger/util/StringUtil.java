package ru.serykhd.logger.util;

import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class StringUtil {

    private final char PACKAGE_SEPARATOR_CHAR = '.';

    /**
     * The shortcut to {@link #simpleClassName(Class) simpleClassName(o.getClass())}.
     */
    public String simpleClassName(Object o) {
        if (o == null) {
            return "null_object";
        }

        return simpleClassName(o.getClass());
    }

    /**
     * Generates a simplified name from a {@link Class}.  Similar to {@link Class#getSimpleName()}, but it works fine
     * with anonymous classes.
     */
    public String simpleClassName(Class<?> clazz) {
        String className = Objects.requireNonNull(clazz, "clazz").getName();
        final int lastDotIdx = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
        if (lastDotIdx > -1) {
            return className.substring(lastDotIdx + 1);
        }
        return className;
    }
}
