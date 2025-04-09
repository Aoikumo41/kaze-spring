package com.kaze.spring.util;

import com.kaze.spring.lang.Nullable;
import org.springframework.util.StringUtils;

public abstract class Assert {
    public static void hasText(@Nullable String text, String message) {
        if (!StringUtils.hasText(text)) {
            throw new IllegalArgumentException(message);
        }
    }
    public static void notNull(@Nullable Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

}
