package com.kamaci.filbak.util;

/**
 * Utility class for file Concurrency Operations
 *
 * @author Furkan KAMACI
 */
public final class ConcurrencyUtils {

    /**
     * Private constructor to prevent instantiation
     */
    private ConcurrencyUtils() {
    }

    /**
     * @param capacity thread count
     * @return thread id
     * @see #getCurrentThreadId()
     * @deprecated
     */
    public static int getCurrentThreadId(int capacity) {
        return (int) (Thread.currentThread().getId() % capacity);
    }

    /**
     * @return thread id which is set to thread's name
     */
    public static int getCurrentThreadId() {
        return Integer.parseInt(Thread.currentThread().getName());
    }

}
