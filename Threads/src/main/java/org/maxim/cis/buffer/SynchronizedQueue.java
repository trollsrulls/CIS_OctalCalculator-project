package org.maxim.cis.buffer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SynchronizedQueue {

    private Semaphore empty;
    private Semaphore full;

    private List<String> elements;

    public SynchronizedQueue(int size) {
        empty = new Semaphore(size);
        full = new Semaphore(0);
        elements = new LinkedList<>();
    }

    public String getFirst() throws InterruptedException {
        String result;
        full.acquire();
        synchronized (SynchronizedQueue.class) {
            result = elements.get(0);
            elements.remove(0);
        }
        empty.release();
        return result;
    }

    public void put(String element) throws InterruptedException {
        empty.acquire();
        synchronized (SynchronizedQueue.class) {
            elements.add(element);
        }
        full.release();
    }

}
