package com.kaze.spring.core;


import com.kaze.spring.lang.Nullable;

public class NestedRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NestedRuntimeException(@Nullable String msg) {
        super(msg);
    }

    public NestedRuntimeException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

    /**
     * 获取最根本的异常（如果有的话）
     */
    @Nullable
    public Throwable getRootCause() {
        return NestedExceptionUtils.getRootCause(this);
    }

    /**
     * 获取给定异常的最具体原因，即最内层原因（根本原因）或异常本身。
     * 与 getRootCause 的区别在于，如果没有根本原因，则回退到原始异常。
     */
    public Throwable getMostSpecificCause() {
        Throwable rootCause = getRootCause();
        return (rootCause != null ? rootCause : this);
    }

    /**
     * 检查此异常是否包含给定类型的异常：要么该异常本身属于给定类，要么其嵌套原因中存在该类型的异常。
     */
    public boolean contains(@Nullable Class<?> exType) {
        if (exType == null) return false;
        if (exType.isInstance(this)) return true;
        Throwable cause = getCause();
        if (cause == this) return false;
        if (cause instanceof NestedRuntimeException exception) {
            return exception.contains(exType);
        } else {
            while (cause != null) {
                if (exType.isInstance(cause)) return true;
                if (cause == null) break;
                cause = cause.getCause();
            }
        }
        return false;
    }


}
