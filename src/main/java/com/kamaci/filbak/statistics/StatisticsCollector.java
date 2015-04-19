package com.kamaci.filbak.statistics;

import com.kamaci.filbak.enums.LockType;

/**
 * @author Furkan KAMACI
 */
public class StatisticsCollector {

    private int threadCount;

    private int maxNumber;

    private LockType lockType;

    private long elapsedTime;

    private int currentTry;

    public StatisticsCollector() {
    }

    /**
     * Collects statistics for given parameters
     *
     * @param threadCount thread count
     * @param maxNumber   maximum number
     * @param lockType    lock type
     * @param elapsedTime elapsed time for each test run
     * @see LockType
     */
    public StatisticsCollector(int threadCount, int maxNumber, LockType lockType, long elapsedTime) {
        this(threadCount, maxNumber, lockType, elapsedTime, 0);
    }

    /**
     * Collects statistics for given parameters
     *
     * @param threadCount thread count
     * @param maxNumber   maximum number
     * @param lockType    lock type
     * @param elapsedTime elapsed time for each test run
     * @param currentTry  try number for which test is run
     * @see LockType
     */
    public StatisticsCollector(int threadCount, int maxNumber, LockType lockType, long elapsedTime, int currentTry) {
        this.threadCount = threadCount;
        this.maxNumber = maxNumber;
        this.lockType = lockType;
        this.elapsedTime = elapsedTime;
        this.currentTry = currentTry;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public LockType getLockType() {
        return lockType;
    }

    public void setLockType(LockType lockType) {
        this.lockType = lockType;
    }

    public long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getCurrentTry() {
        return currentTry;
    }

    @Override
    public String toString() {
        return "currentTry = " + currentTry + ", threadCount = " + threadCount + ", maxNumber = " + maxNumber + ", lockType = " + lockType + ", elapsedTime = " + elapsedTime + " (ms)";
    }
}
