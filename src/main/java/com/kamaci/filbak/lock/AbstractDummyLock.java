package com.kamaci.filbak.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author Furkan KAMACI
 */
public abstract class AbstractDummyLock implements Lock {

    private static final String NOT_SUPPORTED_EXCEPTION_MESSAGE = "Not supported!";

    /**
     * Dummy implementation
     *
     * @throws InterruptedException
     */
    @Override
    public void lockInterruptibly() throws InterruptedException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
    }

    /**
     * Dummy implementation
     *
     * @return returns nothing
     */
    @Override
    public boolean tryLock() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
    }

    /**
     * Dummy implementation
     *
     * @param time time
     * @param unit unit
     * @return returns nothing
     * @throws InterruptedException
     */
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
    }

    /**
     * Dummy implementation
     *
     * @return returns nothing
     */
    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_EXCEPTION_MESSAGE);
    }
}
