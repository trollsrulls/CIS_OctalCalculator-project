package org.maxim.cis.lab3;

import org.maxim.cis.buffer.SynchronizedQueue;
import org.maxim.cis.calculator.OperationFactory;
import org.maxim.cis.executors.Consumer;
import org.maxim.cis.executors.Producer;
import org.maxim.cis.hook.ShutdownHook;

import java.util.concurrent.atomic.AtomicBoolean;

public class RunnerMultitasking {

    public static void main(String[] args) {
        final int CAPACITY = 10000;

        ShutdownHook shutdownHook = new ShutdownHook();
        SynchronizedQueue queue = new SynchronizedQueue(CAPACITY);
        OperationFactory operationFactory = new OperationFactory();

        AtomicBoolean atomic = shutdownHook.getRunAtomic();
        Producer producer1 = new Producer(queue, operationFactory, atomic);
        Producer producer2 = new Producer(queue, operationFactory, atomic);
        Producer producer3 = new Producer(queue, operationFactory, atomic);

        Consumer consumer = new Consumer(queue, atomic);

        Thread t1 = new Thread(producer1);
        Thread t2 = new Thread(producer2);
        Thread t3 = new Thread(producer3);
        Thread t4 = new Thread(consumer);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

}
