package com.kaze.spring.core;

/**
 * 嵌套异常的工具类
 * 提供给NestedRuntimeException, NestedCheckedException用
 */
public abstract class NestedExceptionUtils {

	/**
	 * 获取给定异常的最根本原因（如果存在的话）
	 */
	public static Throwable getRootCause( Throwable original) {
		if (original == null) {
			return null;
		}
		Throwable rootCause = null;
		Throwable cause = original.getCause();
		while (cause != null && cause != rootCause) {
			rootCause = cause;
			cause = cause.getCause();
		}
		return rootCause;
	}

	/**
	 * 获取给定异常的最具体原因，即最内层原因（根本原因）或异常本身。
	 * 与 getRootCause 的区别在于，如果没有根本原因，则回退到原始异常。
	 */
	public static Throwable getMostSpecificCause(Throwable original) {
		Throwable rootCause = getRootCause(original);
		return (rootCause != null ? rootCause : original);
	}

}