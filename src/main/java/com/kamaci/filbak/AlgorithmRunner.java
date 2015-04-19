package com.kamaci.filbak;

import com.kamaci.filbak.enums.LockType;
import com.kamaci.filbak.safeobjects.Counter;
import com.kamaci.filbak.statistics.StatisticsCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author Furkan KAMACI
 */
public class AlgorithmRunner {

    private int[] threadCounts;
    private int maxNumber;
    private int retryCount;

    public static final int[] DEFAULT_THREAD_COUNTS = {1, 2, 3, 4, 5, 6, 7, 8};
    public static final int DEFAULT_MAX_NUMBER = 10000000;
    public static final int DEFAULT_RETRY_COUNT = 20;
    private static final LockType[] LOCK_TYPES = {LockType.FILTER, LockType.BAKERY, LockType.REENTRANT};

    private static final Logger LOGGER = LoggerFactory.getLogger(AlgorithmRunner.class);


    /**
     * Algorithm runner constructor
     */
    public AlgorithmRunner() {
        this(DEFAULT_THREAD_COUNTS, DEFAULT_MAX_NUMBER, DEFAULT_RETRY_COUNT);
    }

    /**
     * Algorithm runner constructor
     *
     * @param maxNumber maximum number
     */
    public AlgorithmRunner(int maxNumber) {
        this(DEFAULT_THREAD_COUNTS, maxNumber, DEFAULT_RETRY_COUNT);
    }

    /**
     * Algorithm runner constructor
     *
     * @param maxNumber  maximum number
     * @param retryCount retry count
     */
    public AlgorithmRunner(int maxNumber, int retryCount) {
        this(DEFAULT_THREAD_COUNTS, maxNumber, retryCount);
    }

    /**
     * Algorithm runner constructor
     *
     * @param threadCounts number of thread counts
     */
    public AlgorithmRunner(int[] threadCounts) {
        this(threadCounts, DEFAULT_MAX_NUMBER, DEFAULT_RETRY_COUNT);
    }

    /**
     * Algorithm runner constructor
     *
     * @param threadCounts number of thread counts
     * @param maxNumber    maximum number
     * @param retryCount   retry count
     */
    public AlgorithmRunner(int[] threadCounts, int maxNumber, int retryCount) {
        //Security with defensive copying instead of storing passed array directly
        this.threadCounts = Arrays.copyOf(threadCounts, threadCounts.length);
        this.maxNumber = maxNumber;
        this.retryCount = retryCount;
    }

    /**
     * Runs tests and calculates algorithm statistics
     *
     * @throws InterruptedException
     * @see #runTest(Counter, int)
     */
    public void runStatistics() throws InterruptedException {
        StatisticsCollector statisticsCollector;

        for (LockType lockType : LOCK_TYPES) {
            for (int threadCount : threadCounts) {
                for (int currentTry = 0; currentTry < retryCount; currentTry++) {
                    Counter counter = new Counter(0, lockType, threadCount, maxNumber);
                    long elapsedTime = runTest(counter, threadCount);
                    statisticsCollector = new StatisticsCollector(threadCount, counter.getMaxNumber(), counter.getLockType(), elapsedTime, currentTry);
                    LOGGER.info(String.valueOf(statisticsCollector));
                }
            }
        }
    }

    /**
     * Runs test for given counter and thread count
     *
     * @param counter     counter
     * @param threadCount thread count
     * @return elapsed time
     * @throws InterruptedException
     */
    public long runTest(Counter counter, int threadCount) throws InterruptedException {
        Thread[] threads = new Thread[threadCount];
        long threadNumber = 0;

        long startTime = System.nanoTime();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(counter);
            //more safer way compared to module of thread id approach
            threads[i].setName(String.valueOf(threadNumber++));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long finishTime = System.nanoTime();

        return TimeUnit.NANOSECONDS.toMillis(finishTime - startTime);
    }
}
