package org.maxim.cis.executors;

import org.maxim.cis.buffer.SynchronizedQueue;
import org.maxim.cis.calculator.OperationFactory;
import org.maxim.cis.calculator.service.Operation;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer implements Runnable {

    private AtomicBoolean runAtomic;

    private SynchronizedQueue queue;
    private OperationFactory operationFactory;

    public Producer(SynchronizedQueue queue, OperationFactory operationFactory, AtomicBoolean runAtomic) {
        this.runAtomic = runAtomic;
        this.queue = queue;
        this.operationFactory = operationFactory;
    }

    public SynchronizedQueue getQueue() {
        return queue;
    }

    public OperationFactory getOperationFactory() {
        return operationFactory;
    }

    @Override
    public void run() {
        Random rand = new Random();
        final String[] operations = {"+", "-", "*", "%"};

        while (runAtomic.get()) {
            try {
                int operationIndex = rand.nextInt(operations.length);
                String leftValue = "" + rand.nextInt() % 8;
                String rightValue = "" + rand.nextInt() % 8;

                Operation operation = operationFactory.getOperation(operations[operationIndex]);
                String result = operation.execute(new String[]{leftValue, rightValue});
                queue.put(result);
            } catch (InterruptedException | RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
