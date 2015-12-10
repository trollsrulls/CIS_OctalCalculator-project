package org.maxim.cis.executors;

import org.maxim.cis.buffer.SynchronizedQueue;

import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer implements Runnable {

    private AtomicBoolean runAtomic;
    private SynchronizedQueue queue;

    public Consumer(SynchronizedQueue queue, AtomicBoolean isStarted) {
        this.runAtomic = isStarted;
        this.queue = queue;
    }

    public SynchronizedQueue getQueue() {
        return queue;
    }

    @Override
    public void run() {
        while (runAtomic.get()) {
            try {
                //Thread.sleep(1000);
                System.out.println(queue.getFirst());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
