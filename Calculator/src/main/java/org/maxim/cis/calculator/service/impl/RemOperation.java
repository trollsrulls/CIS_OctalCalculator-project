package org.maxim.cis.calculator.service.impl;

import org.maxim.cis.calculator.service.AbstractOperation;

public class RemOperation extends AbstractOperation {

    @Override
    protected int performOperation(int[] values) {
        int result = values[0];
        for (int i = 1; i < values.length; i++) {
            result %= values[i];
        }
        return result;
    }

}
