package com.kamaci.filbak.safeobjects;

import com.kamaci.filbak.enums.LockType;
import com.kamaci.filbak.lock.bakery.Bakery;
import com.kamaci.filbak.lock.filter.Filter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Furkan KAMACI
 */
public class Counter implements Runnable {
    private long value;
    private Lock lock;
    private LockType lockType;
    private int maxNumber;
    private int capacity;

    private static final int DEFAULT_MAX_NUMBER = 500;

    /**
     * Constructor for Counter class.
     *
     * @param value    initial value
     * @param capacity capacity for {@link Filter} and {@link Bakery} locks
     */
    public Counter(long value, int capacity) {
        this(value, LockType.REENTRANT, Counter.DEFAULT_MAX_NUMBER, capacity);
    }

    /**
     * Constructor for Counter class.
     *
     * @param value    initial value
     * @param lockType lock type to use.
     * @param capacity capacity for {@link Filter} and {@link Bakery} locks
     * @see LockType
     */
    public Counter(long value, LockType lockType, int capacity) {
        this(value, lockType, Counter.DEFAULT_MAX_NUMBER, capacity);
    }

    /**
     * Constructor for Counter class.
     *
     * @param value     initial value
     * @param lockType  lock type to use.
     * @param capacity  capacity for {@link Filter} and {@link Bakery} locks
     * @param maxNumber maximum number for counter
     * @see LockType
     */
    public Counter(long value, LockType lockType, int capacity, int maxNumber) {
        this.value = value;
        this.lockType = lockType;
        this.capacity = capacity;
        this.maxNumber = maxNumber;
        switch (this.lockType) {
            case FILTER:
                lock = new Filter(capacity);
                break;
            case BAKERY:
                lock = new Bakery(capacity);
                break;
            case REENTRANT:
            default:
                lock = new ReentrantLock();
        }
    }

    /**
     * gets and increments value up to a maximum number
     *
     * @return value before increment if it didn't exceed a defined maximum number. Otherwise returns maximum number.
     */
    public long getAndIncrement() {
        long temp;
        lock.lock();
        try {
            if (value >= maxNumber) {
                return value;
            }
            temp = value;
            value = temp + 1;
        } finally {
            lock.unlock();
        }
        return temp;
    }

    @Override
    public void run() {
        while (getAndIncrement() < maxNumber) {
            //do nothing
        }
    }

    public long getValue() {
        return value;
    }

    public LockType getLockType() {
        return lockType;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getCapacity() {
        return capacity;
    }
}
