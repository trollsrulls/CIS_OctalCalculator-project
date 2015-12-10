package org.maxim.cis.hook;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ShutdownHook {

    private static final Lock MUTEX = new ReentrantLock(true);
    private AtomicBoolean runAtomic;

    public ShutdownHook() {
        MUTEX.lock();
        this.runAtomic = new AtomicBoolean(true);
    }

    public AtomicBoolean getRunAtomic() {
        return runAtomic;
    }

    public void attach(boolean flag) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                MUTEX.unlock();
                runAtomic.set(false);
            }
        });
    }
}