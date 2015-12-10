package org.maxim.cis.calculator.service;

public abstract class AbstractOperation implements Operation {

    protected static final int RADIX = 8;
    protected static final int MINIMAL_ARGS_FOR_OPERATION = 2;

    protected int toDec(String value) {
        return Integer.parseInt(value, RADIX);
    }

    protected String toOctal(int value) {
        return Integer.toString(value, RADIX);
    }

    protected abstract int performOperation(int[] values);

    public String execute(String[] args) {
        if (args == null || args.length < MINIMAL_ARGS_FOR_OPERATION) {
            throw new IllegalArgumentException("Args for operation is empty or not enough.");
        }

        int[] values = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            values[i] = toDec(args[i]);
        }

        int result = performOperation(values);
        return toOctal(result);
    }

}
