package com.kaze.spring.util;

import org.springframework.lang.Nullable;

public class StringUtils {
    public static boolean hasLength(@Nullable String str) {
        return (str != null && !str.isEmpty());
    }
}
